package com.study.starter.utils;

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

}
