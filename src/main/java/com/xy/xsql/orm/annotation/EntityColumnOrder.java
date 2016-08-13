package com.xy.xsql.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段：排序 注解
 * Created by xiaoyao9184 on 2016/4/25.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityColumnOrder {

    /**
     * 正序
     */
    boolean aes() default true;

}
