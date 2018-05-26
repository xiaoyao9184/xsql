package com.xy.xsql.core.handler.object;

import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;

import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2018/5/25.
 * @param <T>
 */
public class GetterSetterObjectHandler<T>  {

    private Getter<T> getter;
    private Setter<T> setter;

    public GetterSetterObjectHandler(Getter<T> getter, Setter<T> setter){
        this.getter = getter;
        this.setter = setter;
    }

    /**
     * init
     * @param supplier Object Supplier
     * @param <C> Child T
     * @return Object
     */
    public <C extends T> C init(Supplier<C> supplier){
        T t = getter.get();
        if(t == null){
            C c = supplier.get();
            setter.set(c);
            return c;
        }
        return (C) t;
    }


    /**
     * Quick in
     * @param getter Object Getter
     * @param setter Object Setter
     * @param <T> T
     * @return GetterSetterObjectHandler
     */
    public static <T> GetterSetterObjectHandler<T> object(Getter<T> getter, Setter<T> setter){
        return new GetterSetterObjectHandler<>(getter, setter);
    }

}
