<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑${clazzinfo.objname}</title>
<link href="${r'$'}{base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${r'$'}{base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${r'$'}{base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/common/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${r'$'}{base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready( function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	
	var $tab = $("#tab");

	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			<#list attributes as item>  
			<#if item.name!='id' && item.name!='create_date' && item.name!='modify_date'>
			"${clazzinfo.tableas}.${item.javaName}":"required"<#if item_index!=(attributes?size-1)>,</#if>
			</#if>
			</#list>
		},
		messages: {
			<#list attributes as item>  
			<#if item.name!='id' && item.name!='create_date' && item.name!='modify_date'>
			"${clazzinfo.tableas}.${item.javaName}":"${(item.desc)!}不能为空"<#if item_index!=(attributes?size-1)>,</#if>
			</#if>
			</#list>
		},
		submitHandler: function(form) {
			form.submit();
		}
	});
	
});
</script>
</head>
<body class="input admin">
	<div class="bar">
		<${r'#'}if isAddAction>添加${clazzinfo.objname}<${r'#'}else>编辑${clazzinfo.objname}</${r'#'}if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<${r'#'}if isAddAction>${clazzinfo.underLineName}!save.action<${r'#'}else>${clazzinfo.underLineName}!update.action</${r'#'}if>" method="post">
			<input type="hidden" name="id" value="${r'$'}{id}" />
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本信息" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<#list attributes as item>  
				<#if item.name!='id' && item.name!='create_date' && item.name!='modify_date'>
				<tr>
					<th>
						${item.desc}: 
					</th>
					<td>
						<#if item.type=='Date'>
						<input type="text" name="${clazzinfo.tableas}.${item.javaName}" class="formText" value="${r'$'}{(${clazzinfo.tableas}.${item.javaName}?string("yyyy-MM-dd HH:mm:ss"))!}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
						<#elseif item.type=='Boolean'>
						<${r'@'}checkbox name="${clazzinfo.tableas}.${item.javaName}" value="${r'$'}{(${clazzinfo.tableas}.${item.javaName})!true}" />是
						<#else>
						<input type="text" name="${clazzinfo.tableas}.${item.javaName}" class="formText" value="${r'$'}{(${clazzinfo.tableas}.${item.javaName})!}" />
						</#if>
						<label class="requireField">*</label>
					</td>
				</tr>
				</#if>
				</#list>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>