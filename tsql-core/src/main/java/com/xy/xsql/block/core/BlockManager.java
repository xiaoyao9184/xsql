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

    private static Map<Class<?>,BlockConverter> typeBlockBuilderMap;

    public void register(Class<?> clazz, BlockConverter blockConverter){
        typeBlockBuilderMap.put(clazz, blockConverter);
    }

    public void scan(String basePackage){
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends BlockConverter>> subTypes = reflections.getSubTypesOf(BlockConverter.class);

        subTypes.stream()
                .forEach(b -> {
                    Type[] types = b.getGenericInterfaces();
                    Stream.of(types)
                            .filter(t -> {
                                return t instanceof ParameterizedType;
                            })
                            .map(t -> (ParameterizedType)t)
                            .filter(pt -> {
                                return BlockConverter.class.equals(pt.getRawType());
                            })
                            .filter(pt -> {
                                return pt.getActualTypeArguments() != null &&
                                        pt.getActualTypeArguments().length == 1;
                            })
                            .findFirst()
                            .ifPresent(pt -> {
                                Class data = (Class) pt.getActualTypeArguments()[0];
                                try {
                                    BlockConverter obj = b.newInstance();
                                    typeBlockBuilderMap.put(data,obj);
                                } catch (InstantiationException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            });

                });

    }



    public Map<Class<?>,BlockConverter> getTypeBlockBuilderMap(){
        return typeBlockBuilderMap;
    }


    static {
        typeBlockBuilderMap = new HashMap<>();
        BlockManager.INSTANCE.scan("com.xy.xsql.block.tsql.core");
    }
}
