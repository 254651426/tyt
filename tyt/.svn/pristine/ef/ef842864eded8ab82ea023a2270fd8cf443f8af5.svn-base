package com.bquan.util.gen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封装字段属性信息
 * @author Rocye
 * @createtime：2015-10-19
 */
public class Attribute {
    private String type;    //JAVA类型
    private String name;    //字段名称
    private String desc;    //字段描述
    private String javaName;
    
    public Attribute() {
    }
    
    public Attribute(String type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
        this.javaName=lineToHump(name);
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getJavaName() {
		return javaName;
	}

	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}


	private static Pattern linePattern = Pattern.compile("_(\\w)");  
    /**下划线转驼峰*/  
    public static String lineToHump(String str){  
        str = str.toLowerCase();  
        Matcher matcher = linePattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }  
    /**驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})*/  
    public static String humpToLine(String str){  
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();  
    }  
    private static Pattern humpPattern = Pattern.compile("[A-Z]");  
    /**驼峰转下划线,效率比上面高*/  
    public static String humpToLine2(String str){  
        Matcher matcher = humpPattern.matcher(str);  
        StringBuffer sb = new StringBuffer();  
        while(matcher.find()){  
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());  
        }  
        matcher.appendTail(sb);  
        return sb.toString();  
    }
    public static void main(String[] args) {  
        String lineToHump = lineToHump("f_parent_no_leader");  
        System.out.println(lineToHump);//fParentNoLeader  
        System.out.println(humpToLine(lineToHump));//f_parent_no_leader  
        System.out.println(humpToLine2(lineToHump));//f_parent_no_leader  
    }
}
