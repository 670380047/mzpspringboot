package com.example.mzpspringboot.util;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/8/17 11:05
 * @File : StringUtil
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:  字符串处理的工具类
 * @date: 2019/8/17 11:05
 */
public class StringUtil {
    /***
    * description: 判断字符串是否为空
    * @author maozp3
    * @date: 11:07 2019/8/17
    * @param: [str]
    * @return boolean
    */
    public static boolean isEmpty(String str){
        return str == null || "".equals(str) || "null".equalsIgnoreCase(str);
    }
}
