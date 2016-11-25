package com.xy.xsql.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段：参数 注解
 * Created by xiaoyao9184 on 2016/1/16.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EParam {

    /**
     * 条件
     */
    boolean and() default true;

    /**
     * 字段(仅在非Column标注时使用)
     */
    String field() default "";

    /**
     * 关系
     */
    String relationship() default "=";

    /**
     * 值
     */
    String value() default "?";

}
