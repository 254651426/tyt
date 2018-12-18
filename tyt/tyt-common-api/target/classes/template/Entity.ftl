package ${clazzinfo.entityPackageName};

import ${clazzinfo.baseEntity};
import java.io.Serializable;
<#list attributes as item>
	<#if item.type == 'Date'>
import java.util.Date;
	<#break>
	</#if>
</#list>
<#list attributes as item>
	<#if item.type == 'BigDecimal'>
import java.math.BigDecimal;
	<#break>
	</#if>
</#list>

/**
 * ${clazzinfo.objname} Entity
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
public class ${clazzinfo.classname} extends BaseStrEntity{

<#list attributes as item>
	<#if item.javaName!='id'&&item.javaName!='createDate'&&item.javaName!='modifyDate'>
	private ${item.type} ${item.javaName};//${item.desc}
	</#if>
</#list>
 
<#list attributes as item>  
	<#if item.javaName!='id'&&item.javaName!='createDate'&&item.javaName!='modifyDate'>
	public ${item.type} get${item.javaName?cap_first}() {  
        return ${item.javaName};  
    }  
    public void set${item.javaName?cap_first}(${item.type} ${item.javaName}) {  
        this.${item.javaName} = ${item.javaName};  
    }
	</#if>
 </#list>
}
