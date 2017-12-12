package com.xy.xsql.entity.model.definition;

import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/10/21
 */
public class NoTableClassDefFoundError extends NoEntityDefFoundError {

    public NoTableClassDefFoundError() {
        super();
    }

    public NoTableClassDefFoundError(String s) {
        super(s);
    }

    public NoTableClassDefFoundError(Class clazz) {
        super(clazz);
    }
}
