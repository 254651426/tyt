$(function () {	
    $("#jqGrid").jqGrid({
        url: '../sys/qrcode/list',
        datatype: "json",
        colModel: [
        	{ hidden:true, name: 'id',key: true},
        	{ label: '二维码编号', name: 'number', width: 60 },
        	{ label: '生成二维码地址', name: 'imgpath', width: 60 },
        	{ label: '绑定商户id', name: 'merchantid', width: 60 },
        	{ label: '二维码使用状态', name: 'isuser', width: 60, formatter: function(value, options, row){
				return value ===0? 
						'<span style="color:red">未绑定</span>' : 
						'<span  style="color:green">已绑定</span>';
				} },
        	{ label: '绑定时间', name: 'bindDate', width: 60 },
        	{ label: '创建时间', name: 'createDate', width: 60 },	
        	{ label: '操作', name: 'isuser', width: 60 , formatter: function(value, options, row){
        		var html="";
        		if(value ===0)
        		{
        			html =  '<a class="bind" data="'+row.id+'" href="javascript:void(0)" style="color:red">【绑定】</a>';
        		}else
        		{
        			html = '<a data="'+row.id+'" class="rebind" href="javascript:void(0)" style="color:green">【解绑】</a>';
        		}
        		html += '<a class="lookqrcode" data="'+row.id+'" qrcodenum="'+row.number+'" href="javascript:void(0)">【查看】</a>';
        		return html;
        	} },
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
    
    //绑定商户
    $(document).on('click', '.bind', function(){
    	var id=$(this).attr("data");
    	var bingdata = [];
		layer.prompt({title: '请输入商户ID，并确认', formType: 0}, function(pass, index){	
			if(pass=="")
			{
				return;
			}
			bingdata[0] = id;
			bingdata[1] = pass;
			$.ajax({
				url:"../sys/qrcode/bind",
				type:'post',
				dataType:'json',
				data:JSON.stringify(bingdata),
				success:function(res){
					if(res.code == 0)
					{
						alert('操作成功', function(index){
							layer.closeAll();	
							vm.reload();
						});
					}else
					{
						alert(res.msg);
					}
				}
			})
		});
    });
    
    //解除绑定商户
    $(document).on('click', '.rebind', function(){
    	var qrcodeid=$(this).attr("data");
    	var rebingdata = [];
    	rebingdata[0] = qrcodeid;
    	layer.confirm('是否确定解除绑定？', {
    		  btn: ['确定','取消'],
    		  title:'温馨提示',
    		}, function(){
    			$.ajax({
    				url:"../sys/qrcode/rebind",
    				type:'post',
    				dataType:'json',
    				data:JSON.stringify(rebingdata),
    				success:function(res){
    					if(res.code == 0)
    					{
    						alert('操作成功', function(index){
    							layer.closeAll();	
    							vm.reload();
    						});
    					}else
    					{
    						alert(res.msg);
    					}
    				}
    			})
    		}, function(){
    		  layer.close();
    		});
    });
    
    //查看二维码
    $(document).on('click', '.lookqrcode', function(){
    	var qrcodeid=$(this).attr("data");
    	var qrcodenum = $(this).attr("qrcodenum");
    	var lookdata = [];
    	lookdata[0] = qrcodeid;
    	$.ajax({
			url:"../sys/qrcode/lookqrcode",
			type:'post',
			dataType:'json',
			data:JSON.stringify(lookdata),
			success:function(res){
				if(res.code== 0)
				{
					var html = "<img src='"+res.url+"' alt='' />";
					layer.open({
						  type: 1,
						  title : '二维码编号：'+qrcodenum,
						  skin: 'layui-layer-rim', //加上边框
						  area: ['314px', '360px'], //宽高
						  content: html
						});
				}else
				{
					alert(res.msg);
				}
			}
		})
    });
    
});

var vm = new Vue({
	el:'#qrcodeApp',
	data:{
		q:{
			key:"",
			searchBy: ""
		},
		showList: true,
		title: null,
		qrcode: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
		/*	vm.showList = false;
			vm.title = "新增";
			vm.qrcode = {};*/
			layer.prompt({title: '输入生成二维码个数（最大为10），并确认', formType: 0}, function(pass, index){	
				if(pass=="")
				{
					layer.msg("二维码个数不能为空！");return;
				}else if(isNaN(pass))
				{
					layer.msg("请输入数字！");return;
				}else if(pass>10)
				{
					layer.msg("二维码个数不能大于10！");return;
				}else{
					$.ajax({
						url:"../sys/qrcode/save",
						type:'post',
						dataType:'json',
						data:pass,
						success:function(res){
							if(res.code == 0)
							{
								alert('操作成功', function(index){
									layer.closeAll();	
									vm.reload();
								});
							}else
							{
								alert(r.msg);
							}
						}
					})
				}
		});
		},
		update: function () {
			var id = getSelectedRow();
			
			if(id == null){
				return ;
			}
			
			$.get("../sys/qrcode/info/"+id, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.qrcode = r.qrcode;
            });
		},
		del: function (event) {
			var ids = getSelectedRows();
			console.log(ids);return;
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/qrcode/delete",
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
			var url = vm.qrcode.id == null ? "../sys/qrcode/save" : "../sys/qrcode/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.qrcode),
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

