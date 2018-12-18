package ${clazzinfo.serviceImplWritePackageName};

import javax.annotation.Resource;
import ${clazzinfo.entityPackageName}.${clazzinfo.classname};
import ${clazzinfo.serviceApiWritePackageName}.${clazzinfo.classname}WriteService;
import ${clazzinfo.daoWritePackageName}.${clazzinfo.classname}WriteMapper;
import com.bquan.service.write.BaseWriteServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * ${clazzinfo.objname} Service层写接口实现
 * @author ${clazzinfo.author}
 * @createTime ${clazzinfo.createTime}
 */
public class ${clazzinfo.classname}WriteServiceImpl extends BaseWriteServiceImpl<${clazzinfo.classname}> implements ${clazzinfo.classname}WriteService{

	@Resource
	private ${clazzinfo.classname}WriteMapper ${clazzinfo.tableas}WriteMapper;

	@Override
	public ${clazzinfo.classname}WriteMapper getBaseWriteMapper() {
		return ${clazzinfo.tableas}WriteMapper;
	}
	
}