package com.study.common.utils;

import java.lang.reflect.Array;
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

    /**
     * 是否为空数组
     *
     * @param object
     * @return
     */
    private static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        // 判断空数组
        if (object.getClass().isArray()) {
            int len = Array.getLength(object);
            Object[] obj = new Object[len];
            for (int i = 0; i < len; i++) {
                obj[i] = Array.get(obj, i);
            }
            return obj.length == 0;
        }
        return false;
    }
}
