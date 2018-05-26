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
     * Call setter with each item
     * @param t Collection
     * @param setter Item Setter
     * @param <T> T
     */
    @SuppressWarnings("unchecked")
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
     * @param t Item
     * @param <T> T
     * @return List
     */
    @SuppressWarnings("unchecked")
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

    /**
     * Get last item
     * @param list List
     * @param <T> T
     * @return Item
     */
    public static <T> T getLastItem(List<T> list){
        int index = list.size() - 1;
        return list.get(index);
    }

    /**
     * Pop last item
     * @param list List
     * @param <T> T
     * @return Item
     */
    public static <T> T popLastItem(List<T> list){
        int index = list.size() - 1;
        T last = list.get(index);
        list.remove(index);
        return last;
    }
}
