package com.xy.xsql.block.core;

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


    public StringWriter printBlock(ReferenceBlock referenceBlock){
        return printBlock(referenceBlock, referenceBlock.getData(), writer);
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    public StringWriter printBlock(ReferenceBlock block, Object data, StringWriter writer){

        //Optional just return
        if(block.isOptional() &&
            block.getOptionalFilter().test(data)){
            return writer;
        }

        //start
        if(block.isStartNewLine()){
            writer.append("\n");
        }

        //style
        if(block.isHeadFootTakeLine()){
            writer.append("\n");
        }else{
            writer.append(" ");
        }


        //data
        if(block.isKeyword()){
            //Keyword
            writer.append(block.getData().toString());
        }else if(block.isReference()){
            //Reference
            ReferenceBlockConverter converter = BlockManager
                    .INSTANCE
                    .getTypeBlockConverterByConverterType(block.getRefClass());

            if(block.isList() &&
                    data instanceof List){
                //List
                printBlock(converter, (List) data,"\n, ",writer);
            }else if(block.isRepeat() &&
                    data instanceof List){
                //Repeat
                printBlock(converter, (List) data," ",writer);
            }else{
                ReferenceBlock referenceBlock = converter.convert(data);
                printBlock(referenceBlock, data, writer);
            }
        }else if(block.isExclusive()){
            //Exclusive
            int index = 0;
            for(Predicate p : block.getCasePredicate()){
                if(p.test(data)){
                    ReferenceBlock subBlock = block.getSub().get(index);
                    printBlock(subBlock, data, writer);
                    break;
                }
                index++;
            }
        }else if(block.isList()){
            //List
            ReferenceBlock itemBlock = block.getSub().get(0);
            Object dataList = block.getDataOrGetterData(data);
            if(dataList instanceof List){
                List listData = (List) dataList;
                for (Object itemData : listData) {
                    printBlock(itemBlock, itemData, writer);
                }
            }
        }else if(block.isRepeat()){
            //Repeat
            for (ReferenceBlock itemBlock : block.getSub()) {
                printBlock(itemBlock, data, writer);
            }
        }else if(block.getSub() != null){
            //
            for (ReferenceBlock subBlock : block.getSub()) {
                Object subData = subBlock.getDataOrGetterData(data);
                printBlock(subBlock, subData, writer);
            }
        }else{
            writer.append(data.toString());
        }


        //style
        if(block.isHeadFootTakeLine()){
            writer.append("\n");
        }else{
            writer.append(" ");
        }

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

        return new ReferenceBlockPrinter().printBlock(b);
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
