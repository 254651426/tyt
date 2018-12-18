var vm = new Vue({
	el:'#dictionaryApp',
	data:{
		params:[
		        
	    ],
	    ischeckdate:[
	    ],// 获取选项框数据
	    checkAll: false,// 全选
		totalItems : 0,
		pageSize : 10,
		pagination : {
			currentPage : 1
		},
		maxSize : 0,
		q:{
			key:"",
			searchBy: ""
		},
		showList: true,
		title: null,
		dictionary: {}
	},
	// 启动监听
	watch:{
		 'ischeckdate':function(){
		   if(this.params.length == this.ischeckdate.length){
		     this.checkAll=true;
		   }else{
		     this.checkAll=false;
		   }
		 },
	},
	methods: {
		checkedAll: function() {
			var ischeckdate = [];// 新建勾选框
		     // 根据勾选框判断
		     if (this.checkAll) {
		    	 //
			     this.params.forEach(function(item){
			    	 ischeckdate.push(item.id);}
			     );
		     }
		     this.ischeckdate = ischeckdate;
		},
		pageNum : function(){
			paging(true);
		},
		pageChanged : function() {
			paging(false);
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.dictionary = {};
		},
		update: function (id) {
			
			if(id == null){
				return ;
			}
			
			$.get("../sys/dictionary/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.dictionary = r.dictionary;
            });
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/dictionary/delete",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.dictionary.id == null ? "../sys/dictionary/save" : "../sys/dictionary/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.dictionary),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		reload: function (event) {
			vm.showList = true;
			paging(true);
		}
	}
});

function paging(syc) {
	var index;
	$.ajax({
		type : "POST",
		url : "../sys/dictionary/list",
		contentType : "application/x-www-form-urlencoded",
		dataType : 'json',
		async : syc,
		data : {
			"pageNumber" : vm.pagination.currentPage,
			"pageSize" : vm.pageSize,
			"searchBy": vm.q.searchBy,
        	"keyword": vm.q.keyword
		},
		beforeSend : function(){
			index = layer.load(1);
		},
		complete : function(data) { 
			layer.close(index);
		}, 
		success : function(r) {
			vm.pageSize = r.page.pageSize;
			vm.pagination.currentPage = r.page.pageNumber;
			vm.params = r.page.result;
			for(var i=0;i<vm.params.length;i++){
				var data;
				if(vm.params[i].merchants==null){
					var data = "";
				}else{
					var data = vm.params[i].merchants.receivablesUserName;
				}
				vm.params[i].receivablesUserName = data;
			}
			vm.totalItems = r.page.totalCount;
		}
	});
}

$().ready( function() {
	paging(true);
});