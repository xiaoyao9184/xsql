package com.xy.xsql.core;

import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;

import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class FiledBuilder {

    /**
     * Call setter with new object
     * @param supplier
     * @param setter
     * @param <T>
     * @return
     */
    public static <T> T set(final Supplier<? extends T> supplier, final Setter<T> setter) {
        T t = supplier.get();
        setter.set(t);

        return t;
    }

    /**
     * Call setter with new object if getter result is NULL
     * @param supplier
     * @param getter
     * @param setter
     * @param <T>
     * @return
     */
    public static <T> T initSet(final Supplier<? extends T> supplier, final Getter<T> getter, final Setter<T> setter) {
        if(getter.get() == null){
            setter.set(supplier.get());
        }
        return getter.get();
    }
}
