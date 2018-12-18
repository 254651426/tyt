<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${clazzinfo.daoReadPackageName}.${clazzinfo.classname}ReadMapper" >
  <resultMap id="BaseResultMap" type="${clazzinfo.entityPackageName}.${clazzinfo.classname}" >
	<#list attributes as item>  
    	<result column="${clazzinfo.tableas}_${item.name}" property="${item.javaName}" />
    </#list>
  </resultMap>
  
  <sql id="Base_Column_List" >
	<#list attributes as item>  
		${clazzinfo.tableas}.${item.name} as ${clazzinfo.tableas}_${item.name}<#if (item_index+1)!=(attributes?size)>,</#if>
	</#list>
  </sql>
  
  <sql id="Base_Where_Sql" >
  <#list attributes as item>  
  	<if test="${item.javaName}!=null"> and ${clazzinfo.tableas}.${item.name} = ${r'#'}{${item.javaName}} </if>
  </#list>
  </sql>
  
  <select id="select" resultMap="BaseResultMap"  >
    select 
    <include refid="Base_Column_List" />
    from ${clazzinfo.tablename} ${clazzinfo.tableas}
    <include refid="baseReadMapper.searchSql" />
    <include refid="Base_Where_Sql" />
    <include refid="baseReadMapper.orderSql" />
  </select>
  
  <select id="count" resultType="Integer"  >
    select 
    	count(id)
    from ${clazzinfo.tablename} ${clazzinfo.tableas}
    <include refid="baseReadMapper.searchSql" />
    <include refid="Base_Where_Sql" />
  </select>
  
  <select id="sum" resultType="java.math.BigDecimal"  >
    select 
    	ifnull(sum(${r'$'}{sumItem}),0) 
    from ${clazzinfo.tablename} ${clazzinfo.tableas}
    <include refid="baseReadMapper.searchSql" />
    <include refid="Base_Where_Sql" />
  </select>
  
</mapper>















