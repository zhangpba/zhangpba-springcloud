package com.study.starter.utils;

/**
 * @author zhangpba
 * @description 数组帮助类
 * @date 2022/6/21
 */
public class ArraysUtils {

    /**
     * 将数组内容转化为字符串
     *
     * @param strings
     * @return
     */
    public static String toString(String[] strings) {
        StringBuffer stringBuffer = new StringBuffer();
        if (strings != null && strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                if (i != strings.length - 1) {
                    stringBuffer.append(strings[i]);
                    stringBuffer.append(",");
                } else {
                    stringBuffer.append(strings[i]);
                }
            }
        }
        return stringBuffer.toString();
    }
}
