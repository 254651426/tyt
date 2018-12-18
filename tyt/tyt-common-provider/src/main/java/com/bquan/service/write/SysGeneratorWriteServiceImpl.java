package com.bquan.service.write;

import com.bquan.util.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bquan.dao.read.SysGeneratorReadDao;
import com.bquan.dao.write.SysGeneratorWriteDao;
import com.bquan.service.write.SysGeneratorWriteService;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

public class SysGeneratorWriteServiceImpl implements SysGeneratorWriteService {
	
	@Autowired
	private SysGeneratorReadDao sysGeneratorReadDao;
	@Autowired
	private SysGeneratorWriteDao sysGeneratorDao;

	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = sysGeneratorReadDao.queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = sysGeneratorReadDao.queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
