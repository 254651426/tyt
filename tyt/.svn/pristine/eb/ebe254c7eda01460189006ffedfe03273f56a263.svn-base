$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/numbers/list',
        datatype: "json",
        colModel: [
        	{ hidden:true, name: 'id',key: true},
        	{ label: '', name: 'number', width: 60 },
        	{ label: '是否使用0，未使用，1使用', name: 'isuser', width: 60 },
        	{ label: '', name: 'createDate', width: 60 },
        	{ label: '', name: 'modifyDate', width: 60 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.result",
            page: "page.pageNumber",
            total: "page.pageCount",
            records: "page.totalCount"
        },
        prmNames : {
            page:"pageNumber", 
            rows:"pageSize", 
            order: "order",
            sort:"orderBy"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#numbersApp',
	data:{
		q:{
			key:"",
			searchBy: ""
		},
		showList: true,
		title: null,
		numbers: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.numbers = {};
		},
		update: function () {
			var id = getSelectedRow();
			
			if(id == null){
				return ;
			}
			
			$.get("../sys/numbers/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.numbers = r.numbers;
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
				    url: "../sys/numbers/delete",
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
			var url = vm.numbers.id == null ? "../sys/numbers/save" : "../sys/numbers/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.numbers),
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
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{
                	'searchBy': vm.q.searchBy,
                	'keyword': vm.q.keyword
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});