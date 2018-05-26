package com.xy.xsql.core.handler.object;

import com.xy.xsql.core.lambda.Setter;

import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2018/5/25.
 * @param <T>
 * @param <C>
 */
public class SupplierObjectHandler<T,C extends T>  {

    private Supplier<C> supplier;

    public SupplierObjectHandler(Supplier<C> supplier){
        this.supplier = supplier;
    }

    /**
     * init
     * @return Object
     */
    public C set(Setter<T> setter){
        C c = supplier.get();
        setter.set(c);
        return c;
    }

    /**
     * Quick in
     * @param supplier Object Supplier
     * @param <T> T
     * @param <C> Child T
     * @return GetterSetterObjectHandler
     */
    public static <T,C extends T> SupplierObjectHandler<T,C> object(Supplier<C> supplier){
        return new SupplierObjectHandler<>(supplier);
    }

}
