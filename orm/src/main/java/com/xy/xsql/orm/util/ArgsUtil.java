package com.xy.xsql.orm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/11/24.
 */
public class ArgsUtil {

    /**
     * Expand Object if Object is Iterable
     * @param removeNull Remove Null Object
     * @param args Object Args
     * @return Object Array
     */
    public static Object[] expand(boolean removeNull, Object... args){
        List<Object> list = new ArrayList<>();
        for (Object arg : args) {
            if(arg != null &&
                    arg instanceof Iterable){
                    Iterable iterable = (Iterable)arg;
                    for (Object obj: iterable) {
                        list.add(obj);
                    }
            }else if(arg != null){
                list.add(arg);
            }else if(!removeNull){
                //noinspection ConstantConditions
                list.add(arg);
            }
        }
        return list.toArray();
    }

}
