package com.study.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * ip帮助类
 *
 * @date 2021-08-15
 */
public class IpUtils {

    /**
     * 获取请求IP:
     * 用户的真实IP不能使用request.getRemoteAddr()
     * 这是因为可能会使用一些代理软件，这样ip获取就不准确了
     * 此外我们如果使用了多级（LVS/Nginx）反向代理的话，ip需要从X-Forwarded-For中获得第一个非unknown的IP才是用户的有效ip。
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 判断ip是否合法
     *
     * @param ip 输入参数
     * @return ip是否合法
     * @date 2021-10-27
     */
    public static boolean checkIp(String ip) {
        // 转义分割
        String[] arrs = ip.split("\\.");
        if (arrs.length != 4) {
            return false;
        }
        for (int i = 0; i < arrs.length; i++) {
            try {
                Integer num = Integer.parseInt(arrs[i]);
                // 字段在0-255之间
                if (num < 0 || num > 255) {
                    return false;
                }
                // 当数字不为0，首个字符不能为0
                if (!arrs[i].equals("0") && arrs[i].startsWith("0")) {
                    return false;
                }
                // 首尾不能为0
                if (i == 0 || i == 3) {
                    if (num == 0) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
