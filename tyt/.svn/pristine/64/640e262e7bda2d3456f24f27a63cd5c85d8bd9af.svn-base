package com.bquan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class conUtil {

	/* 服务器上的数据库配置 */
	private static final String url = "jdbc:mysql://172.31.240.157:3306/webgui";  
	private static final String name = "com.mysql.jdbc.Driver";  
    private static final String user = "root";  
    private static final String password = "root";  
	
	/* 10测试服务器的数据库的配置 */
//	private static final String url = "jdbc:mysql://10.200.52.172:3306/ct_group";  
//	private static final String name = "com.mysql.jdbc.Driver";  
//    private static final String user = "root";  
//    private static final String password = "mysql";  
  
    public static Connection getConn(){
    	Connection conn = null;  
    	try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//获取连接  
		} catch (Exception e) {
			e.printStackTrace();
		}//指定连接类型  
        return conn;
    }
    
    
    public static void insert(final String sql,final Object[] values) {  
    	PreparedStatement pstmt = null;
    	Connection conn = null;
        try {  
            conn = getConn();
            
            conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			if (values != null) {
				for (int i = 0, n = values.length; i < n; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			
			pstmt.execute();
			conn.commit();
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{
        	if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	if(conn != null) {
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
    }  
 
}
