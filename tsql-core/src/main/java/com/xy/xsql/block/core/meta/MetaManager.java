package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.core.IndexClassMapper;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.lambda.Getter;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/7/13.
 */
public enum MetaManager {
    INSTANCE;

    private static IndexClassMapper<Type,ModelMetaBlockConverter> type2converter;
    private static IndexClassMapper<ModelMetaBlockConverter,Type> converter2type;
    private static IndexClassMapper<Type,BlockMeta> type2meta;
    private static IndexClassMapper<Type,BlockMeta> converter2meta;


    static {
        type2converter = new IndexClassMapper<>();
        converter2type = new IndexClassMapper<>();
        type2meta = new IndexClassMapper<>();
        converter2meta = new IndexClassMapper<>();
        BlockManager.INSTANCE.scan("com.xy.xsql.block.tsql.core");
    }

    public static void register(Type type, ModelMetaBlockConverter converter){
        type2converter.map(type,converter);
        converter2type.map(converter,type);
        type2meta.map(type,converter.meta());
        converter2meta.map(converter.getClass(),converter.meta());
    }

    public static void scan(String basePackage){
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends ModelMetaBlockConverter>> subTypes = reflections.getSubTypesOf(ModelMetaBlockConverter.class);

        Map<Type,ModelMetaBlockConverter> temp = new HashMap<>();

        subTypes
                .forEach(b -> {
                    Type[] types = b.getGenericInterfaces();
                    Stream.of(types)
                            .filter(t -> {
                                return t instanceof ParameterizedType;
                            })
                            .map(t -> (ParameterizedType)t)
                            .filter(pt -> {
                                return ModelMetaBlockConverter.class.equals(pt.getRawType());
                            })
                            .filter(pt -> {
                                return pt.getActualTypeArguments() != null &&
                                        pt.getActualTypeArguments().length == 1;
                            })
                            .findFirst()
                            .ifPresent(pt -> {
                                try {
                                    Type type = pt.getActualTypeArguments()[0];
                                    ModelMetaBlockConverter obj = b.newInstance();
                                    temp.put(type,obj);
                                } catch (InstantiationException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            });
                });

        temp.entrySet()
                .forEach(kv -> {
                    register(kv.getKey(),kv.getValue());
                });

    }


    public static Getter<Optional<BlockMeta>> byModel(Class model){
        return () -> {
            if(type2meta.check(model)){
                return Optional.of(type2meta.get(model));
            }
            return Optional.empty();
        };

    }

    public static Getter<Optional<BlockMeta>> byConverter(Class converter){
        return () -> {
            if(converter2meta.check(converter)){
                return Optional.of(type2meta.get(converter));
            }
            return Optional.empty();
        };
    }
}
