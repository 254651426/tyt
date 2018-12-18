$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/merchants/list',
        datatype: "json",
        colModel: [
        	{ hidden:true, name: 'id',key: true},
        	{ label: '用户名', name: 'username', width: 60 },
        	{ label: '手机号', name: 'mobile', width: 60 },
        	{ label: '微信子商户号',name: 'wxSubMchId', width: 60 },
        	{ label: '支付宝授权token', name: 'alipayAppAuthToken', width: 60 },
        	{ label: '创建时间', name: 'createDate', width: 60 },
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
	el:'#merchantsApp',
	data:{
		q:{
			key:"",
			searchBy: ""
		},
		showList: true,
		title: null,
		merchants: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.merchants = {};
		},
		update: function () {
			var id = getSelectedRow();
			
			if(id == null){
				return ;
			}
			
			$.get("../sys/merchants/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.merchants = r.merchants;
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
				    url: "../sys/merchants/delete",
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
			var url = vm.merchants.id == null ? "../sys/merchants/save" : "../sys/merchants/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.merchants),
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