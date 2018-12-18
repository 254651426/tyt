$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/order/list',
        datatype: "json",
        colModel: [
        	{ hidden:true, name: 'id',key: true},
        	{ label: '商户名称', name: 'mid', width: 60 },
        	{ label: '付款人', name: 'truename', width: 60 },
        	{ label: '订单号', name: 'orderId', width: 60 },
        	{ label: '付款金额￥', name: 'goodsPrice', width: 60 },
        	{ label: '付款通道', name: 'payWay', width: 60 },
        	{ label: '付款方式', name: 'payType', width: 60 },
        	{ label: '付款状态', name: 'ispay', width: 60 , formatter: function(value, options, row){
				return value == false? 
						'<span style="color:red">待支付</span>' : 
						'<span  style="color:green">已支付</span>';
				}},
        	{ label: '第三方单号', name: 'transactionId', width: 60 },
        	{ label: '付款时间', name: 'paytime', width: 60 },
        	{ label: '创建时间', name: 'createDate', width: 60 },
        	{ label: '操作', name: '', width: 60 },
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
	el:'#orderApp',
	data:{
		q:{
			key:"",
			searchBy: ""
		},
		showList: true,
		title: null,
		order: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.order = {};
		},
		update: function () {
			var id = getSelectedRow();
			
			if(id == null){
				return ;
			}
			
			$.get("../sys/order/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.order = r.order;
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
				    url: "../sys/order/delete",
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
			var url = vm.order.id == null ? "../sys/order/save" : "../sys/order/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.order),
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