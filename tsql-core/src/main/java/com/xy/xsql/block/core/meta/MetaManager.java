package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.core.SourceTargetMapper;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.lambda.Getter;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
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
@SuppressWarnings("SameParameterValue")
public enum MetaManager {
    INSTANCE;

    private static Logger logger = LoggerFactory.getLogger(MetaManager.class);
    private static SourceTargetMapper<Type,Type> model2converter;
    private static SourceTargetMapper<Type,BlockMeta> model2meta;
    private static SourceTargetMapper<Type,BlockMeta> converter2meta;


    static {
        model2converter = new SourceTargetMapper<>();
        model2meta = new SourceTargetMapper<>();
        converter2meta = new SourceTargetMapper<>();
        MetaManager.scan("com.xy.xsql.block.tsql.core");
    }

    public static void register(Type typeModel, Type typeConverter, BlockMeta meta){
        model2converter.map(typeModel,typeConverter);
        model2meta.map(typeModel,meta);
        converter2meta.map(typeConverter,meta);
    }

    @SuppressWarnings("CodeBlock2Expr")
    public static void scan(String basePackage){
        Reflections reflections = new Reflections(basePackage);

        Set<Class<? extends ModelMetaBlockConverter>> subTypes = reflections.getSubTypesOf(ModelMetaBlockConverter.class);

        Map<Type,ModelMetaBlockConverter> temp = new HashMap<>();

        subTypes
                .forEach(b -> {
                    Type[] types = b.getGenericInterfaces();
                    Optional<ParameterizedType> optional = Stream.of(types)
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
                            .findFirst();

                    optional
                            .ifPresent(pt -> {
                                try {
                                    Type type = pt.getActualTypeArguments()[0];
                                    ModelMetaBlockConverter obj = b.newInstance();

                                    if(temp.containsKey(type)){
                                        logger.debug("The same type is handled by multiple ModelMetaBlockConverter: " +
                                                b.getName() + "-" +
                                                temp.get(type).getClass().getName());
                                    }else{
                                        temp.put(type,obj);
                                    }
                                } catch (InstantiationException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            });
                    if(!optional.isPresent()){
                        logger.debug("Class is ignored because it non-compliant: " + b.getName());
                    }
                });

        temp.forEach((type, modelMetaBlockConverter) -> register(type,modelMetaBlockConverter.getClass(),modelMetaBlockConverter.meta()));

    }


    /**
     * Get by model
     * @param model model
     * @return Optional
     */
    public static MetaHolder byModel(Class model){
        return new MetaHolder(() -> {
            if(model2meta.checkLeft(model)){
                return Optional.of(model2meta.getByLeft(model));
            }
            return Optional.empty();
        });
    }

    /**
     * Get by converter
     * @param converter converter
     * @return Optional
     */
    public static MetaHolder byConverter(Class converter){
        return new MetaHolder(() -> {
            if(converter2meta.checkLeft(converter)){
                return Optional.of(converter2meta.getByLeft(converter));
            }
            return Optional.empty();
        });
    }


    @SuppressWarnings("WeakerAccess")
    public static class MetaHolder{
        public Getter<Optional<BlockMeta>> getter;

        public MetaHolder(Getter<Optional<BlockMeta>> getter){
            this.getter = getter;
        }

        public Optional<BlockMeta> get(){
            return getter.get();
        }

        public StringWriter print(){
            return getter.get()
                    .map(blockMeta -> new ModelMetaBlockPrinter()
                            .printMeta(blockMeta))
                    .orElse(null);
        }

        public Type model(){
            return getter.get()
                    .filter(blockMeta -> model2meta.checkRight(blockMeta))
                    .map(blockMeta -> model2meta.getByRight(blockMeta))
                    .orElse(null);
        }

        public Type converter(){
            return getter.get()
                    .filter(blockMeta -> converter2meta.checkRight(blockMeta))
                    .map(blockMeta -> converter2meta.getByRight(blockMeta))
                    .orElse(null);
        }
    }
}
