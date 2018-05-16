package com.xy.xsql.core.lambda;

/**
 * Setter
 * Created by xiaoyao9184 on 2017/3/11.
 * @param <T>
 */
@FunctionalInterface
public interface Setter<T> {

    /**
     * do set
     * @param t T
     */
    void set(T t);


    static <T> Setter<T> empty(){
        return (e) -> {};
    }
}
