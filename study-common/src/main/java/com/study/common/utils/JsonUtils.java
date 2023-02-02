package com.study.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * JSON帮助类
 */
public class JsonUtils {
    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // 设置FAIL_ON_EMPTY_BEANS属性，当序列化空对象不要抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 设置FAIL_ON_UNKNOWN_PROPERTIES属性，当JSON字符串中存在Java对象没有的属性，忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转json
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String obj2Json(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * json转对象
     *
     * @param json
     * @param objClass
     * @return
     * @throws IOException
     */
    public static <T> T json2Object(String json, Class<T> objClass) {
        try {
            return objectMapper.readValue(json, objClass);
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean isJSONValid(String jsonInString) {
        try {
            objectMapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
