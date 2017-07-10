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
public class MetaContextBlockPrinter {

    private StringWriter writer;

    public MetaContextBlockPrinter(){
        writer = new StringWriter();
    }

    public StringWriter print(BlockMeta blockMeta) {
        return print(blockMeta,true,writer);
    }

    public StringWriter print(BlockMeta blockMeta, boolean printOverall, StringWriter writer) {
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
            print(blockMeta.getRefMeta(),false,writer);
        }else{
            //Sub
            String line = blockMeta.isEachSubTakeLine() ? "\n" : " ";

            if(blockMeta.isExclusive()){
                //Exclusive
                print(blockMeta.getSub(),false,line + "| ",writer);
            }else if(blockMeta.isList()){
                //List
                if(blockMeta.isReference()){
                    writer.append('<');
                    print(blockMeta.getSub(),false,line + ", ",writer);
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
                print(blockMeta.getSub(),false,line,writer);
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

    public void print(List<BlockMeta> blockMetaList, boolean printOverall, String delimiter, StringWriter writer) {
        writer.append(
                blockMetaList
                .stream()
                .map(sub -> {
                    StringWriter stringWriter = new StringWriter();
                    print(sub,printOverall,stringWriter);
                    return stringWriter.toString();
                })
                .collect(Collectors.joining(delimiter))
        );
    }


    @SuppressWarnings("unchecked")
    public StringWriter printBlock(Object data){
        if(BlockManager.INSTANCE.checkTypeBlockConverter(data.getClass())){
            BlockMeta hiddenBlock = BlockManager
                    .INSTANCE
                    .getTypeBlockConverter(data.getClass())
                    .convert(data)
                    .getMeta();

            return printBlock(hiddenBlock,data);
        }else{
            throw new RuntimeException(new BlockStructureCorrectException(null,
                    BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
        }
    }

    public StringWriter printBlock(BlockMeta blockMeta, Object context){
        return printBlock(blockMeta, context, writer);
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    public StringWriter printBlock(BlockMeta block, Object context, StringWriter writer){

        //Optional just return
        if(block.isOptional()){
            Predicate optionalPredicate = block.getOptionalFilter();
            if(optionalPredicate == null){
                throw new RuntimeException(new BlockStructureCorrectException(block,
                        BlockStructureCorrectException.StructureCorrect.OPTION_FILTER_MISS));
            }
            if(optionalPredicate.test(context)){
                return writer;
            }
        }else if(context == null){
            throw new RuntimeException(new BlockStructureCorrectException(block,
                    BlockStructureCorrectException.StructureCorrect.CONTEXT_MISS));
        }

        //start
        if(block.isStartNewLine()){
            writer.append("\n");
        }

        //style
//        if(block.isHeadFootTakeLine()){
//            writer.append("\n");
//        }else{
//            writer.append(" ");
//        }


        //data
        if(block.isReference()){
            //Reference
            BlockMeta blockMeta;
            if(block.isReferenceClass()){
                blockMeta = BlockManager
                        .INSTANCE
                        .getTypeBlockConverterByConverterType(block.getRefClass())
                        .convert(null)
                        .getMeta();
            }else{
                blockMeta = block.getRefMeta();
            }
            Object referenceContext = block.getContext(context);

            if(block.isList() &&
                    referenceContext instanceof List){
                //List
                printBlock(blockMeta, (List) referenceContext,"\n, ",writer);
            }else if(block.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                printBlock(blockMeta, (List) referenceContext," ",writer);
            }else{
                printBlock(blockMeta, referenceContext, writer);
            }
        }else if(block.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : block.getCasePredicate()){
                if(p.test(context)){
                    BlockMeta subBlock = block.getSub().get(index);
                    printBlock(subBlock, context, writer);
                    index = -1;
                    break;
                }
                index++;
            }
            if(index != -1){
                if(BlockManager.INSTANCE.checkTypeBlockConverter(context.getClass())){
                    BlockMeta hiddenBlock = BlockManager
                            .INSTANCE
                            .getTypeBlockConverter(context.getClass())
                            .convert(context)
                            .getMeta();

                    printBlock(hiddenBlock,context);
                }else{
                    throw new RuntimeException(new BlockStructureCorrectException(block,
                            BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
                }
            }
        }else if(block.getSub() != null){
            //Virtual
            if(block.isList() ||
                    block.isRepeat()){
                if(block.getSub().size() != 1){
                    throw new RuntimeException(new BlockStructureCorrectException(block,
                            BlockStructureCorrectException.StructureCorrect.COLLECTION_META_AMOUNT_ERROR));
                }
                Object data = block.getContext(context);
                if(!(data instanceof List)){
                    throw new RuntimeException(new BlockStructureCorrectException(block,
                            BlockStructureCorrectException.StructureCorrect.COLLECTION_CONTEXT_MUST_LIST));
                }
                List<Object> listData = (List)data;
                String delimiter = null;
                if(block.isList()){
                    delimiter = "\n, ";
                }else if(block.isRepeat()) {
                    delimiter = "\n ";
                }
                printBlock(block.getSub().get(0), listData, delimiter, writer);
            }else{
                for (BlockMeta subBlock : block.getSub()) {
                    printBlock(subBlock, context, writer);
                }
            }
        }else{
            //Data
            String blockString;
            if(block.isKeyword()){
                //Keyword
                blockString = block.getData().toString();
            }else{
                Object data = block.getDataOrGetterData(context);
                if(data == null){
                    throw new RuntimeException(new BlockStructureCorrectException(block,
                            BlockStructureCorrectException.StructureCorrect.NO_DATA));
                }
                if(BlockManager
                        .INSTANCE
                        .checkTypeBlockConverter(data.getClass())){
                    BlockMeta hiddenBlock = BlockManager
                            .INSTANCE
                            .getTypeBlockConverter(data.getClass())
                            .convert(data)
                            .getMeta();

                    blockString = new MetaContextBlockPrinter()
                            .printBlock(hiddenBlock,data)
                            .toString();
                }else if(data instanceof List){
                    List<Object> listData = (List)data;
                    String delimiter = null;
                    if(block.isList()){
                        delimiter = "\n, ";
                    }else if(block.isRepeat()) {
                        delimiter = "\n ";
                    }else{
                        throw new RuntimeException(new BlockStructureCorrectException(block,
                                BlockStructureCorrectException.StructureCorrect.COLLECTION_DATA_CANT_FIND_BLOCK_META));
                    }
                    if(listData.size() <= 0){
                        throw new RuntimeException(new BlockStructureCorrectException(block,
                                BlockStructureCorrectException.StructureCorrect.COLLECTION_CONTEXT_MISS));
                    }
                    Object itemData = listData.get(0);
                    if(BlockManager
                            .INSTANCE
                            .checkTypeBlockConverter(itemData.getClass())){
                        BlockMeta hiddenBlock = BlockManager
                                .INSTANCE
                                .getTypeBlockConverter(itemData.getClass())
                                .convert(itemData)
                                .getMeta();

                        StringWriter writer1 = new StringWriter();
                        printBlock(hiddenBlock, listData, delimiter, writer1);
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

            if(block.isHeadFootTakeLine()){
                writer.append("\n");
            }else{
                writer.append(" ");
            }

            writer.append(blockString);

            if(block.isHeadFootTakeLine()){
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
        if(block.isEndNewLine()){
            writer.append("\n");
        }

        return writer;
    }

    public void printBlock(BlockMeta itemBlock, List<Object> listContext, String delimiter, StringWriter writer) {
        writer.append(
                listContext
                        .stream()
                        .map(context -> {
                            StringWriter stringWriter = new StringWriter();
                            printBlock(itemBlock, context, stringWriter);
                            return stringWriter.toString();
                        })
                        .collect(Collectors.joining(delimiter))
        );
    }


    /**
     *
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static StringWriter print(Object object){
        MetaContextBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(object.getClass());

        BlockMeta b = converter.convert(object).getMeta();

        return new MetaContextBlockPrinter().printBlock(b,object);
    }

    @SuppressWarnings("unchecked")
    public static StringWriter print(Class clazz){
        MetaContextBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(clazz);

        BlockMeta b = converter.convert(null).getMeta();

        return new MetaContextBlockPrinter().print(b);
    }

}
