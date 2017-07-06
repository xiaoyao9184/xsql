package com.xy.xsql.block.core;

import com.xy.xsql.block.exception.BlockStructureCorrectException;
import com.xy.xsql.block.model.ReferenceBlock;

import java.io.StringWriter;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/6/9.
 */
public class ReferenceBlockPrinter {

    private StringWriter writer;

    public ReferenceBlockPrinter(){
        writer = new StringWriter();
    }

    public StringWriter print(ReferenceBlock referenceBlock) {
        return print(referenceBlock,true,writer);
    }

    public StringWriter print(ReferenceBlock referenceBlock, boolean printOverall, StringWriter writer) {
        if(referenceBlock.isOverall() &&
                printOverall) {
            //Syntax
            writer.append('<');
            writer.append(referenceBlock.getName());
            writer.append("> ::=\n");
        }

        //start
        if(referenceBlock.isStartNewLine()){
            writer.append("\n");
        }

        //style
        if(referenceBlock.isOptional()){
            if(referenceBlock.isHeadFootTakeLine()){
                writer.append("[\n");
            }else{
                writer.append("[ ");
            }
        }else if(referenceBlock.isRequired()){
            if(referenceBlock.isHeadFootTakeLine()){
                writer.append("{\n");
            }else{
                writer.append("{ ");
            }
        }else if(referenceBlock.isHeadFootTakeLine()){
            writer.append("\n");
        }

        //data
        if(referenceBlock.isKeyword()) {
            //Keyword
            writer.append(referenceBlock.getData().toString());
        }else if(referenceBlock.getName() != null &&
                referenceBlock.isReferenceClass()){
            //Reference Name
            writer.append('<');
            writer.append(referenceBlock.getName());
            writer.append('>');
        }else if(referenceBlock.getName() != null &&
                !referenceBlock.isOverall()){
            //Name
            writer.append(referenceBlock.getName());
        }else if(referenceBlock.isReferenceMeta()){
            //Reference Meta
            print(referenceBlock.getRefMeta(),false,writer);
        }else{
            //Sub
            String line = referenceBlock.isEachSubTakeLine() ? "\n" : " ";

            if(referenceBlock.isExclusive()){
                //Exclusive
                print(referenceBlock.getSub(),false,line + "| ",writer);
            }else if(referenceBlock.isList()){
                //List
                if(referenceBlock.isReference()){
                    writer.append('<');
                    print(referenceBlock.getSub(),false,line + ", ",writer);
                    if(referenceBlock.isReference()){
                        writer.append('>');
                    }
                }else{

                }

            }else if(referenceBlock.getSub() != null){
                if(referenceBlock.isReference()){
                    writer.append('<');
                }
                //Repeat
                print(referenceBlock.getSub(),false,line,writer);
                if(referenceBlock.isReference()){
                    writer.append('>');
                }
            }else{
//                throw new Exception("error block");
            }
        }


        //style
        if(referenceBlock.isRequired()){
            if(referenceBlock.isHeadFootTakeLine()){
                writer.append("\n}");
            }else{
                writer.append(" }");
            }
        }else if(referenceBlock.isOptional()){
            if(referenceBlock.isHeadFootTakeLine()){
                writer.append("\n]");
            }else{
                writer.append(" ]");
            }
        }else if(referenceBlock.isHeadFootTakeLine()){
            writer.append("\n");
        }

        //list repeat
        if(referenceBlock.isList() &&
                referenceBlock.isRepeat()){
            writer.append(" [ [, ]...n ]");
        }else if(referenceBlock.isList()){
            writer.append(" [,...n]");
        }else if(referenceBlock.isRepeat()){
            writer.append(" [...n]");
        }

        //end
        if(referenceBlock.isEndNewLine()){
            writer.append("\n");
        }

        return writer;
    }

    public void print(List<ReferenceBlock> referenceBlockList, boolean printOverall, String delimiter, StringWriter writer) {
        writer.append(
                referenceBlockList
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
            ReferenceBlock hiddenBlock = BlockManager
                    .INSTANCE
                    .getTypeBlockConverter(data.getClass())
                    .convert(data);

            return printBlock(hiddenBlock,data);
        }else{
            throw new RuntimeException(new BlockStructureCorrectException(null,
                    BlockStructureCorrectException.StructureCorrect.NOTHING_PASS_EXCLUSIVE));
        }
    }

    public StringWriter printBlock(ReferenceBlock referenceBlock, Object context){
        return printBlock(referenceBlock, context, writer);
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    public StringWriter printBlock(ReferenceBlock block, Object context, StringWriter writer){

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
            ReferenceBlock referenceBlock;
            if(block.isReferenceClass()){
                ReferenceBlockConverter converter = BlockManager
                        .INSTANCE
                        .getTypeBlockConverterByConverterType(block.getRefClass());
                referenceBlock = converter.build(null);
            }else{
                referenceBlock = block.getRefMeta();
            }
            Object referenceContext = block.getContext(context);

            if(block.isList() &&
                    referenceContext instanceof List){
                //List
                printBlock(referenceBlock, (List) referenceContext,"\n, ",writer);
            }else if(block.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                printBlock(referenceBlock, (List) referenceContext," ",writer);
            }else{
                printBlock(referenceBlock, referenceContext, writer);
            }
        }else if(block.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : block.getCasePredicate()){
                if(p.test(context)){
                    ReferenceBlock subBlock = block.getSub().get(index);
                    printBlock(subBlock, context, writer);
                    index = -1;
                    break;
                }
                index++;
            }
            if(index != -1){
                if(BlockManager.INSTANCE.checkTypeBlockConverter(context.getClass())){
                    ReferenceBlock hiddenBlock = BlockManager
                            .INSTANCE
                            .getTypeBlockConverter(context.getClass())
                            .convert(context);

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
                for (ReferenceBlock subBlock : block.getSub()) {
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
                    ReferenceBlock hiddenBlock = BlockManager
                            .INSTANCE
                            .getTypeBlockConverter(data.getClass())
                            .convert(data);

                    blockString = new ReferenceBlockPrinter()
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
                        ReferenceBlock hiddenBlock = BlockManager
                                .INSTANCE
                                .getTypeBlockConverter(itemData.getClass())
                                .convert(itemData);

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

    public void printBlock(ReferenceBlock itemBlock, List<Object> listContext, String delimiter, StringWriter writer) {
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

    @Deprecated
    public void printBlock(ReferenceBlockConverter converter, List<Object> dataList, String delimiter, StringWriter writer) {
        writer.append(
                dataList
                        .stream()
                        .map(data -> {
                            StringWriter stringWriter = new StringWriter();
                            ReferenceBlock itemBlock = converter.build(data);
                            printBlock(itemBlock, data, stringWriter);
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
        ReferenceBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(object.getClass());

        ReferenceBlock b = converter.convert(object);

        return new ReferenceBlockPrinter().printBlock(b,object);
    }

    @SuppressWarnings("unchecked")
    public static StringWriter print(Class clazz){
        ReferenceBlockConverter converter = BlockManager
                .INSTANCE
                .getTypeBlockConverter(clazz);

        ReferenceBlock b = converter.convert(null);

        return new ReferenceBlockPrinter().print(b);
    }

}
