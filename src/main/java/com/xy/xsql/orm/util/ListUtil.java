package com.xy.xsql.orm.util;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class ListUtil {

    public static <T> List<T> fill(List<T> list, int size, Class<T> fillClass) throws IllegalAccessException, InstantiationException {
        int sizeMax = list.size();
        if(size > sizeMax){
            for(int index = sizeMax; index < size; index++){
                list.add(index, fillClass.newInstance());
            }
        }
        return list;
    }
}
