<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${rc.contextPath}/statics/css/bootstrap.min.css">
<%@ include file="/WEB-INF/page/web/include/head.jsp"%>
<title>影盾</title>
<script src="${rc.contextPath}/statics/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<script src="${rc.contextPath}/statics/libs/vue.min.js"></script>
<script src="${rc.contextPath}/statics/libs/vue-filter.js"></script>
<script src="${rc.contextPath}/statics/libs/vuejs-uib-pagination.js"></script>

<script type="text/javascript">
$(function(){ 
	
	var vm = new Vue({
		el:'#tytUserAppId',
		data:{
			dateTime:"aa",
			params:[
			        
		    ],
		    sendRate:${sendRate},
		    sysDomainList:[],
		    userDomainList:[],
		    leaveMessageList:[],
		    commissionList:[],
			totalItems : 0,
			pageSize : 10,
			pagination : {
				currentPage : 1
			},
			
			commissionTotalItems : 0,
			commissionPageSize : 10,
			commissionPagination : {
				currentPage : 1
			},
			
			
   			sunSendMoney:0,
   			sunPreSendMoney:0,
   			todayCommission:0,
   			preCommission:0,
			
			maxSize : 0,
			q:{
				key:"",
				searchBy: ""
			},
			title: null,
			userId:null,
			userLevel:null,
			userDay:null,
			tytUser: {}
		},
		methods: {
			
			pageChanged : function() {
				vm.paging(false);
			},
			
			commissionPageChanged : function() {
				vm.commissionPaging(false);
			},
			ajaxUserDomain:function(){
				
				$.ajax({
				    url:'${rc.contextPath}/web/user/ajaxUserDomain',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data:{
				    	
				    },
				    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
				    success:function(data,textStatus,jqXHR){
				        vm.sysDomainList = data.list2;
				        vm.userDomainList = data.list1;
				    },
				    error:function(xhr,textStatus){
				        console.log('错误')
				        console.log(xhr)
				        console.log(textStatus)
				    }
				})
			},
			ajaxLeaveMessage:function(){
				
				$.ajax({
				    url:'${rc.contextPath}/web/user/ajaxLeaveMessage',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data:{
				    	
				    },
				    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
				    success:function(data,textStatus,jqXHR){
				    	vm.leaveMessageList = data.leaveMessageList;
				        //console.log(data);
				    },
				    error:function(xhr,textStatus){
				        console.log('错误')
				        console.log(xhr)
				        console.log(textStatus)
				    }
				})
			},
			paging:function(syc) {
				console.log("进来了");
				var pager = {
						"pageNumber" : vm.pagination.currentPage,
						"pageSize" : vm.pageSize,
						"searchBy": vm.q.searchBy,
			        	"keyword": vm.q.keyword,
			        	"beginDate":$("#queryTimeBegin").val(),
			        	"endDate":$("#queryTimeEnd").val()
				}
				var index;
				$.ajax({
					type : "POST",
					url : "${rc.contextPath}/web/user/ajaxRegUser",
					contentType: "application/json; charset=utf-8",
					dataType : 'json',
					async : syc,
					data : JSON.stringify(pager),
					beforeSend : function(){
						
					},
					complete : function(data) { 
						
					}, 
					success : function(r) {
						console.log("*****");
						console.log(r);
						vm.pageSize = r.page.pageSize;
						vm.pagination.currentPage = r.page.pageNumber;
						vm.params = r.page.result;
						for(var i=0;i<vm.params.length;i++){
							var data;
							if(vm.params[i].merchants==null){
								var data = "";
							}else{
								var data = vm.params[i].merchants.receivablesUserName;
							}
							vm.params[i].receivablesUserName = data;
						}
						vm.totalItems = r.page.totalCount;
					}
				});
			},
			commissionPaging:function(syc) {
				console.log(666666666666);
				var pager = {
						"pageNumber" : vm.commissionPagination.currentPage,
						"pageSize" : vm.commissionPageSize,
						"searchBy": vm.q.searchBy,
			        	"keyword": vm.q.keyword,
			        	"beginDate":$("#queryTimeBegin").val(),
			        	"endDate":$("#queryTimeEnd").val()
				}
				var index;
				$.ajax({
					type : "POST",
					url : "${rc.contextPath}/web/user/ajaxCommission",
					contentType: "application/json; charset=utf-8",
					dataType : 'json',
					async : syc,
					data : JSON.stringify(pager),
					beforeSend : function(){
						
					},
					complete : function(data) { 
						
					}, 
					success : function(r) {
						
						vm.sunSendMoney=r.sunSendMoney;
						vm.sunPreSendMoney=r.sunPreSendMoney;
						vm.todayCommission=r.todayCommission;
						vm.preCommission=r.preCommission;
			   			
						vm.commissionPageSize = r.pager.pageSize;
						vm.commissionPagination.currentPage = r.pager.pageNumber;
						vm.commissionList = r.pager.result;
						vm.commissionTotalItems = r.pager.totalCount;
					}
				});
			}
		}
	});
	
	vm.ajaxLeaveMessage();
	vm.commissionPaging(true);
	
	var msg = '${msg}';
	if(msg!=''){
		alert(msg);
	}
	
	var module = '${module}';
	
	$(".imgClass").hide();
	$(".moduleDiv").hide();// 隐藏所有div
	if(module=='leaveMessage'){
		$(".imgClass").eq(4).show();
		$("#leaveMessageId").show();
	}else{
		$(".imgClass").eq(0).show();
		$("#phoneLineId").show();
	}
	
	// 第一个菜单选择显示
	$(".liClass").click(function(){
		
		$(".imgClass").hide();
		$(this).find(".imgClass").show();// 选中图标
		$(".moduleDiv").hide();// 隐藏所有div
		
		if($(this).index()==0){
			$("#phoneLineId").show();
		}else if($(this).index()==1){
			$("#iosId").show();
		}else if($(this).index()==2){
			$("#androidId").show();
		}else if($(this).index()==3){
			$("#domainListId").show();
		}else if($(this).index()==4){
			window.location.href="${rc.contextPath}/web/index/product";
		}else if($(this).index()==5){
			$("#leaveMessageId").show();
		}else if($(this).index()==6){
			$("#userHelpId").show();
		}
	})
	
	// 第二个菜单显示
	$(".quickenbox_2_liClass").click(function(){
		
		$(".imgClass").hide();
		$(this).find(".imgClass").show();// 选中图标
		$(".moduleDiv").hide();// 隐藏所有div
		
		if($(this).index()==0){
			$("#textId").show();
		}else if($(this).index()==1){
			$("#fanliId").show();
		}else if($(this).index()==2){
			$("#fromUserId").show();
		}
		
	})
	
	$("#submitId").click(function(){
		$("#messForm").submit();
	})
	
});
</script>
</head>

