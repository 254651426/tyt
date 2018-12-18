<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${clazzinfo.objname}列表</title>
<link href="${r'$'}{base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${r'$'}{base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${r'$'}{base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/common/js/jquery.pager.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/admin/js/admin.js"></script>
</head>
<body class="list">
	<div class="bar">
		${clazzinfo.objname}列表&nbsp;总记录数: ${r'$'}{pager.totalCount} (共${r'$'}{pager.pageCount}页)
	</div>
	<div class="body">
		<form id="listForm" action="${clazzinfo.underLineName}!list.action" method="post">
			<div class="listBar">
				<input type="button" class="formButton" onclick="location.href='${clazzinfo.underLineName}!add.action'" value="添加${clazzinfo.objname}" hidefocus />
				&nbsp;&nbsp;
				<select name="pager.searchBy">
					<#list attributes as item>  
					<option value="${item.name}"<${r'#'}if pager.searchBy == "${item.name}"> selected</${r'#'}if>>
						${item.desc}
					</option>
					</#list>
				</select>
				<input type="text" name="pager.keyword" value="${r'$'}{pager.keyword!}" />
				<input type="button" id="searchButton" class="formButton" value="搜 索" hidefocus />
				&nbsp;&nbsp;
				<label>每页显示: </label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10"<${r'#'}if pager.pageSize == 10> selected</${r'#'}if>>
						10
					</option>
					<option value="20"<${r'#'}if pager.pageSize == 20> selected</${r'#'}if>>
						20
					</option>
					<option value="50"<${r'#'}if pager.pageSize == 50> selected</${r'#'}if>>
						50
					</option>
					<option value="100"<${r'#'}if pager.pageSize == 100> selected</${r'#'}if>>
						100
					</option>
				</select>
			</div>
			<table id="listTable" class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<#list attributes as item>  
					<th>
						<a href="#" class="sort" name="${item.name}" hidefocus>${item.desc}</a>
					</th>
					</#list>
					<th>
						<span>操作</span>
					</th>
				</tr>
				<${r'#'}list pager.result as ${clazzinfo.tableas}>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${r'$'}{${clazzinfo.tableas}.id}" />
						</td>
						<#list attributes as item>  
						<td>
							<#if item.type=='Date'>
								${r'$'}{(${clazzinfo.tableas}.${item.javaName}?string("yyyy-MM-dd HH:mm"))!}
							<#else>
								${r'$'}{${clazzinfo.tableas}.${item.javaName}}
							</#if>
						</td>
						</#list>
						<td>
							<a href="${clazzinfo.underLineName}!edit.action?id=${r'$'}{${clazzinfo.tableas}.id}" title="编辑">[编辑]</a>
						</td>
					</tr>
				</${r'#'}list>
			</table>
			<${r'#'}if (pager.result?size > 0)>
				<div class="pagerBar">
					<div class="delete">
						<input type="button" id="deleteButton" class="formButton" url="${clazzinfo.underLineName}!delete.action" value="删 除" disabled hidefocus />
					</div>
					<div class="pager">
						<${r'#'}include "/WEB-INF/template/admin/pager.ftl" />
					</div>
				<div>
			<${r'#'}else>
				<div class="noRecord">没有找到任何记录!</div>
			</${r'#'}if>
		</form>
	</div>
</body>
</html>