package ${clazzinfo.serviceImplReadPackageName};

import javax.annotation.Resource;
import ${clazzinfo.entityPackageName}.${clazzinfo.classname};
import ${clazzinfo.serviceApiReadPackageName}.${clazzinfo.classname}ReadService;
import ${clazzinfo.daoReadPackageName}.${clazzinfo.classname}ReadMapper;
import com.bquan.service.read.BaseReadServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * ${clazzinfo.objname} Service层读接口实现
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
public class ${clazzinfo.classname}ReadServiceImpl extends BaseReadServiceImpl<${clazzinfo.classname}> implements ${clazzinfo.classname}ReadService{

	@Resource
	private ${clazzinfo.classname}ReadMapper ${clazzinfo.tableas}ReadMapper;

	@Override
	public ${clazzinfo.classname}ReadMapper getBaseReadMapper() {
		return ${clazzinfo.tableas}ReadMapper;
	}
	
}



