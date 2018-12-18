<!DOCTYPE html>
<html>
<head>
<title>${clazzinfo.objname}</title>
<${r'#'}include "header.html" />
</head>
<body>
<div id="${clazzinfo.tableas}App" v-cloak>
	<div v-show="showList">
		<div class="grid-btn">
			<div class="form-group col-sm-2">
				<select v-model="q.searchBy" class="form-control">
					<option value="">请选择查询项</option>
					<#list attributes as item>  
					<option value="${item.name}">${item.desc}</option>
					</#list>
				</select>
			</div>
			<div class="form-group col-sm-2">
				<input type="text" class="form-control" v-model="q.keyword" @keyup.enter="query" placeholder="请输入查询值">
			</div>
			<a class="btn btn-default" @click="query">查询</a>
			<${r'@'}shiroPermission role="sys:${clazzinfo.tableas}:save" >
			<a class="btn btn-primary" @click="add"><i class="fa fa-plus"></i>&nbsp;新增</a>
			</${r'@'}shiroPermission>
			<${r'@'}shiroPermission role="sys:${clazzinfo.tableas}:update" >
			<a class="btn btn-primary" @click="update"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
			</${r'@'}shiroPermission>
			<${r'@'}shiroPermission role="sys:${clazzinfo.tableas}:delete" >
			<a class="btn btn-primary" @click="del"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
			</${r'@'}shiroPermission>
		</div>
	    <table class="qrcode-box">
			<thead>
				<tr>
					<th>
						<input type="checkbox" class="fan-checkbox" v-model="checkAll" @click="checkedAll()">
					</th>
					<#list attributes as item>
		        	<#if item.name=='id'>
		        	<#else>
			        <th>${(item.desc)!}</th>
					</#if>
					</#list>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="item in params">
					<td><input type="checkbox" :value="item.id" class="fan-checkbox" v-model='ischeckdate' /></td>
					<#list attributes as item>
			        	<#if item.name=='id'>
			        	<#else>
			        	<td>{{ item.${(item.javaName)!} }}</td>
						</#if>
					</#list>
					<td>
		        		<a class="btn btn-primary" href="javascript:void(0)" @click="update(item.id)" >修改</a>
		        		<a class="btn btn-danger" href="javascript:void(0)" @click="del(item.id)" >删除</a>
		        	</td>
				</tr>
			</tbody>
		</table>
	    <uib-pagination :total-items="totalItems" style="padding-left:42% ;float: left;"  v-model="pagination" :max-size="maxSize" :items-per-page="pageSize" class="pagination-sm" :boundary-link-numbers="false" :boundary-links="true" previous-text="上一页" next-text="下一页" first-text="首页" last-text="尾页" :rotate="false" @change="pageChanged" > </uib-pagination>
	    <div class="page-div" >{{pagination.currentPage}}-{{pagination.numPages}}    共{{totalItems}}条</div>
    	<select class="page-select" v-model="pageSize"  @change = "pageNum">
        	<option value="10">10</option> 
        	<option value="20">20</option>
        	<option value="30">30</option>
        </select>
    </div>
     
   	<div v-show="!showList" class="panel panel-default">
		<div class="panel-heading">{{title}}</div>
		<form class="form-horizontal">
			<#list attributes as item>
			<#if item.name!='id'&&item.name!='create_date'&&item.name!='modify_date'>
			<#if 'Boolean'==item.type >
			<div class="form-group">
				<div class="col-sm-2 control-label">${(item.desc)!}</div> 
				<label class="radio-inline">
				  <input type="radio" name="status" value="true" v-model="${clazzinfo.tableas}.${(item.javaName)!}"/> 是
				</label>
				<label class="radio-inline">
				  <input type="radio" name="status" value="false" v-model="${clazzinfo.tableas}.${(item.javaName)!}"/> 否
				</label>
			</div>
			<#else>
			<div class="form-group">
			   	<div class="col-sm-2 control-label">${(item.desc)!}</div>
			   	<div class="col-sm-10">
			      <input type="text" class="form-control" v-model="${clazzinfo.tableas}.${(item.javaName)!}" placeholder="${(item.javaName)!}"/>
			    </div>
			</div>
			</#if>
			</#if>  
			</#list>
			<div class="form-group">
				<div class="col-sm-2 control-label"></div> 
				<input type="button" class="btn btn-primary" @click="saveOrUpdate" value="确定"/>
				&nbsp;&nbsp;<input type="button" class="btn btn-warning" @click="reload" value="返回"/>
			</div>
		</form>
	</div>
</div>

<script src="${r'$'}{rc.contextPath}/js/sys/${clazzinfo.tableas}.js?_${r'$'}{.now?long}"></script>
</body>
</html>