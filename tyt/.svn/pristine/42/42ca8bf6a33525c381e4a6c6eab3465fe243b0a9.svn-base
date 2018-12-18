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
		
		$("#loginId").click(function(){
			$("#passwordId").val(md5($("#passwordId").val()));
			$("#formId").submit();
		})
	});
</script>
</head>

<body>
<%@ include file="/WEB-INF/page/web/include/tab.jsp"%>
<form id="formId" action="${rc.contextPath}/web/user/login" method="post">
 <div class="verticalCenter logoinbox">
   	<div class="logoin-title"><h2>用户登录</h2></div>
   	<ul>
	    <li><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/icon01.png"><input type="text" name="username"  placeholder="邮箱" value="${username}"></li>
	    <li><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/icon02.png"><input type="password" id="passwordId" name="password" placeholder="密码"></li>
	    <li class="reg-button" id="loginId"><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/logoin01.png"></li>
	    <a href="${rc.contextPath}/web/user/forget"><li class="prompt-item"><span class="forget">忘记密码</span></li></a>
   	</ul>
</div>
</form>

<!--版权-->
<%@ include file="/WEB-INF/page/web/include/footer2.jsp"%>
</div>
</body>
</html>
