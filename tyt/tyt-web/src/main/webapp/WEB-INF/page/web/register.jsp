<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<meta charset="utf-8">
<%@ include file="/WEB-INF/page/web/include/head.jsp"%>
<title>影盾</title>
<script type="text/javascript">
$(function(){ 
	
	var msg = '${msg}';
	if(msg!=''){
		alert(msg);
	}
	
	$("#getCodeId").click(function(){
		var username = $("#usernameId").val();
		if(username==null||username==''){
			alert("账号不能为空");
			return;
		}
		$.ajax({
		    url:'${rc.contextPath}/web/user/getCode',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{
		        "username":username,"type":'0'
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		        console.log(xhr)
		        console.log('发送前')
		    },
		    success:function(data,textStatus,jqXHR){
		        console.log(data)
		        console.log(textStatus)
		        console.log(jqXHR)
		        if(data.status=='success'){
		        	alert(data.msg);
		        	var count = 120;
	                var countdown = setInterval(CountDown, 1000);
	                // 设置按钮倒计时函数
	                function CountDown() {
	                  $("#getCodeId").html(count);
	                  $("#getCodeId").css("pointer-events","none");
	                  // 设置按钮为不可点击
	                  if (count == 0) {
	                    // 倒计时结束后，设置按钮为可点击
	                    clickFlag = true;
	                    $("#getCodeId").removeAttr("style");
	                    $("#getCodeId").html("获取验证码");
	                    clearInterval(countdown);
	                  }
	                  count--;
	                }
		        }else{
		        	alert(data.msg);
		        }
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		})
	})
	
	$(".reg-button").click(function(){
		var pass = $("#passId").val();
		var rePass = $("#rePassId").val();
		var username = $("#usernameId").val();
// 		if (-1 == username.indexOf("@")){
// 			alert("请输入正确的邮箱");
// 			return;
// 		}
		if(pass==null||pass==''){
			alert("密码不能为空");
			return;
		}
		if(rePass==null||rePass==''){
			alert("确认密码不能为空");
			return;
		}
		if(pass!=rePass){
			alert("两次密码不一致");
			return;
		}
		
		$.ajax({
		    url:'${rc.contextPath}/web/user/checkIp',
		    type:'POST', //GET
		    async:true,    //或false,是否异步
		    data:{},
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    beforeSend:function(xhr){
		        console.log(xhr)
		        console.log('发送前')
		    },
		    success:function(data,textStatus,jqXHR){
		        if(data.status==true){
		        	
		        }else{
		        	alert(data.msg);
		        }
		        $("#formId").submit();
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		})
		
	})
});
</script>
</head>

<body>
<%@ include file="/WEB-INF/page/web/include/tab.jsp"%>

<form id="formId" action="${rc.contextPath}/web/user/registerSave" method="post">
<div class="logoinbox verticalCenter">
	<div class="logoin-title"><h2>用户注册</h2></div>
	<div class="logoin-title"><h2 style="color:red;">注册登陆即送VIP时长</h2></div>
   	<ul>
           <li><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/icon01.png"><input type="text" name="username" id="usernameId" placeholder="邮箱"></li>
           <li><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/icon02.png"><input type="password" id="passId" name="password" placeholder="密码"></li>
           <li><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/icon02.png"><input type="password" id="rePassId" placeholder="再次输入密码"></li>
           <!-- <li class="ide-code"><input type="text" name="code" placeholder="请输入验证码"><p id="getCodeId">获取验证码</p></li> -->
           <li class="reg-button"><img src="${rc.contextPath}/statics/web/images/register02.png"></li>
    </ul>
    <!-- <div class="logoin-title"><h2 style="color:green;font-size:12px;">部分网易邮箱（163，126等）出现收不到验证码邮件，若收不到邮件，推荐大家使用QQ邮箱</h2></div> -->
</div>
</form>

<!--版权-->
<%@ include file="/WEB-INF/page/web/include/footer2.jsp"%>
</div>
</body>
</html>
