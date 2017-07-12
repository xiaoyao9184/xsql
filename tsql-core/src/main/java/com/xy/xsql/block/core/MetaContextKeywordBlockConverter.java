package com.xy.xsql.block.core;

import com.google.common.base.Strings;
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
public class MetaContextKeywordBlockConverter<CONTEXT>
        implements BaseBuilder<CONTEXT,KeywordListBlock> {


    @Override
    public KeywordListBlock build(CONTEXT context){
        BlockMeta meta = getHideMeta(context);

        Context cache = new Context();
        build(meta,context,cache);
        return new KeywordListBlock(cache.list);
    }

    /**
     * build
     * @param meta meta
     * @param context context
     */
    public void build(BlockMeta meta, Object context, Context cache){

        //Optional just return
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalFilter();
            if(optionalPredicate == null){
                throw new RuntimeException(new BlockStructureCorrectException(meta,
                        BlockStructureCorrectException.StructureCorrect.OPTION_FILTER_MISS));
            }
            //noinspection unchecked
            if(optionalPredicate.test(context)){
                return;
            }
        }else if(context == null){
            throw new RuntimeException(new BlockStructureCorrectException(meta,
                    BlockStructureCorrectException.StructureCorrect.CONTEXT_MISS));
        }


        //format
        if(meta.getFormat() != null){
            cache.setNewLine(meta.getFormat().isNewLine());
            cache.addLevel(meta.getFormat().getIndentation());
        }




        //data
        if(meta.isReference()){
            //Reference
            BlockMeta refMeta;
            if(meta.isReferenceClass()){
                refMeta = BlockManager
                        .INSTANCE
                        .getTypeBlockConverterByConverterType(meta.getRefClass())
                        .meta();
            }else{
                refMeta = meta.getRefMeta();
            }
            Object referenceContext = meta.getContext(context);

            if(meta.isList() &&
                    referenceContext instanceof List){
                //List
                //noinspection unchecked
                build(refMeta, (List) referenceContext, ", ", cache);
            }else if(meta.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                //noinspection unchecked
                build(refMeta, (List) referenceContext, " ", cache);
            }else{
                build(refMeta, referenceContext, cache);
            }
        }else if(meta.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : meta.getCasePredicate()){
                //noinspection unchecked
                if(p.test(context)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    build(exclusiveMeta, context, cache);
                    index = -1;
                    break;
                }
                index++;
            }
            if(index != -1){
                if(!BlockManager.INSTANCE.checkTypeBlockConverter(context.getClass())){
                    throw new RuntimeException(new BlockStructureCorrectException(meta,
                            BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
                }
                BlockMeta hiddenMeta = BlockManager
                        .INSTANCE
                        .getTypeBlockConverter(context.getClass())
                        .meta();

                build(hiddenMeta,context,cache);
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
                Object data = meta.getContext(context);
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
                build(itemMeta, listContext, delimiter, cache);
            }else{
                build(meta.getSub(), context, " ", cache);
            }
        }else{
            //Data
            if(meta.isKeyword()){
                //Keyword
                String blockString = meta.getData().toString();
//                String start = Strings.repeat(" ",cache.getLevel());
//                cache.add(start + blockString);
                cache.add(blockString);
            }else{
                Object data = meta.getDataOrGetterData(context);
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

                    build(hiddenMeta,data,cache);
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

                        build(hiddenMeta, listData, delimiter, cache);
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
                        cache.addAll(listTemp);
                    }
                }else{
//                    String start = Strings.repeat(" ",cache.getLevel());
//                    cache.add(start + data.toString());
                    cache.add(data.toString());
                }
            }
        }


//        cache.subLevel(meta.getFormatLevel());
    }

    /**
     * build context list
     * @param itemMeta meta
     * @param listContext context list
     * @param delimiter delimiter
     * @param cache out
     */
    public void build(BlockMeta itemMeta, List<Object> listContext, String delimiter, Context cache) {
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

        List<String> listTemp = listContext
                .stream()
                .map(context -> {
                    Context cacheSub = new Context().withLevel(cache.level);
                    build(itemMeta, context, cacheSub);
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
        cache.addAll(listTemp);
    }

    /**
     * build meta list
     * @param listMeta meta list
     * @param itemContext context
     * @param delimiter delimiter
     * @param cache out
     */
    public void build(List<BlockMeta> listMeta, Object itemContext, String delimiter, Context cache) {
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
                    Context cacheSub = new Context().withLevel(cache.level);
                    build(meta, itemContext, cacheSub);
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
        cache.addAll(listTemp);
    }

    /**
     * Get meta form BlockManager
     * @param context context
     * @return meta
     */
    private BlockMeta getHideMeta(Object context){
        if(!BlockManager.INSTANCE.checkTypeBlockConverter(context.getClass())){
            throw new RuntimeException(new BlockStructureCorrectException(null,
                    BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
        }

        return BlockManager
                .INSTANCE
                .getTypeBlockConverter(context.getClass())
                .meta();
    }


    public static PrintAdapter convert(Object context){
        KeywordListBlock block = new MetaContextKeywordBlockConverter<>()
                .build(context);

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
