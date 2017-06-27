package com.xy.xsql.block.core;

import com.xy.xsql.block.exception.BlockStructureCorrectException;
import com.xy.xsql.block.model.ReferenceBlock;

import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;
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
        return print(referenceBlock,writer,true);
    }

    public StringWriter print(ReferenceBlock referenceBlock, StringWriter writer, boolean printOverall) {
        if(referenceBlock.isOverall() && printOverall) {
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
        if(referenceBlock.isKeyword()){
            //Keyword
            writer.append(referenceBlock.getData().toString());
        }else if(referenceBlock.isReference() &&
                referenceBlock.getName() != null){
            //Reference
            writer.append('<');
            writer.append(referenceBlock.getName());
            writer.append('>');
        }else if(!referenceBlock.isOverall() &&
                referenceBlock.getName() != null){
            writer.append(referenceBlock.getName());
        }else{
            //Sub
            String line = referenceBlock.isEachSubTakeLine() ? "\n" : " ";

            if(referenceBlock.isExclusive()){
                //Exclusive
                print(referenceBlock.getSub(),line + "| ",writer);
            }else if(referenceBlock.isList()){
                if(referenceBlock.isReference()){
                    writer.append('<');
                }
                //List
                print(referenceBlock.getSub(),line + ", ",writer);
                if(referenceBlock.isReference()){
                    writer.append('>');
                }
            }else if(referenceBlock.getSub() != null){
                if(referenceBlock.isReference()){
                    writer.append('<');
                }
                //Repeat
                print(referenceBlock.getSub(),line,writer);
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

    public void print(List<ReferenceBlock> referenceBlockList, String delimiter, StringWriter writer) {
        writer.append(
                referenceBlockList
                .stream()
                .map(sub -> {
                    StringWriter stringWriter = new StringWriter();
                    print(sub,stringWriter,false);
                    return stringWriter.toString();
                })
                .collect(Collectors.joining(delimiter))
        );
    }


    @Deprecated
    public StringWriter printBlock(ReferenceBlock referenceBlock){
        return printBlock(referenceBlock, referenceBlock.getData(), writer);
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
            ReferenceBlockConverter converter = BlockManager
                    .INSTANCE
                    .getTypeBlockConverterByConverterType(block.getRefClass());

            Object referenceContext = block.getDataOrGetterData(context);

            if(block.isList() &&
                    referenceContext instanceof List){
                //List
                printBlock(converter, (List) referenceContext,"\n, ",writer);
            }else if(block.isRepeat() &&
                    referenceContext instanceof List){
                //Repeat
                printBlock(converter, (List) referenceContext," ",writer);
            }else{
                ReferenceBlock referenceBlock = converter.convert(referenceContext);
                printBlock(referenceBlock, referenceContext, writer);
            }
        }else if(block.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : block.getCasePredicate()){
                if(p.test(context)){
                    ReferenceBlock subBlock = block.getSub().get(index);
                    printBlock(subBlock, context, writer);
                    break;
                }
                index++;
            }
        }else if(block.isList()){
            //List
            ReferenceBlock itemBlock = block.getSub().get(0);
            Object dataList = block.getDataOrGetterData(context);
            if(dataList instanceof List){
                List listData = (List) dataList;
                for (Object itemData : listData) {
                    printBlock(itemBlock, itemData, writer);
                }
            }
        }else if(block.isRepeat()){
            //Repeat
            for (ReferenceBlock itemBlock : block.getSub()) {
                printBlock(itemBlock, context, writer);
            }
        }else if(block.getSub() != null){
            //
            for (ReferenceBlock subBlock : block.getSub()) {
                printBlock(subBlock, context, writer);
            }
        }else{
            String blockString;
            if(block.isKeyword()){
                //Keyword
                blockString = block.getData().toString();
            }else{
                Object data = block.getDataOrGetterData(context);
                blockString = data.toString();
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


    public void printBlock(ReferenceBlockConverter converter, List<Object> dataList, String delimiter, StringWriter writer) {
        writer.append(
                dataList
                        .stream()
                        .map(data -> {
                            StringWriter stringWriter = new StringWriter();
                            ReferenceBlock itemBlock = converter.build(data);
                            printBlock(itemBlock, data, writer);
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
