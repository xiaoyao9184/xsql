package com.xy.xsql.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段 注解
 * Created by xiaoyao9184 on 2016/1/16.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EColumn {

    /**
     * 字段名称
     */
    String name() default "";

}