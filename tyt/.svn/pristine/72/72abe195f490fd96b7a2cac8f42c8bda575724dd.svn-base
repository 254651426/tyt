<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/WEB-INF/page/web/include/head.jsp"%>
<style>
* {
	margin: 0;
	padding: 0;
}
.main {
	width: 1100px;
	height: 640px;
	margin: 20px auto;
	background-color: rgb(250, 250, 250);
	border: 1px solid rgb(204, 204, 204);
}
.main .left {
	width: 260px;
	height: 640px;
	padding-top: 15px;
	box-sizing: border-box;
	border-right: 1px solid rgb(204, 204, 204);
	float: left;
	
}
.main .left li {
	list-style: none;
	height: 50px;
	line-height: 50px;
	padding: 0 50px;
	box-sizing: border-box;
	
}
.main .left a {
	text-decoration: none;
	color: rgb(26, 26, 26);
}
.main .left li:hover {
	background-color: rgb(35, 176, 245);
}

.selectItem{
	background-color: rgb(35, 176, 245);
}

.main .left li:hover a {
	color: #fff;
}

.main .right {
	width: 840px;
	height: 640px;
	padding: 15px 25px 15px 25px;
	box-sizing: border-box;
	float: right;
	overflow:auto;
}
.main .right h3 {
	height: 50px;
	line-height: 50px;
	font-size: 18px;
	font-weight: 700;
	color: rgb(35, 176, 245);
	border-bottom: 1px solid rgb(204, 204, 204);
}
.main .right ul {
	height: 310px;
	margin-top: 10px;
	list-style: none;
}
.main .right li {
	height: 40px;
	line-height: 40px;
	text-indent: 1em;
	border-bottom: 1px dashed rgb(204, 204, 204);
} 
.main .right a {
	text-decoration: none;
	font-size: 12px;
	color: rgb(26, 26, 26);
}
.main .right a:hover {
	text-decoration: underline;
	color: rgb(35, 176, 245);
}
</style>
<title>影盾</title>
</head>
<script src="${rc.contextPath}/statics/libs/vue.min.js"></script>
<script src="${rc.contextPath}/statics/libs/vue-filter.js"></script>
<script type="text/javascript">
$(function(){ 
	
	var vm = new Vue({
		el:'#questionDivId',
		data:{
			headTitle:"公告",
			listShow:true,
			content:'',
			showArray:[
           		true,
           		false,
           		false,
           		false,
           		false,
           		false,
           		false
			],
			articleList:[]
		},
		methods: {
		
			getArticleList:function(index,type,title){
				// 清空选中
				vm.showArray=[false,false,false,false,false,false,false];
				// 设置选中
				vm.showArray[index]=true;
				// 显示列表
				vm.listShow=true;
				// 设置标题
				vm.headTitle=title;
				$.ajax({
				    url:'${rc.contextPath}/web/question/getArticleList',
				    type:'POST', //GET
				    async:true,    //或false,是否异步
				    data:{
				    	type:type
				    },
				    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
				    success:function(data,textStatus,jqXHR){
				        vm.articleList = data.data;
				    },
				    error:function(xhr,textStatus){
				        console.log('错误')
				        console.log(xhr)
				        console.log(textStatus)
				    }
				})
			},
			titleClick:function(id,content){
				vm.listShow=false;
				vm.content=content;
			}
			
		}
	});
	
	vm.getArticleList(0,'gonggao','公告');
	var h = $(window).height()-82-80-20-20-2;
	$(".main").css("height",h);
	$(".main .left").css("height",h);
	$(".main .right").css("height",h);
	
});
</script>
<body>
<%@ include file="/WEB-INF/page/web/include/tab.jsp"%>
    
<!--产品功能-->

<div class="main" id="questionDivId">
	<div class="left">
		<ul>
			<li :class="{'selectItem':showArray[0]}" @click="getArticleList(0,'gonggao','公告')" ><a href="#">公告</a></li>
			<li :class="{'selectItem':showArray[1]}" @click="getArticleList(1,'windows','Windows客户端')"  ><a href="#">Windows客户端</a></li>
			<li :class="{'selectItem':showArray[2]}" @click="getArticleList(2,'mac','Mac客户端')"><a href="#">Mac客户端</a></li>
			<li :class="{'selectItem':showArray[3]}" @click="getArticleList(3,'linux','Linux客户端')"><a href="#">Linux客户端</a></li>
			<li :class="{'selectItem':showArray[4]}" @click="getArticleList(4,'android','安卓App')"><a href="#">安卓App</a></li>
			<li :class="{'selectItem':showArray[5]}" @click="getArticleList(5,'ios','苹果App')"><a href="#">苹果App</a></li>
			<li :class="{'selectItem':showArray[6]}" @click="getArticleList(6,'plugin','浏览器插件')"><a href="#">浏览器插件</a></li>
		</ul>
	</div>
	<div class="right" v-if="listShow" >
		<h3>{{headTitle}}</h3>
		<ul>
			<li v-for=" (title,$index) in articleList " @click="titleClick(title.id,title.content)" ><a href="#">{{title.title}}</a></li>
		</ul>
	</div>
	
	<div class="right" v-cloak v-if="!listShow" v-html="content" >
		
	</div>
	
</div>

<!--版权-->
<%@ include file="/WEB-INF/page/web/include/footer.jsp"%>
</div>
</body>
</html>
