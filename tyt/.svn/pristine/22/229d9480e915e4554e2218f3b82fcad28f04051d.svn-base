package com.bquan.util.gen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 国信安达SSM框架基础部分生成器
 * 本类主要是生成有些表字段太多的配置文件input.properties的信息
 * @author Rocye
 * @createTime 2015-06-25
 */
public class GetInputPropertiesData {
    
    // 这里填入套接字、用户、密码--测试用
    private static final String[] CONNECT_STR = { "jdbc:mysql://10.200.52.136:8066/bizflow-x-master", "bizflow-x", "test" };

    /**
     * 打印指定数据库指定表的字段及备注信息，用于input.properties
     * @author Rocye
     * @createTime 2015-06-25
     */
    public static void printTablecolumns(String tableName) {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        StringBuilder filedStrB = new StringBuilder();
        StringBuilder commeStrB = new StringBuilder();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECT_STR[0], CONNECT_STR[1], CONNECT_STR[2]);
            stmt = conn.createStatement();
            sql = "show full columns from " + tableName + "";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String filedName = rs.getString("field");
                String filedType = rs.getString("type");
                String comment = rs.getString("comment");
                if(filedType.indexOf("tinyint(1)") > -1){
                    filedStrB.append(",Boolean ");
                }else if(filedType.indexOf("int") > -1){
                    filedStrB.append(",Integer ");
                }else if(filedType.indexOf("char") > -1){
                    filedStrB.append(",String ");
                }else if(filedType.indexOf("float") > -1){
                    filedStrB.append(",Float ");
                }else if(filedType.indexOf("double") > -1){
                    filedStrB.append(",Double ");
                }else if(filedType.indexOf("decimal") > -1){
                    filedStrB.append(",Float ");
                }else if(filedType.indexOf("datetime") > -1){
                    filedStrB.append(",Date ");
                }else if(filedType.indexOf("date") > -1){
                    filedStrB.append(",Date ");
                }
                filedStrB.append(filedName);
                commeStrB.append("," + comment);
            }
            
            System.out.println(filedStrB.toString().substring(1));
            System.out.println(commeStrB.toString().substring(1));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 获取指定数据库指定表的字段及备注信息的列表
     * @author Rocye
     * @createTime 2015-10-19
     */
    public static List<Attribute> getTablecolumns(String tableName,String dbserver,String dbuser,String dbpass) {
        List<Attribute> attrList = new ArrayList<Attribute>(0);
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbserver, dbuser, dbpass);
            stmt = conn.createStatement();
            sql = "show full columns from " + tableName + "";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String filedName = rs.getString("field");
                String filedType = rs.getString("type");
                String comment = rs.getString("comment");
                //System.out.println(filedType);
                if(filedType.indexOf("tinyint(1)") > -1){
                    filedType = "Boolean";
                }else if(filedType.indexOf("int") > -1){
                    filedType = "Integer";
                }else if(filedType.indexOf("char") > -1 || filedType.indexOf("text") > -1){
                    filedType = "String";
                }else if(filedType.indexOf("float") > -1){
                    filedType = "Float";
                }else if(filedType.indexOf("double") > -1){
                    filedType = "Double";
                }else if(filedType.indexOf("decimal") > -1){
                    filedType = "BigDecimal";
                }else if(filedType.indexOf("datetime") > -1){
                    filedType = "Date";
                }else if(filedType.indexOf("date") > -1){
                    filedType = "Date";
                }
                attrList.add(new Attribute(filedType, filedName, comment));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return attrList;
    }
    
    public static void excSql(List<String> sqlList,String dbserver,String dbuser,String dbpass){
    	Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbserver, dbuser, dbpass);
            stmt = conn.createStatement();
            for(String sql:sqlList){
            	stmt.addBatch(sql);
            }
            stmt.executeBatch();
            //conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        printTablecolumns("dxb_corporate_authen_product");

    }

}
