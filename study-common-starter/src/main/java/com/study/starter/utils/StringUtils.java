package com.study.starter.utils;

import java.lang.reflect.Array;

/**
 * 字符串帮助类
 */
public class StringUtils {

    /**
     * 判断一个字符是否为字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        // 取字符串第一个字符，如果在a-z或者A-Z之间，那么为字母，否则不是
        char c = str.charAt(0);
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 是否为空数组
     * @param object
     * @return
     */
    private static boolean isEmpty(Object object){
        if(object == null){
            return true;
        }
        // 判断空数组
        if(object.getClass().isArray()){
            int len = Array.getLength(object);
            Object[] obj = new Object[len];
            for (int i = 0; i < len; i++) {
                obj[i] = Array.get(obj,i);
            }
            return obj.length == 0;
        }
        return false;
    }

}
