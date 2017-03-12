package com.xy.xsql.orm.util;


import com.xy.xsql.orm.core.BaseBuilder;
import com.xy.xsql.orm.core.Getter;
import com.xy.xsql.orm.core.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class ListBuilder<ListType> implements BaseBuilder<Void,List<ListType>> {

    private List<ListType> list;

    public ListBuilder(){
        this.list = new ArrayList<>();
    }

    public ListBuilder<ListType> withItem(ListType listType){
        if(listType == null){
            return this;
        }
        list.add(listType);
        return this;
    }

    @Override
    public List<ListType> build(Void aVoid) {
        return list;
    }


    public static <T> T initNew(final Supplier<? extends T> supplier, final Getter<List<T>> getter, final Setter<List<T>> setter) {
        if(getter.get() == null){
            setter.set(new ArrayList<>());
        }
        T t = supplier.get();
        getter.get().add(t);

        return t;
    }

    public static <T> T initAdd(final T t, final Getter<List<T>> getter, final Setter<List<T>> setter) {
        if(getter.get() == null){
            setter.set(new ArrayList<>());
        }
        getter.get().add(t);

        return t;
    }
}
