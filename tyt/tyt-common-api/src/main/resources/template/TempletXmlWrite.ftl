<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${clazzinfo.daoWritePackageName}.${clazzinfo.classname}WriteMapper" >
  
  <delete id="delete" parameterType="${clazzinfo.entityPackageName}.${clazzinfo.classname}" >
  	<if test="id!=null">
	  	delete from 
			${clazzinfo.tablename}
	    where 
	    	id = ${r'#'}{id}
  	</if>
  </delete>
  
  <delete id="deleteBatch">
	delete from ${clazzinfo.tablename} where id in 
	<foreach item="id" collection="array" open="(" separator="," close=")">
		${r'#'}{id}
	</foreach>
  </delete>
  
  <insert id="insert" parameterType="${clazzinfo.entityPackageName}.${clazzinfo.classname}" >
		INSERT INTO ${clazzinfo.tablename} (
		<#list attributes as item>
    		<#if item_index == 0>
    		${item.name}
    		<#else>
            ,${item.name}
            </#if>
        </#list>
		)
		VALUES
		(
	    <#list attributes as item>
		    <#if item_index == 0>
			${r'#'}{${item.javaName}}
            <#else> 
            ,${r'#'}{${item.javaName}}
            </#if>
        </#list>
		)
	</insert>
	
	<update id="update" parameterType="${clazzinfo.entityPackageName}.${clazzinfo.classname}">
		UPDATE ${clazzinfo.tablename}
		<set>
	    <#list attributes as item>
	    	<#if item_index == 0>
			<if test="${item.javaName}!= null">${item.name} = ${r'#'}{${item.javaName}}</if>
            <#else> 
            <if test="${item.javaName}!= null">,${item.name} = ${r'#'}{${item.javaName}}</if>
            </#if>
        </#list>
		</set>
		WHERE id = ${r'#'}{id}
	</update>
	
</mapper>

