package com.data.chain.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @author peilizhu
 * @desc 描述信息
 * @date 2022/12/15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringBeanUtils {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringBeanUtils.applicationContext = applicationContext;
    }

    /**获取applicationContext**/
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**通过name获取 Bean.**/
    public static Object getBean(String name){
        try {
            return getApplicationContext().getBean(name);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**通过class获取Bean.**/
    public static <T> T getBean(Class<T> clazz){
        try {
            return getApplicationContext().getBean(clazz);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**通过name,以及Clazz返回指定的Bean**/
    public static <T> T getBean(String name,Class<T> clazz){
        try {
            return getApplicationContext().getBean(name, clazz);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return null;
    }
}
