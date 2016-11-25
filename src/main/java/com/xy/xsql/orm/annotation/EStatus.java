package com.xy.xsql.orm.annotation;

import com.xy.xsql.orm.model.BaseRowStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段：状态 注解
 * Created by xiaoyao9184 on 2016/6/26.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EStatus {

    /**
     * 删除标识
     */
    BaseRowStatus deleFlag() default BaseRowStatus.NOTUSE;

}
