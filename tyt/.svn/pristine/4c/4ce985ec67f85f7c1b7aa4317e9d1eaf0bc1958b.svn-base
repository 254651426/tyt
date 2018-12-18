package com.bquan.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.bquan.bean.TreeNode;

public class FileUtil {

	public static SimpleDateFormat SIM_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 读取文件
	 * @param fileName
	 * @return
	 */
	public static String readFileByLines(String fileName) {
		StringBuffer sf = new StringBuffer("");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
            		new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	System.out.println(tempString);
            	sf.append(tempString+"\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return sf.toString();
    }
	
	/**
	 * 遍历文件夹
	 * @param treeNodeList
	 * @param dir
	 * @param nodeId
	 */
	public static void Dir(List<TreeNode> treeNodeList,File dir,String nodeId) {
		try {
			if (dir.exists()) {
	            for (File f : dir.listFiles()) {
	            	// 生成节点的id
	    			String id = UUID.randomUUID().toString();
	                if (f.isDirectory()) {
	                	treeNodeList.add(
	                			new TreeNode(
	                					id, nodeId, f.getName(), 
	                					true, true,f.getPath(),
	                					false,new BigDecimal(f.length()/(1024*1024)),SIM_DATE.format(f.lastModified())));
	                    Dir(treeNodeList,f,id);
	                } else {
	                	treeNodeList.add(
	                			new TreeNode(
	                					id, nodeId, f.getName(), 
	                					false, false,f.getPath(),
	                					true,new BigDecimal(f.length()),
	                					SIM_DATE.format(f.lastModified())));
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void DirChildren(List<String> pathList,File dir,String nodeId, String prefix) {
		try {
			if (dir.exists()) {
	            for (File f : dir.listFiles()) {
	            	// 生成节点的id
	    			String id = UUID.randomUUID().toString();
	                if (f.isDirectory()) {
	                	if(f.getName().startsWith(prefix)){
		                	pathList.add(f.getName());
	                	}
	                	DirChildren(pathList,f,id,prefix);
	                } else {
	                	if(f.getName().startsWith(prefix)){
		                	pathList.add(f.getName());
	                	}
	                }
	            }
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新文件内容
	 * @param path
	 * @param text
	 */
	public static void updateFileContent(String path,String text){
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			if(file.isFile()){
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(text);
			}
		} catch (Exception e) {
			
		}finally {
			try {
				if(bw!=null){
					bw.close();
				}
			} catch (Exception e2) {
				
			}
		}
	}

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /**
     * 将一个文件或文件夹下的所有文件
     * 	拷贝到
     * 	另外一个文件或文件夹中
     * @param fromPath
     * @param toPath
     */
    public static void copyFile(String fromPath,String toPath){
    	File from = new File(fromPath);
    	File to = new File(toPath);
        
        // 要复制的文件或者文件夹不存在，直接退出
        if(!from.exists()){
        	return;
        }
        
        if(from.isFile()){
        	byte[] b = new byte[(int) from.length()];
        	
        	if(to.isDirectory()){
        		System.out.println("执行了");
        		to = new File(to.getPath()+File.separator+from.getName());
        		System.out.println(to.getPath());
        	}
        	
        	// 判断上级目录是否存在
    		if(!to.getParentFile().exists()){
    			to.getParentFile().mkdirs();
    		}
        	
        	FileInputStream is= null;
            FileOutputStream ps= null;
        	try {
        		is = new FileInputStream(from);
        		ps = new FileOutputStream(to);
                is.read(b);
                ps.write(b);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}finally{
        		try {
        			if(is!=null){
        				is.close();
        			}
        			if(ps!=null){
        				ps.close();
        			}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
        	}
        }else if(from.isDirectory()){
        	if(!from.exists()){
        		from.mkdirs();
        	}
        	for(File f : from.listFiles()){
        		// 创建文件夹
        		if(f.isDirectory()){
        			File dir = new File(to.getPath()+File.separator+f.getName());
        			if(!dir.exists()){
        				dir.mkdirs();
        			}
        		}
        		copyFile(from.getPath()+File.separator+f.getName(), 
        				to.getPath()+File.separator+f.getName());
        	}
        }
    }
    
    /*
     * Java文件操作 获取文件扩展名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
        public static String getExtensionName(String filename) { 
            if ((filename != null) && (filename.length() > 0)) { 
                int dot = filename.lastIndexOf('.'); 
                if ((dot >-1) && (dot < (filename.length() - 1))) { 
                    return filename.substring(dot + 1); 
                } 
            } 
            return filename; 
        } 
        
    /*
     * Java文件操作 获取不带扩展名的文件名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
        public static String getFileNameNoEx(String filename) { 
            if ((filename != null) && (filename.length() > 0)) { 
                int dot = filename.lastIndexOf('.'); 
                if ((dot >-1) && (dot < (filename.length()))) { 
                    return filename.substring(0, dot); 
                } 
            } 
            return filename; 
        } 
        
}
