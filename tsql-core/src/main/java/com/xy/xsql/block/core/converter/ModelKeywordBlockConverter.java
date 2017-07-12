package com.xy.xsql.block.core.converter;

import com.google.common.base.Strings;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.core.printer.PrintAdapter;
import com.xy.xsql.block.exception.BlockStructureCorrectException;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.KeywordListBlock;
import com.xy.xsql.core.builder.BaseBuilder;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 */
public class ModelKeywordBlockConverter<MODEL>
        implements BaseBuilder<MODEL,KeywordListBlock> {


    @Override
    public KeywordListBlock build(MODEL model){
        BlockMeta meta = getHideMeta(model);

        Context context = new Context();
        build(meta,model,context);
        return new KeywordListBlock(context.list);
    }

    /**
     * build
     * @param meta meta
     * @param model context
     */
    public void build(BlockMeta meta, Object model, Context context){

        //Optional just return
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalFilter();
            if(optionalPredicate == null){
                throw new RuntimeException(new BlockStructureCorrectException(meta,
                        BlockStructureCorrectException.StructureCorrect.OPTION_FILTER_MISS));
            }
            //noinspection unchecked
            if(optionalPredicate.test(model)){
                return;
            }
        }else if(model == null){
            throw new RuntimeException(new BlockStructureCorrectException(meta,
                    BlockStructureCorrectException.StructureCorrect.CONTEXT_MISS));
        }


        //format
        if(meta.getFormat() != null){
            context.setNewLine(meta.getFormat().isNewLine());
            context.addLevel(meta.getFormat().getIndentation());
        }




        //data
        if(meta.isReference()){
            //Reference
            BlockMeta refMeta;
            if(meta.isReferenceClass()){
                refMeta = BlockManager
                        .INSTANCE
//                        .target(ModelMetaBlockConverter.class)
//                        .get(meta.getRefClass())
//                        .meta();
                        .getTypeBlockConverterByConverterType(meta.getRefClass())
                        .meta();
            }else{
                refMeta = meta.getRefMeta();
            }
            Object referenceContext = meta.getContext(model);

            if(meta.isList() &&
                    referenceContext instanceof List){
                //List
                //noinspection unchecked
                build(refMeta, (List) referenceContext, ", ", context);
            }else if(meta.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                //noinspection unchecked
                build(refMeta, (List) referenceContext, " ", context);
            }else{
                build(refMeta, referenceContext, context);
            }
        }else if(meta.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : meta.getCasePredicate()){
                //noinspection unchecked
                if(p.test(model)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    build(exclusiveMeta, model, context);
                    index = -1;
                    break;
                }
                index++;
            }
            if(index != -1){
                if(!BlockManager.INSTANCE.checkTypeBlockConverter(model.getClass())){
                    throw new RuntimeException(new BlockStructureCorrectException(meta,
                            BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
                }
                BlockMeta hiddenMeta = BlockManager
                        .INSTANCE
                        .getTypeBlockConverter(model.getClass())
                        .meta();

                build(hiddenMeta,model,context);
            }
        }else if(meta.getSub() != null){
            //Virtual
            if(meta.isList() ||
                    meta.isRepeat()){
                if(meta.getSub().size() != 1){
                    throw new RuntimeException(new BlockStructureCorrectException(meta,
                            BlockStructureCorrectException.StructureCorrect.COLLECTION_META_AMOUNT_ERROR));
                }
                BlockMeta itemMeta = meta.getSub().get(0);
                Object data = meta.getContext(model);
                if(!(data instanceof List)){
                    throw new RuntimeException(new BlockStructureCorrectException(meta,
                            BlockStructureCorrectException.StructureCorrect.COLLECTION_CONTEXT_MUST_LIST));
                }
                //noinspection unchecked
                List<Object> listContext = (List)data;
                String delimiter = null;
                if(meta.isList()){
                    delimiter = ", ";
                }else if(meta.isRepeat()) {
                    delimiter = " ";
                }
                build(itemMeta, listContext, delimiter, context);
            }else{
                build(meta.getSub(), model, " ", context);
            }
        }else{
            //Data
            if(meta.isKeyword()){
                //Keyword
                String blockString = meta.getData().toString();
//                String start = Strings.repeat(" ",cache.getLevel());
//                cache.add(start + blockString);
                context.add(blockString);
            }else{
                Object data = meta.getDataOrGetterData(model);
                if(data == null){
                    throw new RuntimeException(new BlockStructureCorrectException(meta,
                            BlockStructureCorrectException.StructureCorrect.NO_DATA));
                }
                if(BlockManager
                        .INSTANCE
                        .checkTypeBlockConverter(data.getClass())){
                    BlockMeta hiddenMeta = BlockManager
                            .INSTANCE
                            .getTypeBlockConverter(data.getClass())
                            .meta();

                    build(hiddenMeta,data,context);
                }else if(data instanceof List){
                    //noinspection unchecked
                    List<Object> listData = (List)data;
                    String delimiter;
                    if(meta.isList()){
                        delimiter = ", ";
                    }else if(meta.isRepeat()) {
                        delimiter = " ";
                    }else{
                        throw new RuntimeException(new BlockStructureCorrectException(meta,
                                BlockStructureCorrectException.StructureCorrect.COLLECTION_DATA_CANT_FIND_BLOCK_META));
                    }
                    if(listData.size() <= 0){
                        throw new RuntimeException(new BlockStructureCorrectException(meta,
                                BlockStructureCorrectException.StructureCorrect.COLLECTION_CONTEXT_MISS));
                    }
                    Object itemData = listData.get(0);
                    if(BlockManager
                            .INSTANCE
                            .checkTypeBlockConverter(itemData.getClass())){
                        BlockMeta hiddenMeta = BlockManager
                                .INSTANCE
                                .getTypeBlockConverter(itemData.getClass())
                                .meta();

                        build(hiddenMeta, listData, delimiter, context);
                    }else{
//                        String start = Strings.repeat(" ",cache.getLevel());
                        List<String> listTemp = listData
                                .stream()
                                .map(Objects::toString)
                                //joining
                                .flatMap(string -> Stream.concat(
//                                        Stream.of(start + delimiter),
//                                        Stream.of(start + string)
                                        Stream.of(delimiter),
                                        Stream.of(string)
                                ))
                                .skip(1)
                                .collect(Collectors.toList());
                        context.addAll(listTemp);
                    }
                }else{
//                    String start = Strings.repeat(" ",cache.getLevel());
//                    cache.add(start + data.toString());
                    context.add(data.toString());
                }
            }
        }


//        cache.subLevel(meta.getFormatLevel());
    }

    /**
     * build context list
     * @param itemMeta meta
     * @param listModel context list
     * @param delimiter delimiter
     * @param context out
     */
    public void build(BlockMeta itemMeta, List<Object> listModel, String delimiter, Context context) {
//        List<String> listTemp = listContext
//                        .stream()
//                        .map(context -> {
//                            Context cacheSub = new Context().withLevel(cache.level);
//                            build(itemMeta, context, cacheSub);
//                            return cacheSub.getList();
//                        })
//                        .filter(stringList -> !stringList.isEmpty())
//                        //joining
//                        .flatMap(stringList -> Stream.concat(
//                                Stream.of(delimiter),
//                                stringList.stream()))
//                        .skip(1)
//                        .collect(Collectors.toList());
//        cache.addAll(listTemp);

        List<String> listTemp = listModel
                .stream()
                .map(model -> {
                    Context cacheSub = new Context().withLevel(context.level);
                    build(itemMeta, model, cacheSub);
                    return cacheSub;
                })
                .filter(cacheSub -> !cacheSub.isEmpty())
                //joining
                .flatMap(cacheSub -> {
                    String delimiterFormat = delimiter;
                    if(cacheSub.isNewLine()){
                        String start = Strings.repeat(cacheSub.getIndentation(),cacheSub.getSafeLevel());
                        delimiterFormat = "\n" + start;
                    }
                    return Stream.concat(
                            Stream.of(delimiterFormat),
                            cacheSub.getList().stream());
//                    Stream.concat(
//                        Stream.of(delimiter),
//                        cacheSub.getList().stream())
                })
                .skip(1)
                .collect(Collectors.toList());
        context.addAll(listTemp);
    }

    /**
     * build meta list
     * @param listMeta meta list
     * @param itemModel context
     * @param delimiter delimiter
     * @param context out
     */
    public void build(List<BlockMeta> listMeta, Object itemModel, String delimiter, Context context) {
//        List<String> listTemp = listMeta
//                .stream()
//                .map(meta -> {
//                    Context cacheSub = new Context().withLevel(cache.level);
//                    build(meta, itemContext, cacheSub);
//                    return cacheSub.getList();
//                })
//                .filter(stringList -> !stringList.isEmpty())
//                //joining
//                .flatMap(stringList -> Stream.concat(
//                        Stream.of(delimiter),
//                        stringList.stream()))
//                .skip(1)
//                .collect(Collectors.toList());
//        cache.addAll(listTemp);

        List<String> listTemp = listMeta
                .stream()
                .map(meta -> {
                    Context cacheSub = new Context().withLevel(context.level);
                    build(meta, itemModel, cacheSub);
                    return cacheSub;
                })
                .filter(cacheSub -> !cacheSub.isEmpty())
                //joining
                .flatMap(cacheSub -> {
                    String delimiterFormat = delimiter;
                    if(cacheSub.isNewLine()){
                        String start = Strings.repeat(cacheSub.getIndentation(),cacheSub.getSafeLevel());
                        delimiterFormat = "\n" + start;
                    }
                    return Stream.concat(
                            Stream.of(delimiterFormat),
                            cacheSub.getList().stream());
                })
                .skip(1)
                .collect(Collectors.toList());
        context.addAll(listTemp);
    }

    /**
     * Get meta form BlockManager
     * @param model context
     * @return meta
     */
    private BlockMeta getHideMeta(Object model){
        if(!BlockManager.INSTANCE.checkTypeBlockConverter(model.getClass())){
            throw new RuntimeException(new BlockStructureCorrectException(null,
                    BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
        }

        return BlockManager
                .INSTANCE
                .getTypeBlockConverter(model.getClass())
                .meta();
    }


    public static PrintAdapter convert(Object model){
        KeywordListBlock block = new ModelKeywordBlockConverter<>()
                .build(model);

        return new PrintAdapter<KeywordListBlock,StringWriter>()
                .withBlock(block);
    }

    @SuppressWarnings({"WeakerAccess", "SameParameterValue", "unused"})
    public static class Context {

        private List<String> list;
        private boolean newLine;
        private int level;
        private String indentation = "\t";

        public Context(){
            list = new ArrayList<>();
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public boolean isNewLine() {
            return newLine;
        }

        public void setNewLine(boolean newLine) {
            this.newLine = newLine;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getIndentation() {
            return indentation;
        }

        public void setIndentation(String indentation) {
            this.indentation = indentation;
        }

        public Context withLevel(int level){
            this.level = level;
            return this;
        }

        /*

         */

        public int getSafeLevel() {
            return level < 0 ? 0 : level;
        }

        public void addLevel(int formatLevel) {
            level = level + formatLevel;
        }

        public void subLevel(int formatLevel) {
            level = level - formatLevel;
        }

        public void add(String s) {
//            String start = Strings.repeat(indentation,this.level);
//            list.add(start + s);
            list.add(s);
        }

        public void addAll(List<String> listTemp) {
//            listTemp = listTemp
//                    .stream()
//                    .map(s -> {
//                        String start = Strings.repeat(indentation,this.level);
//                        return start + s;
//                    })
//                    .collect(Collectors.toList());
            list.addAll(listTemp);
        }

        public void addAll(List<String> listTemp, int level) {
            listTemp = listTemp
                    .stream()
                    .map(s -> {
                        String start = Strings.repeat(indentation,level);
                        return start + s;
                    })
                    .collect(Collectors.toList());
            list.addAll(listTemp);
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }
    }

}
