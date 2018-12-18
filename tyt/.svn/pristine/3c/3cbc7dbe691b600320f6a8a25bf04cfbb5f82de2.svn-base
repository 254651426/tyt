var vm = new Vue({
	el:'#mainApp',
	data:{
		q:{
			username: null
		},
		showList: true,
		title:null,
		dayPay:null,
		monthPay:null,
		yearPay:null,
		newUser:null,
		roleList:{},
		user:{
			status:1,
			roleIdList:[]
		},
		userList:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {status:1,roleIdList:[]};
			
			//获取角色信息
			this.getRoleList();
		},
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//获取角色信息
			this.getRoleList();
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sys/user/delete",
				    data: JSON.stringify(userIds),
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
			var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.user),
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
		getUser: function(userId){
			$.get("../sys/user/info/"+userId, function(r){
				vm.user = r.user;
			});
		},
		getRoleList: function(){
			$.get("../sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
		reload: function (event) {
			vm.showList = true;
		}
	}
});

$().ready(function() {
	
	$.ajax({
		type: "POST",
	    url: "../sys/orders/statistics",
	    data: {
	    	
	    },
	    contentType : "application/x-www-form-urlencoded",
		dataType:'json',
	    success: function(r){
	    	console.log(r);
	    	vm.dayPay=r.dayPay;
	    	vm.monthPay=r.monthPay;
	    	vm.yearPay=r.yearPay;
	    	vm.newUser=r.newUser;
	    	vm.userList=r.userList;
		}
	});
	
})