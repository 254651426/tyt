package com.bquan.service.write;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:33:38
 */
public interface SysGeneratorWriteService {
	
	/**
	 * 生成代码
	 */
	byte[] generatorCode(String[] tableNames);
}
