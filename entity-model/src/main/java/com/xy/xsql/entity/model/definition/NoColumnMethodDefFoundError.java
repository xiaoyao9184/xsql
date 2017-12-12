package com.xy.xsql.entity.model.definition;

import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/10/21
 */
public class NoColumnMethodDefFoundError extends NoEntityDefFoundError {

    public NoColumnMethodDefFoundError() {
        super();
    }

    public NoColumnMethodDefFoundError(String s) {
        super(s);
    }

    public NoColumnMethodDefFoundError(Method method) {
        super(method);
    }
}
