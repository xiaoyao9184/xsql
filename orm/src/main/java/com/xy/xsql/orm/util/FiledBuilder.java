package com.xy.xsql.orm.util;

import com.xy.xsql.orm.core.Setter;

import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class FiledBuilder {

    public static <T> T set(final Supplier<? extends T> supplier, final Setter<T> setter) {
        T t = supplier.get();
        setter.set(t);

        return t;
    }

}
