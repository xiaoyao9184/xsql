package com.xy.xsql.core.lambda;

/**
 * Getter
 * Created by xiaoyao9184 on 2017/3/11.
 * @param <T>
 */
@FunctionalInterface
public interface Getter<T> {

    /**
     * do get
     * @return T
     */
    T get();

    static <T> Getter<T> empty(){
        return () -> null;
    }
}
