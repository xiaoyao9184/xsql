package com.xy.xsql.block.core.printer;

import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.exception.BlockStructureCorrectException;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.ModelMetaBlock;

import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/6/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ModelMetaBlockPrinter
        implements BlockPrinter<ModelMetaBlock,StringWriter> {

    private StringWriter writer;

    public ModelMetaBlockPrinter(){
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
            writer.append(blockMeta.getName());
        }else if(blockMeta.isNamedReference() &&
                blockMeta.getName() != null){
            //Reference Name
            writer.append('<');
            writer.append(blockMeta.getName());
            writer.append('>');
        }else if(blockMeta.isAnonymousReference()){
            //Reference Meta
            printMeta(blockMeta.getReferenceMeta(),false,writer);
        }else if(blockMeta.isVirtual()) {
            //Sub
            String line = blockMeta.isEachSubTakeLine() ? "\n" : " ";

            if(blockMeta.isExclusive()){
                line = line + "| ";
            }else if(blockMeta.isList()){
                line = line + ", ";
            }

            if(blockMeta.isNamedReference()){
                writer.append('<');
            }
            printMeta(blockMeta.getSub(),false,line,writer);
            if(blockMeta.isNamedReference()){
                writer.append('>');
            }

//            if(blockMeta.isExclusive()){
//                //Exclusive
//                printMeta(blockMeta.getSub(),false,line + "| ",writer);
//            }else if(blockMeta.isList()){
//                //List
//                if(blockMeta.isReference()){
//                    writer.append('<');
//                    printMeta(blockMeta.getSub(),false,line + ", ",writer);
//                    if(blockMeta.isReference()){
//                        writer.append('>');
//                    }
//                }else{
//
//                }
//
//            }else if(blockMeta.getSub() != null){
//                if(blockMeta.isReference()){
//                    writer.append('<');
//                }
//                //Repeat
//                printMeta(blockMeta.getSub(),false,line,writer);
//                if(blockMeta.isReference()){
//                    writer.append('>');
//                }
//            }else{
////                throw new Exception("error block");
//            }
        }else if(blockMeta.getName() != null &&
            !blockMeta.isOverall()){
            //Data
            writer.append(blockMeta.getName());
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
     * @param model model
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public StringWriter printModel(Object model){
        if(!BlockManager.INSTANCE.checkTypeBlockConverter(model.getClass())){
            throw new RuntimeException(new BlockStructureCorrectException(null,
                    BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
        }

        BlockMeta hiddenMeta = BlockManager
                .INSTANCE
                .getTypeBlockConverter(model.getClass())
                .meta();

        return printModel(hiddenMeta,model);
    }

    /**
     * print context
     * @param meta meta
     * @param model model
     * @return writer
     */
    public StringWriter printModel(BlockMeta meta, Object model){
        printModel(meta, model, writer);
        return writer;
    }

    /**
     * print context
     * @param meta meta
     * @param model model
     * @param writer writer
     * @return print any thing
     */
    @SuppressWarnings({"Duplicates", "unchecked"})
    public boolean printModel(BlockMeta meta, Object model, StringWriter writer){

        //Optional just return
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalPredicate();
            if(optionalPredicate == null){
                throw new RuntimeException(new BlockStructureCorrectException(meta,
                        BlockStructureCorrectException.StructureCorrect.OPTION_FILTER_MISS));
            }
            if(optionalPredicate.test(model)){
                return false;
            }
        }else if(model == null){
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
            if(meta.isNamedReference()){
                refMeta = BlockManager
                        .INSTANCE
                        .getTypeBlockConverterByConverterType(meta.getReferenceConverter())
                        .meta();
            }else{
                refMeta = meta.getReferenceMeta();
            }
            Object referenceContext = meta.getScope(model);

            if(meta.isList() &&
                    referenceContext instanceof List){
                //List
                printModel(refMeta, (List) referenceContext,"\n, ",writer);
            }else if(meta.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                printModel(refMeta, (List) referenceContext,"\n ",writer);
            }else{
                printModel(refMeta, referenceContext, writer);
            }
        }else if(meta.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : meta.getExclusivePredicate()){
                if(p.test(model)){
                    BlockMeta exclusiveMeta = meta.getSub().get(index);
                    printModel(exclusiveMeta, model, writer);
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

                printModel(hiddenMeta,model,writer);
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
                Object data = meta.getScope(model);
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
                printModel(itemMeta, listContext, delimiter, writer);
            }else{
                printModel(meta.getSub(),model," ",writer);
            }
        }else{
            //Data
            String blockString;
            if(meta.isKeyword()){
                //Keyword
                blockString = meta.getName();
            }else{
                Object data = meta.getScope(model);
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

                    blockString = new ModelMetaBlockPrinter()
                            .printModel(hiddenMeta,data)
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
                        printModel(hiddenMeta, listData, delimiter, writer1);
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
//                writer.append(" ");
            }

            writer.append(blockString);

            if(meta.isHeadFootTakeLine()){
                writer.append("\n");
            }else{
//                writer.append(" ");
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

        return true;
    }

    /**
     * print context list
     * @param itemMeta meta
     * @param listModel context model
     * @param delimiter delimiter
     * @param writer writer
     */
    public void printModel(BlockMeta itemMeta, List<Object> listModel, String delimiter, StringWriter writer) {
        writer.append(
                listModel
                        .stream()
                        .map(context -> {
                            StringWriter stringWriter = new StringWriter();
                            printModel(itemMeta, context, stringWriter);
                            return stringWriter.toString();
                        })
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining(delimiter))
        );
    }

    /**
     * print meta list
     * @param listMeta meta list
     * @param itemModel model
     * @param delimiter delimiter
     * @param writer writer
     */
    public void printModel(List<BlockMeta> listMeta, Object itemModel, String delimiter, StringWriter writer) {
        writer.append(
                listMeta
                        .stream()
                        .map(meta -> {
                            StringWriter stringWriter = new StringWriter();
                            printModel(meta, itemModel, stringWriter);
                            return stringWriter.toString();
                        })
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.joining(delimiter))
        );
    }


    /**
     * print block
     * @param block block
     * @return writer
     */
    @Override
    public StringWriter print(ModelMetaBlock block) {
        if(block.getModel() == null){
            printMeta(block.getMeta());
        }else if(block.getMeta() == null){
            printModel(block.getModel());
        }else{
            printModel(block.getMeta(),block.getModel());
        }
        return this.writer;
    }


    /**
     * print model
     * @param model model
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter print(Object model){
        ModelMetaBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(model.getClass());

        BlockMeta b = converter.meta();

        return new ModelMetaBlockPrinter().printModel(b,model);
    }

    /**
     * print meta of model class
     * @param clazz model class
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter print(Class clazz){
        ModelMetaBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(clazz);

        BlockMeta b = converter.meta();

        return new ModelMetaBlockPrinter().printMeta(b);
    }

    /**
     * print meta
     * @param meta meta
     * @return writer
     */
    public static StringWriter print(BlockMeta meta) {
        return new ModelMetaBlockPrinter().printMeta(meta);
    }

}
