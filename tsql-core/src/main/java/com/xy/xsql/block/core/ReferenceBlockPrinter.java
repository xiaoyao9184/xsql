package com.xy.xsql.block.core;

import com.xy.xsql.block.model.ReferenceBlock;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaoyao9184 on 2017/6/9.
 */
public class ReferenceBlockPrinter {

    private StringWriter writer;

    public ReferenceBlockPrinter(){
        writer = new StringWriter();
    }

    public StringWriter print(ReferenceBlock referenceBlock) throws Exception {
        return print(referenceBlock,writer,true);
    }


    public StringWriter print(ReferenceBlock referenceBlock,StringWriter writer, boolean printOverall) throws Exception {
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
                referenceBlock.isRepeat() &&
                referenceBlock.isMore()){
            writer.append(" [ [, ]...n ]");
        }else if(referenceBlock.isList() &&
                referenceBlock.isMore()){
            writer.append(" [,...n]");
        }else if(referenceBlock.isRepeat() &&
                referenceBlock.isMore()){
            writer.append(" [...n]");
        }

        //end
        if(referenceBlock.isEndNewLine()){
            writer.append("\n");
        }

        return writer;
    }


    public void print(List<ReferenceBlock> referenceBlockList,String delimiter,StringWriter writer) throws Exception {
        writer.append(
                referenceBlockList
                .stream()
                .map(sub -> {
                    try {
                        StringWriter stringWriter = new StringWriter();
                        print(sub,stringWriter,false);
                        return stringWriter.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.joining(delimiter))
        );
    }

}