<body>
<%@ include file="/WEB-INF/page/web/include/tab.jsp"%>

<div class="personalcenter " id="tytUserAppId" >
	<div class="personal-left" >
    	<div class="personal-left-title"><p>影盾</p></div>
        <div class="">
        	<div class="per-information-title">
            	<img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" src="${rc.contextPath}/statics/web/images/pic01.png">
                <h2>
                	${sessionScope.user.username}<br>
                	<a href="${rc.contextPath}/web/index/download"><p>最新版下载</p></a>
                </h2>
            </div>
            
            <c:choose>  
               <c:when test="${sessionScope.user.level==1 or sessionScope.user.level==2 or sessionScope.user.level==3 or sessionScope.user.level==4 or sessionScope.user.level==5 or sessionScope.user.level==6}">
			   		<div class="open"><p>vip用户</p><span>vip截止时间：<fmt:formatDate value="${sessionScope.user.vipEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>    
			   </c:when>  
            
			   <c:otherwise>  
			   		<a href="${rc.contextPath}/web/index/product"><div class="open"><p>非vip</p><span>开通之后，即可上任意网站</span></div></a>
			   </c:otherwise>  
			</c:choose>
            
            
        </div>
        
        	<div class="quickenbox">
            	<ul>
            		<li class="liClass"><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>手机线路列表</p></li>
            		<li class="liClass"><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>Iphone上网教程</p></li>
            		<li class="liClass"><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>安卓上网教程</p></li>
                	<li class="liClass" @click="ajaxUserDomain" ><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>加速上网列表</p></li>
                    <li class="liClass"><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>充值VIP</p></li>
                    <li class="liClass" style="border:none;"><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>留言管理</p></li>
                </ul>
            </div>
            
            <div class="quickenbox">
            	<ul>
            		<li class="quickenbox_2_liClass" ><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>推广合作</p></li>
                	<li class="quickenbox_2_liClass" ><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>返利统计</p></li>
                	<li class="quickenbox_2_liClass" @click="pageChanged" style="border:none;"><p><img title="影盾官网，影盾，影盾软件,影盾插件,影盾加速器，影盾浏览器插件，影盾chrome浏览器插件,代理,windows代理，mac代理，代理软件,时光隧道,蓝灯(Lartrn),Green加速器,壁虎漫步,出锅。加速器插件,加速器，科学上网,Chrome插件，浏览器插件，chrome浏览器插件,科学上网,科学上网插件,加速器软件,访问Google、访问Facebook、访问YouTube,访问Twitter。" class="imgClass" src="${rc.contextPath}/statics/web/images/duigou2.png"/>注册用户</p></li>
                </ul>
            </div>
            
    </div>
  
    <!-- 手机配置页面 -->
    <div id="phoneLineId" class="personal-right moduleDiv">
    	<div class="personal-right-title">
       		<h2>线路节点列表</h2>
        </div>
        
        <c:choose>  
           <c:when test="${sessionScope.user.level==3 or sessionScope.user.level==4 or sessionScope.user.level==6}">
		   		<table style="width:100%;border:solid 1px #add9c0;border-collapse:collapse;text-align:center;">
		        	<tr style="border:solid 1px #add9c0;text-align:center;">
		        		<th style="width:10%;text-align:center;">地区</th>
		        		<th style="width:10%;text-align:center;">服务器</th>
		        		<th style="width:20%;text-align:center;">端口</th>
		        		<th style="width:20%;text-align:center;">加密方式</th>
		        		<th style="width:20%;text-align:center;">密码</th>
		        		<th style="width:20%;text-align:center;">二维码</th>
		        	</tr>
		        	<c:forEach items="${userLineList}" var="userLine">
		        		<tr style="border:solid 1px #add9c0;text-align:center;">
		        			<th style="vertical-align:middle;text-align:center;">${userLine.area }</th>
			        		<th style="vertical-align:middle;text-align:center;">${userLine.userIp }</th>
			        		<th style="vertical-align:middle;text-align:center;">${userMessage.shadowsocksPort }</th>
			        		<th style="vertical-align:middle;text-align:center;">aes-256-cfb</th>
			        		<th style="vertical-align:middle;text-align:center;">${userMessage.shadowsocksPassword }</th>
			        		<th style="vertical-align:middle;text-align:center;"><img width="100px" src="${rc.contextPath}${userLine.imgUrl}" /></th>
			        	</tr>
		        	</c:forEach>
		        </table>
		   </c:when>  
		     
		   <c:otherwise>  
		   		<br />
		   		<div>
		   			<p style="color:red;">
		       		            您当前为：<span style="font-size:28px;">
		       		            <c:choose>  
						           <c:when test="${sessionScope.user.level==0}">
								   		免费用户
								   </c:when> 
								   <c:when test="${sessionScope.user.level==1}">
								   		月费会员
								   </c:when>
								   <c:when test="${sessionScope.user.level==5}">
								   		体验用户
								   </c:when>
							    </c:choose>
							    ,无法使用手机上网服务。
							   </span>
					</p>
					<br />
		       		<p style="color:red;">
		       			<span style="font-size:28px;">请升级为：半年，年费或者两年套餐会员</span>
					</p>
					<br />
					<a href="${rc.contextPath}/web/index/product"><span style="font-size:28px;color:green;">点击升级VIP</span></a>
		        </div>
		   </c:otherwise>  
		</c:choose>
        
    </div>
    
    <!-- 苹果教程页面 -->
    <div id="iosId" class="personal-right moduleDiv">
      <div class="personal-right-title">
       		<h2>Iphone上网教程</h2>
       		</br>
   		    <a href="https://itunes.apple.com/us/app/wingy-http-s-socks5-proxy-utility/id1178584911?mt=8" class="button button-caution button-pill button-jumbo">点击下载Iphone客户端</a>
   		    </br></br></br>
      </div>
        
      <span style="color:red;font-size:30px;">1.打开软件,点击左上角的+</span>
		   		</br>
		   		<img width="300px" src="${rc.contextPath}/statics/web/images/ios_1.PNG">
		   		</br>
		   		</br><span style="color:red;font-size:30px;">2.点击扫描二维码</span></br>
		   		<img width="300px" src="${rc.contextPath}/statics/web/images/ios_2.PNG">
		   		</br>
		   		</br><span style="color:red;font-size:30px;">3.用摄像头扫描下面的二维码</span></br>
		   		<img width="300px" src="${rc.contextPath}/statics/web/images/ios_3.PNG">
		   		</br>
		   		<img src="${rc.contextPath}${shaFilePath}" />
		   		</br>
		   		</br><span style="color:red;font-size:30px;">4.点击加载出来的配置</span></br>
		   		<img width="300px" src="${rc.contextPath}/statics/web/images/ios_4.PNG">
		   		</br>
		   		</br><span style="color:red;font-size:30px;">5.点击Allow，并进行指纹验证</span></br>
		   		<img width="300px" src="${rc.contextPath}/statics/web/images/ios_5.PNG">
		   		</br>
		   		</br><span style="color:red;font-size:30px;">6.回到最开始的界面点击启动按钮</span></br>
		   		<img width="300px" src="${rc.contextPath}/statics/web/images/ios_6.PNG">
		   		
        
    </div>
    
    <!-- 安卓教程页面 -->
    <div id="androidId" class="personal-right moduleDiv">
    	<div class="personal-right-title">
       		<h2>安卓上网教程</h2>
        </div>
        
        <div>
        	</br>
	   		<a href="http://123.56.204.25/client/android.apk" class="button button-caution button-pill button-jumbo">点击下载安卓客户端</a>
	   		</br></br></br>
	   		
	   		<span style="color:red;font-size:30px;">1.打开软件,点击左上角的菜单</span>
	   		</br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_1.png">
	   		</br>
	   		</br><span style="color:red;font-size:30px;">2.点击添加配置文件</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_2.png">
	   		</br>
	   		</br><span style="color:red;font-size:30px;">3.点击扫描二维码</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_3.png">
	   		</br>
	   		</br><span style="color:red;font-size:30px;">4.点击yes安装二维码扫描器</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_4.png">
	   		</br>
	   		</br><span style="color:red;font-size:30px;">5.选择一个应用商店，跳转安装扫码器</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_5.png">
	   		</br>
	   		</br><span style="color:red;font-size:30px;">6.重新点击扫描二维码，扫描下面的二维码</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_6.png">
	   		</br>
	   		<img src="${rc.contextPath}${shaFilePath}" />
	   		</br>
	   		</br><span style="color:red;font-size:30px;">7.扫描完成之后，配置信息会自动加载到软件中</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_7.png">
	   		</br>
	   		</br><span style="color:red;font-size:30px;">8.点击右上角的开关。至此所有的配置完成</span></br>
	   		<img width="300px" src="${rc.contextPath}/statics/web/images/android_8.png">
        </div>
        
    </div>
    
    <!-- 加速上网列表的div -->
    <div id="domainListId" class="personal-right moduleDiv">
    	<div class="personal-right-title">
       		<h2>加速上网列表</h2>
        </div>
        
       <div class="personalP">
       		<p>智能模式：下面科学列表中的网站进行代理访问<br>
       		   全局模式：所有的网站均进行代理访问。<br>
       		   ps：全局模式会导致访问国内的网站较慢。建议使用智能模式，可在软件中将您要代理的网站加入到科学列表中
			</p>
        </div>
        <!-- 
        <div class="addName">
        	<div><input type="text" value="请输入域名，按回车添加"><h2>添加域名</h2><div class="clearfix"></div></div>
            <p>在访问下面的域名时，影盾会按需开启上网加速访问模式，自动防止无法访问的情况发生</p>
        </div>
         -->
         
        <div class="add-name">
	        <p>我的加速列表</p>
	        <div class="add-namebox">
	        	<li></li>
	        	<a v-for="domain in userDomainList" class="domainClass" target="_blank" href="http://www.{{domain.domain }}">{{domain.domain }}<span>X</span></a>
	            <div class="clearfix"></div>
	        </div>
        </div>
        
        <div class="realmName">
        	<!--修改代码-->
	        <p>默认加速列表</p>
	        <div  class="realmName-list">
	        <a class="domainClass" v-for="domain in sysDomainList" target="_blank" href="http://www.{{omain.domain }}">{{domain.domain }}</a>
	        <div class="clearfix"></div>
        </div>
        </div>
    </div>
    
    <!-- 留言的div -->
    <div id="leaveMessageId" class="personal-right2 moduleDiv">
    	<div class="personal-right-title2">
       		<h2>我的留言</h2>
        </div>
	    <div class="consultbox">
	    	
	    		<div v-for="mess in leaveMessageList" class="consult-box">
		        	<div class="consult-title">
		        		<h2>我的提问：</h2>
		                <h3>{{mess.createTime | time }}</h3>
		                <div class="clearfix"></div>
		            </div>
		            <div class="consult-content">
		            	<p>{{mess.info}}</p>
		            </div>
	            	<div v-if="mess.back_info" class="consult-reply">
		            	<p><span>管理员：</span>{{mess.back_info }}</p>
		            </div>
		        </div>
	    	
	    </div>
    
    	<form id="messForm" action="${rc.contextPath}/web/leaveMessage/insertMessage" method="post">
		    <div class="advisory-inputbox">
		        <h2>我要留言</h2>
		    	<textarea name="info" class="inputbox" rows="4"></textarea>
		        <p id="submitId">提交</p>
		    </div>
	    </form>
    </div>
    
    <div id="textId" class="personal-right moduleDiv">
    	<div class="personal-right-title">
       		<h2><span id="commissionTime">${dateTime }</span>返利规则</h2>
        </div>
        
        <div class="personalP">
       		<p style="color:black;">
       			我的推广链接：http://www.tianyantong.net.cn/tyt/web/user/register?code=${user.shareCode }
			</p>
        </div>
        
       <table style="width:100%;border:solid 1px #add9c0;border-collapse:collapse;text-align:center;">
        	<tr style="border:solid 1px #add9c0;text-align:center;">
        		<th style="width:30%;text-align:center;">套餐</th>
        		<th style="width:30%;text-align:center;">返利比例</th>
        		<th style="width:40%;text-align:center;">返利金额</th>
        	</tr>
        	<tr style="border:solid 1px #add9c0;text-align:center;">
        		<th style="width:30%;text-align:center;">月费套餐</th>
        		<th style="width:30%;text-align:center;">${sendRate }%</th>
        		<th style="width:40%;text-align:center;">${sendRate*18/100 }</th>
        	</tr>
        	<tr style="border:solid 1px #add9c0;text-align:center;">
        		<th style="width:30%;text-align:center;">半年套餐</th>
        		<th style="width:30%;text-align:center;">${sendRate }%</th>
        		<th style="width:40%;text-align:center;">${sendRate*88/100 }</th>
        	</tr>
        	<tr style="border:solid 1px #add9c0;text-align:center;">
        		<th style="width:30%;text-align:center;">年费套餐</th>
        		<th style="width:30%;text-align:center;">${sendRate }%</th>
        		<th style="width:40%;text-align:center;">${sendRate*168/100 }</th>
        	</tr>
        	<tr style="border:solid 1px #add9c0;text-align:center;">
        		<th style="width:30%;text-align:center;">两年套餐</th>
        		<th style="width:30%;text-align:center;">${sendRate }%</th>
        		<th style="width:40%;text-align:center;">${sendRate*298/100 }</th>
        	</tr>
        </table>
        
    </div>
    
    <div id="fanliId" class="personal-right moduleDiv">
    	<div class="personal-right-title">
       		<h2><span id="commissionTime">${dateTime }</span>返利信息统计</h2>
        </div>
       <div class="personalP">
       		<p>
       			今日返利：￥<span id="todayTotalId">{{todayCommission }}</span><br>
       			昨日返利：￥<span id="todayTotalId">{{preCommission }}</span><br>
       			本月返利：￥<span id="sendTotalId">{{sunSendMoney }}</span><br>
       			上月返利：￥<span id="sendTotalId">{{sunPreSendMoney }}</span><br>
       			
       			总充值个数：<span id="todayTotalId">{{commissionTotalItems }}</span><br>
       			第{{commissionPagination.currentPage}}/{{commissionPagination.numPages}}页 
			</p>
        </div>
        
        <table style="width:100%;border:solid 1px #add9c0;border-collapse:collapse;">
        	<tr style="border:solid 1px #add9c0;">
        		<th style="width:20%;">购买账户</th>
        		<th style="width:20%;">购买时间</th>
        		<th style="width:20%;">订单金额</th>
        		<th style="width:20%;">返利</th>
        		<th style="width:20%;">首充/续费</th>
        	</tr>
       		<tr v-for="commission in commissionList" style="border:solid 1px #add9c0;">
        		<th>{{commission.username }}</th>
        		<th>{{commission.createDate | time }}</th>
        		<th>￥{{commission.price }}</th>
        		<th>￥{{commission.price*sendRate/100 }}</th>
        		<th v-if="commission.isSend">续费</th><th v-else>首充</th>
        	</tr>
        </table>
        <uib-pagination :total-items="commissionTotalItems" style="padding-left:42% ;float: left;"  v-model="commissionPagination" :max-size="maxSize" :items-per-page="commissionPageSize" class="pagination-sm" :boundary-link-numbers="false" :boundary-links="true" previous-text="上一页" next-text="下一页" first-text="首页" last-text="尾页" :rotate="false" @change="commissionPageChanged" > </uib-pagination>
    </div>
    
    <div id="fromUserId" class="personal-right moduleDiv">
    	<div class="personal-right-title">
       		<h2>${dateTime }渠道注册用户</h2>
        </div>
        
       <div class="personalP">
       		<p>
       			今日注册人数：{{totalItems}}<br>
       			第{{pagination.currentPage}}/{{pagination.numPages}}页    共{{totalItems}}条
			</p>
			<!-- 
			查询时间：<input id="queryTimeBegin" type="text" readonly="readonly" maxlength="20" style="background-color:#add9c0;color:black;width:100px;height:24px;"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        		   ~<input id="queryTimeEnd" type="text" readonly="readonly" maxlength="20" style="background-color:#add9c0;color:black;width:100px;height:24px;"
							 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
        			<input @click="query" type="button" value="查询" style="width:30px;height:24px;" />
        	 -->
        </div>
        
        <table style="width:100%;border:solid 1px #add9c0;border-collapse:collapse;">
        	<tr style="border:solid 1px #add9c0;">
        		<th style="width:50%;">用户名</th>
        		<th style="width:50%;">注册时间</th>
        	</tr>
        	<tr style="border:solid 1px #add9c0;" v-for="item in params">
	        	<td>{{ item.username }}</td>
	        	<td>{{ item.createDate | time }}</td>
			</tr>
        </table>
        <uib-pagination :total-items="totalItems" style="padding-left:42% ;float: left;"  v-model="pagination" :max-size="maxSize" :items-per-page="pageSize" class="pagination-sm" :boundary-link-numbers="false" :boundary-links="true" previous-text="上一页" next-text="下一页" first-text="首页" last-text="尾页" :rotate="false" @change="pageChanged" > </uib-pagination>
    </div>
    		
</div>

</div>
</body>
</html>
