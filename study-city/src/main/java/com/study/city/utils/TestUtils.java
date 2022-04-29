package com.study.city.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class TestUtils {

    // 免费API
    public static void main(String path[]) throws Exception {
        // https://www.free-api.com/doc/554
        URL u = new URL("https://yiketianqi.com/api?unescape=1&version=v1&appid=85841439&appsecret=KEKCDLT4I&city=宝鸡");
        InputStream in = u.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        System.out.println(new String(b, "utf-8"));
    }
}
