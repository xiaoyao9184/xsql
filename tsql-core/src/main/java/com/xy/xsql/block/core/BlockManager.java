package com.xy.xsql.block.core;

import com.xy.xsql.block.core.converter.BlockConverter;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.core.printer.BlockPrinter;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.core.mapper.SourceTargetMapper;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.util.GenericTypeUtil;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public enum BlockManager {
    INSTANCE;

    private Logger logger;
    private SourceTargetMapper<Type,BlockConverter> model2converter;
    private SourceTargetMapper<Type,BlockPrinter> block2printer;

    BlockManager() {
        logger = LoggerFactory.getLogger(BlockManager.class);
        model2converter = new SourceTargetMapper<>();
        block2printer = new SourceTargetMapper<>();
    }

    public void scan(String basePackage){
        byConverter().scan(basePackage);
        byPrinter().scan(basePackage);
    }

    public String print(Object model) {
        Block block = byConverter().convert(model);
        Objects.requireNonNull(block);
        return byPrinter().print(block);
    }




    static{
        BlockManager.INSTANCE.scan(BlockManager.class.getPackage().getName());
    }

    public static BlockManager init(Enum... instance) {
//        if()
//        instance.getClass().getEnumConstants()
        return BlockManager.INSTANCE;
    }


    public static ConverterHandler byConverter(){
        return new ConverterHandler();
    }

    public static PrinterHandler byPrinter(){
        return new PrinterHandler();
    }


    public static class ConverterHandler {

        @SuppressWarnings("unchecked")
        public Block convert(Object model){
            if(BlockManager.INSTANCE.model2converter.checkLeft(model.getClass())){
                return BlockManager.INSTANCE.model2converter
                        .getByLeft(model.getClass())
                        .convert(model);
            }
            return null;
        }



        public void register(Type type, ModelMetaBlockConverter converter) {
            if(BlockManager.INSTANCE.model2converter.checkLeft(type)){
                BlockManager.INSTANCE.logger.debug("The same type is handled by multiple BlockConverter: " +
                        type.getTypeName() + "-" +
                        BlockManager.INSTANCE.model2converter.getByLeft(type).getClass().getName());
            }else{
                BlockManager.INSTANCE.model2converter.map(type,converter);
            }
        }

        public void register(BlockConverter converter){
            GenericTypeUtil.getGenericArguments(converter.getClass(),BlockConverter.class)
                    .findFirst()
                    .ifPresent(type -> {
                        if(BlockManager.INSTANCE.model2converter.checkLeft(type)){
                            BlockManager.INSTANCE.logger.debug("The same type is handled by multiple BlockConverter: " +
                                    type.getTypeName() + "-" +
                                    BlockManager.INSTANCE.model2converter.getByLeft(type).getClass().getName());
                        }else{
                            BlockManager.INSTANCE.model2converter.map(type,converter);
                        }
                    });
        }

        public void register(Class<? extends BlockConverter> converter){
            GenericTypeUtil.getGenericArguments(converter,BlockConverter.class)
                    .findFirst()
                    .filter(type -> type instanceof Class)
                    .ifPresent(type -> {
                        if(BlockManager.INSTANCE.model2converter.checkLeft(type)){
                            BlockManager.INSTANCE.logger.debug("The same type is handled by multiple BlockConverter: " +
                                    type.getTypeName() + "-" +
                                    BlockManager.INSTANCE.model2converter.getByLeft(type).getClass().getName());
                        }else{
                            try {
                                BlockConverter blockConverter = converter.newInstance();
                                BlockManager.INSTANCE.model2converter.map(type,blockConverter);
                            } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        public void scan(String basePackage){
            new Reflections(basePackage)
                    .getSubTypesOf(BlockConverter.class)
                    .forEach(this::register);
        }
    }

    public static class PrinterHandler {

        @SuppressWarnings("unchecked")
        public <B extends Block> String print(B block){
            if(BlockManager.INSTANCE.block2printer.checkLeft(block.getClass())){
                return BlockManager.INSTANCE.block2printer
                        .getByLeft(block.getClass())
                        .print(block)
                        .toString();
            }
            return null;
        }

        public void register(BlockPrinter printer){
            GenericTypeUtil.getGenericArguments(printer.getClass(),BlockPrinter.class)
                    .findFirst()
                    .ifPresent(type -> {
                        if(BlockManager.INSTANCE.block2printer.checkLeft(type)){
                            BlockManager.INSTANCE.logger.debug("The same type is handled by multiple BlockPrinter: " +
                                    type.getTypeName() + "-" +
                                    BlockManager.INSTANCE.block2printer.getByLeft(type).getClass().getName());
                        }else{
                            BlockManager.INSTANCE.block2printer.map(type,printer);
                        }
                    });
        }

        public void register(Class<? extends BlockPrinter> printer){
            GenericTypeUtil.getGenericArguments(printer,BlockPrinter.class)
                    .findFirst()
                    .filter(type -> type instanceof Class)
                    .ifPresent(type -> {
                        if(BlockManager.INSTANCE.block2printer.checkLeft(type)){
                            BlockManager.INSTANCE.logger.debug("The same type is handled by multiple BlockPrinter: " +
                                    type.getTypeName() + "-" +
                                    BlockManager.INSTANCE.block2printer.getByLeft(type).getClass().getName());
                        }else{
                            try {
                                BlockPrinter blockPrinter = printer.newInstance();
                                BlockManager.INSTANCE.block2printer.map(type,blockPrinter);
                            } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        public void scan(String basePackage){
            new Reflections(basePackage)
                    .getSubTypesOf(BlockPrinter.class)
                    .forEach(this::register);
        }
    }

}
