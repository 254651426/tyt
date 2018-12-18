package com.bquan.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

public class FileOperateUtil {

	public static String copyFile(String path,File file,String fileName){
		try {
			System.out.println(file.getName());
			System.out.println(fileName);
			String extName = fileName.substring(
					fileName.lastIndexOf(".")+1, 
					fileName.length());
			String newFileName = UUID.randomUUID().toString()+"."+extName;
			File toCopyFile = new File(path+"/"+newFileName);
			FileUtils.copyFile(file, toCopyFile);
			return newFileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String copyFile(ServletContext servletContext,File file,String fileName){
		try {
			System.out.println(file.getName());
			System.out.println(fileName);
			String extName = fileName.substring(
					fileName.lastIndexOf(".")+1, 
					fileName.length());
			String newFileName = UUID.randomUUID().toString()+"."+extName;
			File toCopyFile = new File(servletContext.getRealPath("/upload/img")+"/"+newFileName);
			FileUtils.copyFile(file, toCopyFile);
			return newFileName;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
