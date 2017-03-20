package com.xy.xsql.core;


import com.xy.xsql.core.builder.BaseBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 * @param <ListType>
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


    /**
     * Init List if NULL
     * @param getter
     * @param setter
     * @param <T>
     */
    public static <T> void initList(final Getter<List<T>> getter, final Setter<List<T>> setter) {
        if(getter.get() == null){
            setter.set(new ArrayList<>());
        }
    }

    /**
     * Add New element to List
     * @param supplier
     * @param getter
     * @param setter
     * @param <T>
     * @return
     */
    public static <T> T initNew(final Supplier<? extends T> supplier, final Getter<List<T>> getter, final Setter<List<T>> setter) {
        if(getter.get() == null){
            setter.set(new ArrayList<>());
        }
        T t = supplier.get();
        getter.get().add(t);

        return t;
    }

    /**
     * Add element to List
     * @param t
     * @param getter
     * @param setter
     * @param <T>
     * @return
     */
    public static <T> T initAdd(final T t, final Getter<List<T>> getter, final Setter<List<T>> setter) {
        if(getter.get() == null){
            setter.set(new ArrayList<>());
        }
        if(t != null){
            getter.get().add(t);
        }

        return t;
    }

    /**
     * Add elements to List
     * @param t
     * @param getter
     * @param setter
     * @param <T>
     * @return
     */
    public static <T> Collection<T> initAdd(final Collection<T> t, final Getter<List<T>> getter, final Setter<List<T>> setter) {
        if(getter.get() == null){
            setter.set(new ArrayList<>());
        }
        getter.get().addAll(t);

        return t;
    }

    /**
     * Call setter with each element
     * @param t
     * @param setter
     * @param <T>
     */
    public static <T> void setter(final Collection<T> t, final Setter<T>... setter) {
        final int[] index = {0};
        t.forEach((a)->{
            if(setter.length > index[0]){
                setter[index[0]].set(a);
                index[0]++;
            }
        });
    }

    /**
     * Reverse Array to List
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> reverse(T... t){
        Iterator<T> reversedStream = Stream
                .of(t)
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator();

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(reversedStream,
                        Spliterator.ORDERED), false).collect(
                Collectors.toList());
    }

}
