package com.study.city.utils;

/**
 * @author zhangpba
 * @date 2021-04-29
 */
public class PathUtils {

    /**
     * 获取当前包的位置
     *
     * @return
     */
    public static String getPackagePath() {
        // 获取excel的位置
        String filePath = System.getProperty("user.dir") // 获取当前工程的位置
                + System.getProperty("file.separator") + "study-city"
                + System.getProperty("file.separator") + "src"
                + System.getProperty("file.separator") + "main"
                + System.getProperty("file.separator") + "java"
                + System.getProperty("file.separator") + "com"
                + System.getProperty("file.separator") + "study"
                + System.getProperty("file.separator") + "city"
                + System.getProperty("file.separator");

        return filePath;
    }


    /**
     * 获取具体路径
     * @param word txt-文本；excel-表格；word-文档
     * @return
     */
    public static String getSpecific(String word) {
        String filePath = getPackagePath() + word + System.getProperty("file.separator");
        return filePath;
    }
}
