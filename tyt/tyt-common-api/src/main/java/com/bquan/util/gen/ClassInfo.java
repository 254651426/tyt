package com.bquan.util.gen;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 封装字段属性信息
 * @author Rocye
 * @createtime：2015-10-19
 */
public class ClassInfo {
    private String author;          //作者
    private String objname;         //业务类对象名
    private String srcpath;         //类根路径
    private String packagename;     //公共包名
    private String tablename;       //表名
    private String tableas;			//数据库别名
    private String underLineName;   // 下划线名称
    private String classname;       //类名
    private String classnamelc;     //首字母小写类名
    private String createTime;      //创建时间
    
    private String baseEntity;
    private String entityPackageName;
    private String daoReadPackageName;
    private String daoWritePackageName;
    private String serviceApiReadPackageName;
    private String serviceApiWritePackageName;
    private String serviceImplReadPackageName;
    private String serviceImplWritePackageName;
    private String controllerPackageName;
    
    public ClassInfo() {
    }
    
    public ClassInfo(String author, String objname, String srcpath,
            String packagename, String tablename, String tableas,String classname,
            String baseEntity,
			String entityPackageName,
			String daoReadPackageName,
			String daoWritePackageName,
			String serviceApiReadPackageName,
			String serviceApiWritePackageName,
			String serviceImplReadPackageName,
			String serviceImplWritePackageName,
			String controllerPackageName) {
        this.author = author;
        this.objname = objname;
        this.srcpath = srcpath;
        this.packagename = packagename;
        this.tableas = tableas;
        this.tablename = tablename;
        this.classname = classname;
        this.classnamelc = StringUtils.uncapitalize(this.classname);
        this.createTime = Utils.fotmatDate3(new Date());
        this.baseEntity = baseEntity ;
        this.entityPackageName = entityPackageName ;
        this.daoReadPackageName = daoReadPackageName ;
        this.daoWritePackageName = daoWritePackageName ;
        this.serviceApiReadPackageName =  serviceApiReadPackageName;
        this.serviceApiWritePackageName = serviceApiWritePackageName ;
        this.serviceImplReadPackageName =  serviceImplReadPackageName;
        this.serviceImplWritePackageName =  serviceImplWritePackageName;
        this.controllerPackageName = controllerPackageName ;
        this.underLineName=Utils.camelToUnderline(tableas);
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getObjname() {
        return objname;
    }
    public void setObjname(String objname) {
        this.objname = objname;
    }
    
    public String getSrcpath() {
        return srcpath;
    }
    public void setSrcpath(String srcpath) {
        this.srcpath = srcpath;
    }
    
    public String getPackagename() {
        return packagename;
    }
    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }
    
    public String getTablename() {
        return tablename;
    }
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    
    public String getClassname() {
        return classname;
    }
    public void setClassname(String classname) {
        this.classname = classname;
    }
    
    public String getClassnamelc() {
        return classnamelc;
    }
    public void setClassnamelc(String classnamelc) {
        this.classnamelc = classnamelc;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public String getEntityPackageName() {
		return entityPackageName;
	}

	public void setEntityPackageName(String entityPackageName) {
		this.entityPackageName = entityPackageName;
	}

	public String getControllerPackageName() {
		return controllerPackageName;
	}

	public void setControllerPackageName(String controllerPackageName) {
		this.controllerPackageName = controllerPackageName;
	}

	public String getTableas() {
		return tableas;
	}

	public void setTableas(String tableas) {
		this.tableas = tableas;
	}

	/**
	 * @return the baseEntity
	 */
	public String getBaseEntity() {
		return baseEntity;
	}

	/**
	 * @param baseEntity the baseEntity to set
	 */
	public void setBaseEntity(String baseEntity) {
		this.baseEntity = baseEntity;
	}

	/**
	 * @return the daoReadPackageName
	 */
	public String getDaoReadPackageName() {
		return daoReadPackageName;
	}

	/**
	 * @param daoReadPackageName the daoReadPackageName to set
	 */
	public void setDaoReadPackageName(String daoReadPackageName) {
		this.daoReadPackageName = daoReadPackageName;
	}

	/**
	 * @return the daoWritePackageName
	 */
	public String getDaoWritePackageName() {
		return daoWritePackageName;
	}

	/**
	 * @param daoWritePackageName the daoWritePackageName to set
	 */
	public void setDaoWritePackageName(String daoWritePackageName) {
		this.daoWritePackageName = daoWritePackageName;
	}

	/**
	 * @return the serviceApiReadPackageName
	 */
	public String getServiceApiReadPackageName() {
		return serviceApiReadPackageName;
	}

	/**
	 * @param serviceApiReadPackageName the serviceApiReadPackageName to set
	 */
	public void setServiceApiReadPackageName(String serviceApiReadPackageName) {
		this.serviceApiReadPackageName = serviceApiReadPackageName;
	}

	/**
	 * @return the serviceApiWritePackageName
	 */
	public String getServiceApiWritePackageName() {
		return serviceApiWritePackageName;
	}

	/**
	 * @param serviceApiWritePackageName the serviceApiWritePackageName to set
	 */
	public void setServiceApiWritePackageName(String serviceApiWritePackageName) {
		this.serviceApiWritePackageName = serviceApiWritePackageName;
	}

	/**
	 * @return the serviceImplReadPackageName
	 */
	public String getServiceImplReadPackageName() {
		return serviceImplReadPackageName;
	}

	/**
	 * @param serviceImplReadPackageName the serviceImplReadPackageName to set
	 */
	public void setServiceImplReadPackageName(String serviceImplReadPackageName) {
		this.serviceImplReadPackageName = serviceImplReadPackageName;
	}

	/**
	 * @return the serviceImplWritePackageName
	 */
	public String getServiceImplWritePackageName() {
		return serviceImplWritePackageName;
	}

	/**
	 * @param serviceImplWritePackageName the serviceImplWritePackageName to set
	 */
	public void setServiceImplWritePackageName(String serviceImplWritePackageName) {
		this.serviceImplWritePackageName = serviceImplWritePackageName;
	}

	public String getUnderLineName() {
		return underLineName;
	}

	public void setUnderLineName(String underLineName) {
		this.underLineName = underLineName;
	}

}
