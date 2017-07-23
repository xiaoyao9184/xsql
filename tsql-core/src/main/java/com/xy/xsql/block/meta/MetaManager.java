package com.xy.xsql.block.meta;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.util.GenericTypeUtil;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 * Created by xiaoyao9184 on 2017/7/13.
 */
@SuppressWarnings("SameParameterValue")
public enum MetaManager {
    INSTANCE;

    private Logger logger;
    private SourceTargetMapper<Type,Type> model2converter;
    private SourceTargetMapper<Type,BlockMeta> model2meta;
    private SourceTargetMapper<Type,BlockMeta> converter2meta;

    MetaManager(){
        logger = LoggerFactory.getLogger(MetaManager.class);
        model2converter = new SourceTargetMapper<>();
        model2meta = new SourceTargetMapper<>();
        converter2meta = new SourceTargetMapper<>();
    }

    private void register(Type typeModel, Type typeConverter, BlockMeta meta){
        model2converter.map(typeModel,typeConverter);
        model2meta.map(typeModel,meta);
        converter2meta.map(typeConverter,meta);
    }

    public void register(Class<? extends ModelMetaBlockConverter> metaConverter){
        Optional<Type> optional = GenericTypeUtil
                .getGenericArguments(metaConverter,ModelMetaBlockConverter.class)
                .findFirst();

        if(!optional.isPresent()){
            logger.debug("Class is ignored because it non-compliant: " + metaConverter.getName());
        }
        optional.ifPresent(type -> {
            if(model2converter.checkLeft(type)){
                logger.debug("The same type is handled by multiple ModelMetaBlockConverter: " +
                        metaConverter.getName() + "-" +
                        model2converter.getByLeft(type).getClass().getName());
            }else{
                try {
                    ModelMetaBlockConverter obj = metaConverter.newInstance();
                    register(type,metaConverter,obj.meta());
                    BlockManager
                            .byConverter()
                            .register(type,obj);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressWarnings("CodeBlock2Expr")
    public void scan(String basePackage){
        new Reflections(basePackage)
                .getSubTypesOf(ModelMetaBlockConverter.class)
                .forEach(this::register);
    }




    static{
        MetaManager.INSTANCE.scan("com.xy.xsql.block.tsql.core");
    }

    /**
     * Get by model
     * @param model model
     * @return Optional
     */
    public static MetaHolder byModel(Class model){
        return new MetaHolder(() -> {
            if(MetaManager.INSTANCE.model2meta.checkLeft(model)){
                return Optional.of(MetaManager.INSTANCE.model2meta.getByLeft(model));
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
            if(MetaManager.INSTANCE.converter2meta.checkLeft(converter)){
                return Optional.of(MetaManager.INSTANCE.converter2meta.getByLeft(converter));
            }
            return Optional.empty();
        });
    }


    /**
     * Hold meta
     */
    @SuppressWarnings("WeakerAccess")
    public static class MetaHolder{
        private Getter<Optional<BlockMeta>> getter;

        public MetaHolder(Getter<Optional<BlockMeta>> getter){
            this.getter = getter;
        }

        /**
         * Get meta
         * @return BlockMeta
         */
        public Optional<BlockMeta> meta(){
            return getter.get();
        }

        /**
         * Print syntax
         * @return StringWriter
         */
        public String print(){
            return getter.get()
                    .map(blockMeta ->
                            new ModelMetaBlockPrinter().printMeta(blockMeta).toString())
                    .orElse(null);
        }

        /**
         * Type of model
         * @return Type
         */
        public Type model(){
            return getter.get()
                    .filter(blockMeta -> MetaManager.INSTANCE.model2meta.checkRight(blockMeta))
                    .map(blockMeta -> MetaManager.INSTANCE.model2meta.getByRight(blockMeta))
                    .orElse(null);
        }

        /**
         * Type of converter
         * @return Type
         */
        public Type converter(){
            return getter.get()
                    .filter(blockMeta -> MetaManager.INSTANCE.converter2meta.checkRight(blockMeta))
                    .map(blockMeta -> MetaManager.INSTANCE.converter2meta.getByRight(blockMeta))
                    .orElse(null);
        }
    }
}
