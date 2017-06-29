package com.xy.xsql.block.core;

import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public enum BlockManager {
    INSTANCE;

    private static Map<Type,ReferenceBlockConverter> typeBlockBuilderMap;
    private static Map<Type,ReferenceBlockConverter> converterTypeBlockConverterMap;

    static {
        typeBlockBuilderMap = new HashMap<>();
        converterTypeBlockConverterMap = new HashMap<>();
        BlockManager.INSTANCE.scan("com.xy.xsql.block.tsql.core");
    }


    public void register(Type clazz, ReferenceBlockConverter referenceBlockConverter){
        typeBlockBuilderMap.put(clazz, referenceBlockConverter);
        converterTypeBlockConverterMap.put(referenceBlockConverter.getClass(), referenceBlockConverter);
    }

    public void scan(String basePackage){
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends ReferenceBlockConverter>> subTypes = reflections.getSubTypesOf(ReferenceBlockConverter.class);

        subTypes
                .forEach(b -> {
                    Type[] types = b.getGenericInterfaces();
                    Stream.of(types)
                            .filter(t -> {
                                return t instanceof ParameterizedType;
                            })
                            .map(t -> (ParameterizedType)t)
                            .filter(pt -> {
                                return ReferenceBlockConverter.class.equals(pt.getRawType());
                            })
                            .filter(pt -> {
                                return pt.getActualTypeArguments() != null &&
                                        pt.getActualTypeArguments().length == 1;
                            })
                            .findFirst()
                            .ifPresent(pt -> {
                                try {
                                    Type type = pt.getActualTypeArguments()[0];
                                    ReferenceBlockConverter obj = b.newInstance();
                                    register(type,obj);
                                } catch (InstantiationException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            });

                });

    }



    public Map<Type,ReferenceBlockConverter> getTypeBlockConverterMap(){
        return typeBlockBuilderMap;
    }

    public ReferenceBlockConverter getTypeBlockConverter(Type type) {
        return typeBlockBuilderMap.get(type);
    }

    public ReferenceBlockConverter getTypeBlockConverterByConverterType(Class refClass) {
        return converterTypeBlockConverterMap.get(refClass);
    }

    public boolean checkTypeBlockConverter(Type type) {
        return typeBlockBuilderMap.containsKey(type);
    }
}
