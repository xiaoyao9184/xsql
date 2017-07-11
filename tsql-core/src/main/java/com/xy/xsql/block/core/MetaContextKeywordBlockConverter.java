package com.xy.xsql.block.core;

import com.xy.xsql.block.exception.BlockStructureCorrectException;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.KeywordBlock;
import com.xy.xsql.block.model.MetaContextBlock;
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
        implements BaseBuilder<CONTEXT,List<KeywordBlock>> {


    private List<KeywordBlock> list = new ArrayList<>();

    @Override
    public List<KeywordBlock> build(CONTEXT context){
        BlockMeta meta = getHideMeta(context);

        build(meta,context,list);
        return list;
    }


    public List<KeywordBlock> build(BlockMeta meta, Object context, List<KeywordBlock> list){

        //Optional just return
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalFilter();
            if(optionalPredicate == null){
                throw new RuntimeException(new BlockStructureCorrectException(meta,
                        BlockStructureCorrectException.StructureCorrect.OPTION_FILTER_MISS));
            }
            if(optionalPredicate.test(context)){
                return list;
            }
        }else if(context == null){
            throw new RuntimeException(new BlockStructureCorrectException(meta,
                    BlockStructureCorrectException.StructureCorrect.CONTEXT_MISS));
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
                build(refMeta, (List) referenceContext,"\n, ");
            }else if(meta.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                build(refMeta, (List) referenceContext," ");
            }else{
                build(refMeta, referenceContext, list);
            }
        }else if(meta.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : meta.getCasePredicate()){
                if(p.test(context)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    build(exclusiveMeta, context, list);
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

                build(hiddenMeta,context,list);
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
                List<Object> listContext = (List)data;
                String delimiter = null;
                if(meta.isList()){
                    delimiter = "\n, ";
                }else if(meta.isRepeat()) {
                    delimiter = "\n ";
                }
                build(itemMeta, listContext, delimiter);
            }else{
                for (BlockMeta subMeta : meta.getSub()) {
                    build(subMeta, context, list);
                }
            }
        }else{
            //Data
            if(meta.isKeyword()){
                //Keyword
                String blockString = meta.getData().toString();
                list.add(new KeywordBlock(blockString));
                return list;
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

                    build(hiddenMeta,data,list);
                }else if(data instanceof List){
                    List<Object> listData = (List)data;
                    String delimiter;
                    if(meta.isList()){
                        delimiter = "\n, ";
                    }else if(meta.isRepeat()) {
                        delimiter = "\n ";
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

                        build(hiddenMeta,listData,delimiter);
                    }else{
                        listData
                                .stream()
                                .map(Objects::toString)
                                .map(KeywordBlock::new)
                                .flatMap(b -> Stream.concat(
                                        Stream.of(new KeywordBlock(delimiter)),
                                        Stream.of(b)
                                ))
                                .skip(1)
                                .forEach(list::add);
                    }
                }else{
                    list.add(new KeywordBlock(data.toString()));
                }
            }
        }

        return list;
    }

    /**
     * print context list
     * @param itemMeta meta
     * @param listContext context list
     * @param delimiter delimiter
     */
    public void build(BlockMeta itemMeta, List<Object> listContext, String delimiter) {
        KeywordBlock delimiterBlock = new KeywordBlock(delimiter);
        listContext
                .stream()
                .flatMap(context -> Stream.concat(
                        Stream.of(delimiterBlock),
                        build(itemMeta, context, new ArrayList<>())
                                .stream()))
                .skip(1)
                .forEach(b -> list.add(b));
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
}
