package com.data.chain.annotation;

import java.lang.annotation.*;

/**
 * @author peilizhu
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminAuthIgnore {

}
