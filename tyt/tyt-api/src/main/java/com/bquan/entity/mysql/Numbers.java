package com.bquan.entity.mysql;

import java.io.Serializable;
import java.util.Date;

/**
 * 编号表 Entity
 * @author FSY
 * @createTime 2017-05-12
 */
public class Numbers extends BaseStrEntity{

	private Integer number;//
	private Integer isuser;//是否使用0，未使用，1使用
 
	public Integer getNumber() {  
        return number;  
    }  
    public void setNumber(Integer number) {  
        this.number = number;  
    }
	public Integer getIsuser() {  
        return isuser;  
    }  
    public void setIsuser(Integer isuser) {  
        this.isuser = isuser;  
    }
}
