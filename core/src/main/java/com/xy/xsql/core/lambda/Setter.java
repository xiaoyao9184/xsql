package com.xy.xsql.core.lambda;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
@FunctionalInterface
public interface Setter<T> {

    void set(T t);
}
