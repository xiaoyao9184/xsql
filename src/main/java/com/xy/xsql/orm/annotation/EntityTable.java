package com.xy.xsql.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体表 注解
 * Created by xiaoyao9184 on 2016/6/26.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityTable {

    /**
     * 表名
     */
    String name() default "";

    /**
     * 别名
     */
    String otherName() default "";

}