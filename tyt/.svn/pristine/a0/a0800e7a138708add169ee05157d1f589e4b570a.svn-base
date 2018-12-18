<%@ page contentType="text/html;charset=UTF-8" %>
<div class="top">
	<div class="nav">
		<a href="${rc.contextPath}/web/index/index"><div class="logo"><img src="${rc.contextPath}/statics/web/images/logo.png"></div></a>
		<div class="menu">
			<ul>
				<a href="${rc.contextPath}/web/index/index"><li class="homepage<c:if test="${menu=='index'}"> cur</c:if>">首页</li></a>
			    <a href="${rc.contextPath}/web/index/product"><li <c:if test="${menu=='product'}">class="cur"</c:if>>套餐价格</li></a>
			    <a href="${rc.contextPath}/web/index/download"><li class="download<c:if test="${menu=='download'}"> cur</c:if>">客户端下载</li></a>
			    <a href="${rc.contextPath}/web/question/index"><li <c:if test="${menu=='question'}">class="cur"</c:if>>帮助中心</li></a>
			    <a href="${rc.contextPath}/web/user/user"><li <c:if test="${menu=='user'}">class="cur"</c:if>>个人中心</li></a>
			</ul>
			
			<c:choose>
   				<c:when test="${empty sessionScope.user.username}">
   					<div class="logoin"><a href="${rc.contextPath}/web/user/goLogin"><h2>登录</h2></a><a href="${rc.contextPath}/web/user/register"><h3>注册</h3></a></div>
   				</c:when>
   
   				<c:otherwise> 
   					<div class="secede"><h2>${fn:substring(sessionScope.user.username,0,18)}</h2><h3 id="logoutId">退出</h3></div>
   				</c:otherwise>
			</c:choose>
			
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=2390933530&site=qq&menu=yes"><div class="consults"><img src="${rc.contextPath}/statics/web/images/zixun.png"></div></a>
<script type="text/javascript">
	$(function(){ 
		$("#logoutId").on("click",function(){
			window.location.href="${rc.contextPath}/web/user/logout";
		})
	});
</script>