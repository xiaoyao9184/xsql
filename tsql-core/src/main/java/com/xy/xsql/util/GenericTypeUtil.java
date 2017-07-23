package com.xy.xsql.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/7/23.
 */
public class GenericTypeUtil {

    /**
     * getGenericArguments
     * @param clazz class
     * @param interfaces interface class
     * @return Type Stream
     */
    public static Stream<Type> getGenericArguments(Class clazz, Class interfaces){
        Type[] types = clazz.getGenericInterfaces();
        return Stream.of(types)
                .filter(t -> {
                    return t instanceof ParameterizedType;
                })
                .map(t -> (ParameterizedType)t)
                .filter(pt -> {
                    return interfaces.equals(pt.getRawType());
                })
                .filter(pt -> {
                    return pt.getActualTypeArguments() != null &&
                            pt.getActualTypeArguments().length > 0;
                })
                .flatMap(pt -> Stream.of(pt.getActualTypeArguments()));
    }
}
