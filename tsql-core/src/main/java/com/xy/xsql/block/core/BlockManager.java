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

    private static Map<Type,MetaContextBlockConverter> typeBlockBuilderMap;
    private static Map<Type,MetaContextBlockConverter> converterTypeBlockConverterMap;

    static {
        typeBlockBuilderMap = new HashMap<>();
        converterTypeBlockConverterMap = new HashMap<>();
        BlockManager.INSTANCE.scan("com.xy.xsql.block.tsql.core");
    }


    public void register(Type clazz, MetaContextBlockConverter metaContextBlockConverter){
        //TODO same type
        typeBlockBuilderMap.put(clazz, metaContextBlockConverter);
        converterTypeBlockConverterMap.put(metaContextBlockConverter.getClass(), metaContextBlockConverter);
    }

    public void scan(String basePackage){
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends MetaContextBlockConverter>> subTypes = reflections.getSubTypesOf(MetaContextBlockConverter.class);

        subTypes
                .forEach(b -> {
                    Type[] types = b.getGenericInterfaces();
                    Stream.of(types)
                            .filter(t -> {
                                return t instanceof ParameterizedType;
                            })
                            .map(t -> (ParameterizedType)t)
                            .filter(pt -> {
                                return MetaContextBlockConverter.class.equals(pt.getRawType());
                            })
                            .filter(pt -> {
                                return pt.getActualTypeArguments() != null &&
                                        pt.getActualTypeArguments().length == 1;
                            })
                            .findFirst()
                            .ifPresent(pt -> {
                                try {
                                    Type type = pt.getActualTypeArguments()[0];
                                    MetaContextBlockConverter obj = b.newInstance();
                                    register(type,obj);
                                } catch (InstantiationException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            });

                });

    }



    public Map<Type,MetaContextBlockConverter> getTypeBlockConverterMap(){
        return typeBlockBuilderMap;
    }

    public MetaContextBlockConverter getTypeBlockConverter(Type type) {
        return typeBlockBuilderMap.get(type);
    }

    public MetaContextBlockConverter getTypeBlockConverterByConverterType(Class refClass) {
        return converterTypeBlockConverterMap.get(refClass);
    }

    public boolean checkTypeBlockConverter(Type type) {
        return typeBlockBuilderMap.containsKey(type);
    }
}
