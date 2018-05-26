package com.xy.xsql.core.handler.list;

import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.lambda.Setter;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by xiaoyao9184 on 2018/5/25.
 * @param <T>
 */
public class ListHandler<T>  {

    private Supplier<List<T>> supplier;
    private Getter<List<T>> getter;
    private Setter<List<T>> setter;


    public ListHandler(Supplier<List<T>> supplier, Getter<List<T>> getter, Setter<List<T>> setter){
        this.supplier = supplier;
        this.getter = getter;
        this.setter = setter;
    }

    public ListHandler(Getter<List<T>> getter, Setter<List<T>> setter){
        this.supplier = ArrayList::new;
        this.getter = getter;
        this.setter = setter;
    }


    /**
     * init
     * @param supplier List Supplier
     * @return List
     */
    public List<T> init(Supplier<List<T>> supplier){
        List<T> list = getter.get();
        if(list == null){
            list = supplier.get();
            if(Setter.empty().equals(setter)){
                //nothing
            }
            setter.set(list);
        }
        return list;
    }

    /**
     * init
     * @return List
     */
    public List<T> init(){
        return init(this.supplier == null ?
                ArrayList::new :
                this.supplier);
    }

    /**
     * Add item to list
     * @param c Item
     * @return C Item
     */
    public <C extends T> C add(C c) {
        this.init().add(c);
        return c;
    }

    /**
     * Add item to list
     * @param supplier Item Supplier
     * @return C Item
     */
    public <C extends T> C addNew(Supplier<C> supplier) {
        C c = supplier.get();
        return this.add(c);
    }

    /**
     * Add collection to list
     * @param collection Collection
     * @return Collection
     */
    public <C extends T> Collection<C> addAll(Collection<C> collection) {
        this.init().addAll(collection);
        return collection;
    }

    /**
     * Add collection to list
     * @param array Array
     * @return Collection
     */
    public <C extends T> Collection<C> addAll(C... array) {
        List<C> list = Arrays.asList(array);
        return addAll(list);
    }

    /**
     * Add collection to list
     * @param stream Stream
     * @return Collection
     */
    public <C extends T> Collection<C> addAll(Stream<C> stream) {
        List<C> list = stream.collect(Collectors.toList());
        return addAll(list);
    }


    /**
     * Quick in
     * @param supplier List Supplier
     * @param getter List Getter
     * @param setter List Setter
     * @param <T> T
     * @return ListHandler
     */
    public static <T> ListHandler<T> list(Supplier<List<T>> supplier, Getter<List<T>> getter, Setter<List<T>> setter){
        return new ListHandler<>(supplier, getter, setter);
    }

    /**
     * Quick in
     * @param getter List Getter
     * @param setter List Setter
     * @param <T> T
     * @return ListHandler
     */
    public static <T> ListHandler<T> list(Getter<List<T>> getter, Setter<List<T>> setter){
        return new ListHandler<>(getter, setter);
    }

    /**
     * Quick in
     * cant init because list is reference, cant set it
     * @param list list
     * @param <T> T
     * @return ListHandler
     */
    public static <T> ListHandler<T> list(final List<T> list){
        return new ListHandler<>(() -> list, Setter.empty());
    }

}
