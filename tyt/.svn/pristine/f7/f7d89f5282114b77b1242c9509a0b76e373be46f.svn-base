// 通过url直接传参的方式调用ajax处理
var ajaxPostByUrlParam = function(url,beforeCall,successCall){
	$.ajax({
		url: url,
		type: "POST",
		dataType: "json",
		cache: false,
		beforeSend: function() {
			// 若请求前调用的函数不为空，则执行该步骤
			if(beforeCall!=null){
				beforeCall();
			}
		},
		success: function(data) {
			// 若成功处理数据，则执行对应的成功处理调用的函数
			successCall(data);
		},
		error: function(){
			alert("系统出现异常,请与管理员联系!");
		}
	});	
};

// 通过form表单提交的方式调用ajax处理
var ajaxPostByForm = function(url,form,beforeCall,successCall){
	$.ajax({
		url: url,
		type: "POST",
		data: form.serialize(),
		dataType: "json",
		cache: false,
		beforeSend: function() {
			// 若请求前调用的函数不为空，则执行该步骤
			if(beforeCall!=null){
				beforeCall();
			}
		},
		success: function(data) {
			// 若成功处理数据，则执行对应的成功处理调用的函数
			successCall(data);
		},
		error: function(){
			alert("系统出现异常,请与管理员联系!");
		}
	});	
};

//通过url直接传参的方式调用ajax处理
var ajaxGetByUrlParam = function(url,beforeCall,successCall){
	$.ajax({
		url: url,
		type: "GET",
		dataType: "json",
		cache: false,
		beforeSend: function() {
			// 若请求前调用的函数不为空，则执行该步骤
			if(beforeCall!=null){
				beforeCall();
			}
		},
		success: function(data) {
			// 若成功处理数据，则执行对应的成功处理调用的函数
			successCall(data);
		},
		error: function(){
			alert("系统出现异常,请与管理员联系!");
		}
	});	
};

// 通过form表单提交的方式调用ajax处理
var ajaxGetByForm = function(url,form,beforeCall,successCall){
	$.ajax({
		url: url,
		type: "GET",
		data: form.serialize(),
		dataType: "json",
		cache: false,
		beforeSend: function() {
			// 若请求前调用的函数不为空，则执行该步骤
			if(beforeCall!=null){
				beforeCall();
			}
		},
		success: function(data) {
			// 若成功处理数据，则执行对应的成功处理调用的函数
			successCall(data);
		},
		error: function(){
			alert("系统出现异常,请与管理员联系!");
		}
	});	
};