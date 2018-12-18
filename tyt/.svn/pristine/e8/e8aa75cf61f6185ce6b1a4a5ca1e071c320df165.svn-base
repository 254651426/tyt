package com.bquan.util;

import com.bquan.util.RRException;
import org.apache.commons.lang.StringUtils;

/**
 * SQL过滤
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-01 16:16
 */
public class SQLFilterUtil {

    /**
     * SQL注入过滤(发现有注入风险，返回true)
     * @param str  待验证的字符串
     */
    public static boolean sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return false;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                return true;
            }
        }

        return false;
    }
}
