package com.xy.xsql.block.core;

import com.xy.xsql.block.exception.BlockStructureCorrectException;
import com.xy.xsql.block.model.BlockMeta;

import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/6/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class MetaContextBlockPrinter {

    private StringWriter writer;

    public MetaContextBlockPrinter(){
        writer = new StringWriter();
    }


    /**
     * print meta
     * @param blockMeta meta
     * @return writer
     */
    public StringWriter printMeta(BlockMeta blockMeta) {
        return printMeta(blockMeta,true,writer);
    }

    /**
     * print meta
     * @param blockMeta meta list
     * @param printOverall printOverall
     * @param writer writer
     * @return writer
     */
    public StringWriter printMeta(BlockMeta blockMeta, boolean printOverall, StringWriter writer) {
        if(blockMeta.isOverall() &&
                printOverall) {
            //Syntax
            writer.append('<');
            writer.append(blockMeta.getName());
            writer.append("> ::=\n");
        }

        //start
        if(blockMeta.isStartNewLine()){
            writer.append("\n");
        }

        //style
        if(blockMeta.isOptional()){
            if(blockMeta.isHeadFootTakeLine()){
                writer.append("[\n");
            }else{
                writer.append("[ ");
            }
        }else if(blockMeta.isRequired()){
            if(blockMeta.isHeadFootTakeLine()){
                writer.append("{\n");
            }else{
                writer.append("{ ");
            }
        }else if(blockMeta.isHeadFootTakeLine()){
            writer.append("\n");
        }

        //data
        if(blockMeta.isKeyword()) {
            //Keyword
            writer.append(blockMeta.getData().toString());
        }else if(blockMeta.getName() != null &&
                blockMeta.isReferenceClass()){
            //Reference Name
            writer.append('<');
            writer.append(blockMeta.getName());
            writer.append('>');
        }else if(blockMeta.getName() != null &&
                !blockMeta.isOverall()){
            //Name
            writer.append(blockMeta.getName());
        }else if(blockMeta.isReferenceMeta()){
            //Reference Meta
            printMeta(blockMeta.getRefMeta(),false,writer);
        }else{
            //Sub
            String line = blockMeta.isEachSubTakeLine() ? "\n" : " ";

            if(blockMeta.isExclusive()){
                //Exclusive
                printMeta(blockMeta.getSub(),false,line + "| ",writer);
            }else if(blockMeta.isList()){
                //List
                if(blockMeta.isReference()){
                    writer.append('<');
                    printMeta(blockMeta.getSub(),false,line + ", ",writer);
                    if(blockMeta.isReference()){
                        writer.append('>');
                    }
                }else{

                }

            }else if(blockMeta.getSub() != null){
                if(blockMeta.isReference()){
                    writer.append('<');
                }
                //Repeat
                printMeta(blockMeta.getSub(),false,line,writer);
                if(blockMeta.isReference()){
                    writer.append('>');
                }
            }else{
//                throw new Exception("error block");
            }
        }


        //style
        if(blockMeta.isRequired()){
            if(blockMeta.isHeadFootTakeLine()){
                writer.append("\n}");
            }else{
                writer.append(" }");
            }
        }else if(blockMeta.isOptional()){
            if(blockMeta.isHeadFootTakeLine()){
                writer.append("\n]");
            }else{
                writer.append(" ]");
            }
        }else if(blockMeta.isHeadFootTakeLine()){
            writer.append("\n");
        }

        //list repeat
        if(blockMeta.isList() &&
                blockMeta.isRepeat()){
            writer.append(" [ [, ]...n ]");
        }else if(blockMeta.isList()){
            writer.append(" [,...n]");
        }else if(blockMeta.isRepeat()){
            writer.append(" [...n]");
        }

        //end
        if(blockMeta.isEndNewLine()){
            writer.append("\n");
        }

        return writer;
    }

    /**
     * print meta list
     * @param blockMetaList meta
     * @param printOverall printOverall
     * @param delimiter delimiter
     * @param writer writer
     */
    public void printMeta(List<BlockMeta> blockMetaList, boolean printOverall, String delimiter, StringWriter writer) {
        writer.append(
                blockMetaList
                .stream()
                .map(sub -> {
                    StringWriter stringWriter = new StringWriter();
                    printMeta(sub,printOverall,stringWriter);
                    return stringWriter.toString();
                })
                .collect(Collectors.joining(delimiter))
        );
    }


    /**
     * print context
     * @param context context
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public StringWriter printContext(Object context){
        if(!BlockManager.INSTANCE.checkTypeBlockConverter(context.getClass())){
            throw new RuntimeException(new BlockStructureCorrectException(null,
                    BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
        }

        BlockMeta hiddenMeta = BlockManager
                .INSTANCE
                .getTypeBlockConverter(context.getClass())
                .meta();

        return printContext(hiddenMeta,context);
    }

    /**
     * print context
     * @param meta meta
     * @param context context
     * @return writer
     */
    public StringWriter printContext(BlockMeta meta, Object context){
        return printContext(meta, context, writer);
    }

    /**
     * print context
     * @param meta meta
     * @param context context
     * @param writer writer
     * @return writer
     */
    @SuppressWarnings({"Duplicates", "unchecked"})
    public StringWriter printContext(BlockMeta meta, Object context, StringWriter writer){

        //Optional just return
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalFilter();
            if(optionalPredicate == null){
                throw new RuntimeException(new BlockStructureCorrectException(meta,
                        BlockStructureCorrectException.StructureCorrect.OPTION_FILTER_MISS));
            }
            if(optionalPredicate.test(context)){
                return writer;
            }
        }else if(context == null){
            throw new RuntimeException(new BlockStructureCorrectException(meta,
                    BlockStructureCorrectException.StructureCorrect.CONTEXT_MISS));
        }

        //start
        if(meta.isStartNewLine()){
            writer.append("\n");
        }

        //style
//        if(block.isHeadFootTakeLine()){
//            writer.append("\n");
//        }else{
//            writer.append(" ");
//        }


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
                printContext(refMeta, (List) referenceContext,"\n, ",writer);
            }else if(meta.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                printContext(refMeta, (List) referenceContext," ",writer);
            }else{
                printContext(refMeta, referenceContext, writer);
            }
        }else if(meta.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : meta.getCasePredicate()){
                if(p.test(context)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    printContext(exclusiveMeta, context, writer);
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

                printContext(hiddenMeta,context);
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
                printContext(itemMeta, listContext, delimiter, writer);
            }else{
                for (BlockMeta subMeta : meta.getSub()) {
                    printContext(subMeta, context, writer);
                }
            }
        }else{
            //Data
            String blockString;
            if(meta.isKeyword()){
                //Keyword
                blockString = meta.getData().toString();
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

                    blockString = new MetaContextBlockPrinter()
                            .printContext(hiddenMeta,data)
                            .toString();
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

                        StringWriter writer1 = new StringWriter();
                        printContext(hiddenMeta, listData, delimiter, writer1);
                        blockString = writer1.toString();
                    }else{
                        blockString = listData
                                .stream()
                                .map(Objects::toString)
                                .collect(Collectors.joining(delimiter));
                    }
                }else{
                    blockString = data.toString();
                }
            }

            if(meta.isHeadFootTakeLine()){
                writer.append("\n");
            }else{
                writer.append(" ");
            }

            writer.append(blockString);

            if(meta.isHeadFootTakeLine()){
                writer.append("\n");
            }else{
                writer.append(" ");
            }
        }


        //style
//        if(block.isHeadFootTakeLine()){
//            writer.append("\n");
//        }else{
//            writer.append(" ");
//        }

        //end
        if(meta.isEndNewLine()){
            writer.append("\n");
        }

        return writer;
    }

    /**
     * print context list
     * @param itemMeta meta
     * @param listContext context list
     * @param delimiter delimiter
     * @param writer writer
     */
    public void printContext(BlockMeta itemMeta, List<Object> listContext, String delimiter, StringWriter writer) {
        writer.append(
                listContext
                        .stream()
                        .map(context -> {
                            StringWriter stringWriter = new StringWriter();
                            printContext(itemMeta, context, stringWriter);
                            return stringWriter.toString();
                        })
                        .collect(Collectors.joining(delimiter))
        );
    }



    /**
     * print meta of context
     * @param context context
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter printMeta(Object context){
        MetaContextBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(context.getClass());

        BlockMeta b = converter.meta();

        return new MetaContextBlockPrinter().printContext(b,context);
    }

    /**
     * print meta of context
     * @param clazz context class
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter printMeta(Class clazz){
        MetaContextBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(clazz);

        BlockMeta b = converter.meta();

        return new MetaContextBlockPrinter().printMeta(b);
    }
}
