package com.xy.xsql.core.lambda;

/**
 * Configurator
 * Created by xiaoyao9184 on 2017/7/6.
 * @param <T>
 */
@FunctionalInterface
public interface Configurator<T> {

    /**
     * do config
     * @param t T
     */
    void config(T t);
}
