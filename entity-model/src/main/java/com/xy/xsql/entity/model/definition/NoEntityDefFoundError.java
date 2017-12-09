package com.xy.xsql.entity.model.definition;

import java.lang.reflect.Method;

/**
 * Created by xiaoyao9184 on 2017/10/21
 */
public class NoEntityDefFoundError extends LinkageError {
    private static final long serialVersionUID = 9095859863287012458L;

    public NoEntityDefFoundError() {
        super();
    }

    public NoEntityDefFoundError(String s) {
        super(s);
    }

    public NoEntityDefFoundError(Class clazz) {
        super(clazz.getName());
    }

    public NoEntityDefFoundError(Method method) {
        super(method.getName());
    }



    public static NoEntityDefFoundError column(String name) {
        return new NoEntityDefFoundError(name);
    }


}
