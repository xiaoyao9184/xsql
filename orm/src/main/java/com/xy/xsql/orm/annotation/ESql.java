package com.xy.xsql.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体SQL 注解
 * Created by xiaoyao9184 on 2016/1/16.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ESql {

    /**
     * 查询sql
     */
    String sql() default "";

    /**
     * 参数sql
     * 通过判断此项判断参数量，根据实际参数自动隐藏条件
     */
    @Deprecated
    String[] args() default {};

}