<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/WEB-INF/page/web/include/head.jsp"%>
<title>支付</title>
<script type="text/javascript">
	$(function(){ 
		
		var msg = '${msg}';
		if(msg!=''){
			alert(msg);
		}
		
	});
</script>
</head>

<body style="background:#f7f7f7;">
<div class="toplogo">
	<div class="logo-img">
		<img src="${rc.contextPath}/statics/web/images/logo.png">
    </div>
</div>
<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1023468189&site=qq&menu=yes"><div class="consults"><img src="${rc.contextPath}/statics/web/images/zixun.png"></div></a>

<div class="orderbox">
	<div class="orderbox-title"><h2>订单详情</h2></div>
    <div class="account-number">
    	<h2>充值账户：<span>${sessionScope.user.username}</span></h2>
    	<h3>应付金额：<span>￥${price }</span></h3>
        <div class="clearfix"></div>
    </div>
</div>
    <div class="order-content" style="text-align:center;">
    	
    	<c:choose>  
           <c:when test="${!empty filePath }">
           		<img alt="wechat" style="width:300px;" src="${rc.contextPath}${filePath}">
		   </c:when>  
           
		   <c:otherwise>
        		${errorInfo }
		   </c:otherwise>
		   
		</c:choose>
    	
    	</br>
    	<span>请打开微信扫一扫</span></br>
    </div>

</body>
</html>
