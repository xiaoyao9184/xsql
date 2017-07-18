package com.xy.xsql.block.core.printer;

import com.google.common.base.Strings;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.meta.MetaManager;
import com.xy.xsql.block.exception.MetaException;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.ModelMetaBlock;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.block.model.BlockMeta.BlockConvention.*;
import static com.xy.xsql.block.exception.MetaException.*;
import static com.xy.xsql.block.model.BlockMeta.BlockDelimiterConvention.*;

/**
 *
 * Print Meta
 * Only need to go deep into 'Data meta'
 *
 *
 * Print Model
 * Must go deep into the bottom,
 * Even if the 'Reference meta' is used,
 * Or a 'Data meta' that contains a subset
 *
 * Created by xiaoyao9184 on 2017/6/9.
 */
@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class ModelMetaBlockPrinter
        implements BlockPrinter<ModelMetaBlock,StringWriter> {

//    private StringWriter writer;

    public ModelMetaBlockPrinter(){
//        writer = new StringWriter();
    }


    /**
     * print meta
     * @param blockMeta meta
     * @return writer
     */
    public StringWriter printMeta(BlockMeta blockMeta) {
        Context context = new Context();
        context.setPrintOverall(true);
        printMeta(blockMeta,context);
        return context.getWriter();
    }

    /**
     * print meta
     * @param blockMeta meta list
     * @return writer
     */
    public void printMeta(BlockMeta blockMeta, Context context) {

        //format style: line indentation
        context.setMeta(blockMeta);
        blockMeta.syntax()
                .ifPresent(syntax -> {
                    context.setSyntax(syntax);
                    context.addLevel(syntax.getIndentation());
                });
        blockMeta.sub_syntax()
                .ifPresent(syntax -> {
                    context.setSubSyntax(syntax);
                });

        //Syntax
        if(blockMeta.isOverall() &&
                context.isPrintOverall()) {
            context.getWriter()
                    .append(REFERENCE_START.toString())
                    .append(blockMeta.getName())
                    .append(REFERENCE_END.toString())
                    .append(BLANKS.toString())
                    .append(REFERENCE_LABEL.toString())
                    .append(LINE.toString());
        }

        //start style
//        context.getWriter().append(blockMeta.style()
//                .filter(BlockMeta.Style::isStartNewLine)
//                .map(style -> LINE.toString())
//                .orElse(EMPTY.toString()));

        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isOptional)
                .map(style -> OPTIONAL_START.toString())
                .orElse(EMPTY.toString()));
        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isRequired)
                .map(style -> REQUIRED_START.toString())
                .orElse(EMPTY.toString()));

        context.getWriter().append(blockMeta.style()
                .filter(style -> style.isOptional() || style.isRequired())
                .filter(style -> !style.isConventionLineDelimiter())
                .map(style -> BLANKS.toString())
                .orElse(EMPTY.toString()));
        context.getWriter().append(blockMeta.style()
                .filter(style -> style.isOptional() || style.isRequired())
                .filter(style -> style.isConventionLineDelimiter())
                .map(style -> LINE.toString())
                .orElse(EMPTY.toString()));


        //TODO
        context.getWriter().append(blockMeta.syntax()
                .filter(BlockMeta.SyntaxFormat::isIndentationContent)
                .map(style ->{
                    context.addLevel(1);
                    return LINE.toString() +
                            Strings.repeat(style.getIndentationChar(),
                                    context.getAbsoluteLevel());
                })
                .orElse(EMPTY.toString()));


        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isReference)
                .map(style -> REFERENCE_START.toString())
                .orElse(EMPTY.toString()));
        //context
        if(blockMeta.isData()){
            //use Name
            context.getWriter().append(blockMeta.getName());
        }else if(blockMeta.isAnonymousReference()){
            //no use name
            if(blockMeta.isReferenceMeta()){
                //Reference Meta
                printMeta(blockMeta.getReferenceMeta(),context);
            }else{
                //Reference Converter
                BlockMeta hideMeta = MetaManager
                        .byConverter(blockMeta.getReferenceConverter())
                        .get()
                        .orElseThrow(MetaException::miss_meta);
                printMeta(hideMeta,context);
            }
        }else if(blockMeta.isOverall() ||
                blockMeta.isVirtual()) {
            //Sub
//            StringBuilder delimiterBuilder = new StringBuilder();
////            delimiterBuilder.append(blockMeta.style()
////                            .filter(BlockMeta.Style::isSubNewLine)
////                            .map(style -> LINE.toString())
////                            .orElse(BLANKS.toString()));
//
//            if(blockMeta.isExclusive()){
//                delimiterBuilder
//                        .append(BLANKS.toString())
//                        .append(ONE_OF.toString())
//                        .append(BLANKS.toString());
//            }else if(blockMeta.isList()){
//                delimiterBuilder
//                        .append(BLANKS.toString())
//                        .append(COMMA.toString())
//                        .append(BLANKS.toString());
//            }else{
//                delimiterBuilder
//                        .append(BLANKS.toString());
//            }
            String delimiter;
            if(blockMeta.isList()){
                delimiter = PREFIX_COMMA.toString();
            }else if(blockMeta.isExclusive()){
                delimiter = PREFIX_ONE_OF.toString();
            }else{
                delimiter = BLANKS.toString();
            }

            printMeta(blockMeta.getSub(),false,delimiter,context);
        }

        //end style
        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isReference)
                .map(style -> REFERENCE_END.toString())
                .orElse(EMPTY.toString()));

        context.getWriter().append(blockMeta.style()
                .filter(style -> style.isOptional() || style.isRequired())
                .filter(style -> !style.isConventionLineDelimiter())
                .map(style -> BLANKS.toString())
                .orElse(EMPTY.toString()));
        context.getWriter().append(blockMeta.style()
                .filter(style -> style.isOptional() || style.isRequired())
                .filter(style -> style.isConventionLineDelimiter())
                .map(style -> LINE.toString())
                .orElse(EMPTY.toString()));

        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isOptional)
                .map(style -> OPTIONAL_END.toString())
                .orElse(EMPTY.toString()));

        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isRequired)
                .map(style -> REQUIRED_END.toString())
                .orElse(EMPTY.toString()));

        //list repeat
        if(!blockMeta.isVirtual()){
            if(blockMeta.isList() &&
                    blockMeta.isRepeat()){
                context.getWriter().append(BLANKS.toString())
                        .append(REPEATED_COMMA_BLANKS.toString());
            }else if(blockMeta.isList()){
                context.getWriter().append(BLANKS.toString())
                        .append(REPEATED_COMMA.toString());
            }else if(blockMeta.isRepeat()){
                context.getWriter().append(BLANKS.toString())
                        .append(REPEATED_BLANKS.toString());
            }
        }

        //end style
        context.getWriter().append(blockMeta.style()
                .filter(BlockMeta.Style::isEndNewLine)
                .map(style -> LINE.toString())
                .orElse(EMPTY.toString()));
    }

    /**
     * print meta list
//     * @param blockMetaList meta
     * @param printOverall printOverall
     * @param delimiter delimiter
//     * @param writer writer
     */
    public void printMeta(List<BlockMeta> collectionMeta, boolean printOverall, String delimiter, Context context) {
        //The default format for the subset
        //line
        String defaultLine = context.sub_syntax()
                .filter(BlockMeta.Format::isNewLine)
                .map(f -> LINE.toString())
                .orElse(EMPTY.toString());
        //indentation
        String defaultIndentation = context.sub_syntax()
                .filter(BlockMeta.Format::isNewLine)
                .map(f -> Strings.repeat(f.getIndentationChar(),
                        context.getAbsoluteLevel() + f.getIndentation()))
                .orElse(EMPTY.toString());
        //delimiter
        String defaultDelimiter = context.sub_syntax()
                .filter(f -> !f.isUseDefaultDelimiter())
                .map(BlockMeta.Format::getDelimiterChar)
                .orElse(delimiter);

        String stringTemp = collectionMeta
                .stream()
                .map(sub -> {
                    Context itemContext = new Context();
                    printMeta(sub,itemContext);
                    return itemContext;
                })
                //joining
                .flatMap(itemContext -> {
                    //If the format is defined, the parent default format is ignored
                    String itemDefaultLine = itemContext.syntax()
                            .map(f -> EMPTY.toString())
                            .orElse(defaultLine);
                    String itemDefaultIndentation = itemContext.syntax()
                            .map(f -> EMPTY.toString())
                            .orElse(defaultIndentation);
                    String itemDefaultDelimiter = itemContext.syntax()
                            .map(f -> delimiter)
                            .orElse(defaultDelimiter);

                    //item format
                    //line
                    String itemCustomizeLine = itemContext.syntax()
                            .filter(BlockMeta.Format::isNewLine)
                            .map(f -> LINE.toString())
                            .orElse(itemDefaultLine);
                    //indentation
                    String itemCustomizeIndentation = itemContext.syntax()
                            .filter(BlockMeta.Format::isNewLine)
                            .map(f -> Strings.repeat(f.getIndentationChar(),
                                    context.getAbsoluteLevel() + f.getIndentation()))
                            .orElse(itemDefaultIndentation);
                    //delimiter
                    String itemCustomizeDelimiter = itemContext.syntax()
                            .filter(f -> !f.isUseDefaultDelimiter())
                            .map(BlockMeta.Format::getDelimiterChar)
                            .orElse(itemDefaultDelimiter);

                    String itemDelimiter = itemCustomizeLine +
                            itemCustomizeIndentation +
                            itemCustomizeDelimiter;

                    return Stream.concat(
                            Stream.of(itemDelimiter),
                            Stream.of(itemContext.getWriter().toString()));
                })
                .skip(1)
                .collect(Collectors.joining());

        context.getWriter().append(stringTemp);
    }


    /**
     * print context
     * @param model model
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public StringWriter printModel(Object model){
        BlockMeta hiddenMeta = MetaManager
                .byModel(model.getClass())
                .get()
                .orElseThrow(MetaException::miss_meta);
        return printModel(hiddenMeta,model);
    }

    /**
     * print model
     * @param meta meta
     * @param model model
     * @return writer
     */
    public StringWriter printModel(BlockMeta meta, Object model){
        StringWriter writer = new StringWriter();
        printModel(meta, model, writer);
        return writer;
    }

    /**
     * print context
     * @param meta meta
     * @param model model
     * @param writer writer
     */
    @SuppressWarnings({"Duplicates", "unchecked"})
    public void printModel(BlockMeta meta, Object model, StringWriter writer){

        //Optional
        if(meta.isOptional()){
            Predicate optionalPredicate = meta.getOptionalPredicate();
            if(optionalPredicate == null){
                throw miss_option_predicate(meta);
            }
            //noinspection unchecked
            if(optionalPredicate.test(model)){
                return;
            }
        }else if(model == null){
            throw miss_model(meta);
        }

        //format style
        writer.append(meta.format()
                .filter(BlockMeta.Format::isNewLine)
                .map(style -> LINE.toString())
                .orElse(EMPTY.toString()));

        //data
        if(meta.isExclusive()){
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
                //TODO
                //hide exclusive
                /**
                 * {@link com.xy.xsql.block.tsql.core.clause.select.GroupByConverter.ColumnNameItemConverter}
                 */
                Optional<BlockMeta> optional = MetaManager
                        .byModel(model.getClass())
                        .get();
                if(!optional.isPresent()){
                    throw MetaException.nothing_pass_exclusive(meta);
                }else{
                    printModel(optional.get(), model, writer);
                }
            }
        }else if(meta.isReference()){
            //Reference child
            BlockMeta refMeta;
            if(meta.isReferenceConverter()){
                Optional<BlockMeta> optional = MetaManager
                        .byConverter(meta.getReferenceConverter())
                        .get();
                if(!optional.isPresent()){
                    throw miss_reference_meta(meta);
                }else{
                    refMeta = optional.get();
                }
            }else{
                refMeta = meta.getReferenceMeta();
            }
            Object referenceModel = meta.getScope(model);

            if(meta.isList() &&
                    referenceModel instanceof List){
                //List
                printModel(refMeta, (List) referenceModel,", ",writer);
            }else if(meta.isRepeat() &&
                    referenceModel instanceof List){
                //Repeat
                printModel(refMeta, (List) referenceModel," ",writer);
            }else{
                printModel(refMeta, referenceModel, writer);
            }
        }else if(meta.isOverall() ||
                meta.isVirtual() ||
                meta.getSub() != null){
            //Virtual
            if(meta.isList() ||
                    meta.isRepeat()){
                if(meta.getSub().size() != 1){
                    throw MetaException.collection_meta_not_single(meta);
                }
                //item meta
                BlockMeta itemMeta = meta.getSub().get(0);
                //collection model
                Object collectionModel = meta.getScope(model);
                if(!(collectionModel instanceof List)){
                    throw MetaException.collection_model_not_collection(meta);
                }
                String delimiter = "";
                if(meta.isList()){
                    delimiter = ", ";
                }else if(meta.isRepeat()) {
                    delimiter = " ";
                }
                printModel(itemMeta, (List)collectionModel, delimiter, writer);
            }else{
                printModel(meta.getSub(),model," ",writer);
            }
        }else if(meta.isKeyword()){
            //Keyword
            writer.append(meta.getName());
        }else{
            //Data
            Object data = meta.getScope(model);
            if(data == null){
                throw miss_model(meta);
            }

            Optional<BlockMeta> optional = MetaManager
                    .byModel(data.getClass())
                    .get();

            if(optional.isPresent()){
                BlockMeta hiddenMeta = optional.get();
                printModel(hiddenMeta,data,writer);
            }else if(data instanceof List &&
                    meta.isCollection()){
                //collection model
                List<Object> collectionModel = (List)data;
                if(collectionModel.size() <= 0){
                    throw data_collection_empty(meta);
                }
                //delimiter
                String delimiter = "";
                if(meta.isList()){
                    delimiter = ", ";
                }else if(meta.isRepeat()) {
                    delimiter = " ";
                }

                List<StringWriter> collectionWriter = collectionModel
                        .stream()
                        .map(itemModel -> {
                            StringWriter itemWriter = new StringWriter();
                            Optional<BlockMeta> hideMeta = MetaManager
                                    .byModel(itemModel.getClass())
                                    .get();
                            if(hideMeta.isPresent()){
                                //item meta
                                BlockMeta itemMeta = hideMeta.get();
                                printModel(itemMeta, itemModel, itemWriter);
                            }else{
                                itemWriter.append(itemModel.toString());
                            }
                            return itemWriter;
                        })
                        .collect(Collectors.toList());

                String finalDelimiter = delimiter;
                String stringTemp = collectionWriter
                        .stream()
                        //joining
                        .flatMap(sw -> Stream.concat(
                                Stream.of(finalDelimiter),
                                Stream.of(sw.toString())
                        ))
                        .skip(1)
                        .collect(Collectors.joining(""));
                writer.append(stringTemp);
            }else{
                //Unknown
                writer.append(data.toString());
            }
        }
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
            return printMeta(block.getMeta());
        }else if(block.getMeta() == null){
            return printModel(block.getModel());
        }else{
            return printModel(block.getMeta(),block.getModel());
        }
    }


    /**
     * print model
     * @param model model
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter print(Object model){
        return new ModelMetaBlockPrinter()
                .printModel(model);
    }




    /**
     * print Meta Context
     */
    @SuppressWarnings({"WeakerAccess", "SameParameterValue", "unused"})
    public static class Context {

        private boolean printOverall;
        private StringWriter writer;

        /**
         * Absolute level
         */
        private int level;

        private BlockMeta meta;
        private BlockMeta.Style style;
        private BlockMeta.Style subStyle;
        private BlockMeta.SyntaxFormat syntax;
        private BlockMeta.SyntaxFormat subSyntax;

        public Context(){
            writer = new StringWriter();
        }

        public boolean isPrintOverall() {
            return printOverall;
        }

        public void setPrintOverall(boolean printOverall) {
            this.printOverall = printOverall;
        }

        public StringWriter getWriter() {
            return writer;
        }

        public void setWriter(StringWriter writer) {
            this.writer = writer;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public BlockMeta getMeta() {
            return meta;
        }

        public void setMeta(BlockMeta meta) {
            this.meta = meta;
        }

        public BlockMeta.Style getStyle() {
            return style;
        }

        public void setStyle(BlockMeta.Style style) {
            this.style = style;
        }

        public BlockMeta.Style getSubStyle() {
            return subStyle;
        }

        public void setSubStyle(BlockMeta.Style subStyle) {
            this.subStyle = subStyle;
        }

        public BlockMeta.SyntaxFormat getSyntax() {
            return syntax;
        }

        public void setSyntax(BlockMeta.SyntaxFormat syntax) {
            this.syntax = syntax;
        }

        public BlockMeta.SyntaxFormat getSubSyntax() {
            return subSyntax;
        }

        public void setSubSyntax(BlockMeta.SyntaxFormat subSyntax) {
            this.subSyntax = subSyntax;
        }

        /*

         */

        public Context clone(){
            Context clone = new Context();
            clone.setLevel(this.level);
            clone.setStyle(this.style);
            return clone;
        }

        public int getAbsoluteLevel() {
            return level < 0 ? 0 : level;
        }

        public void addLevel(int formatLevel) {
            level = level + formatLevel;
        }

        public Optional<BlockMeta.Style> format(){
            return Optional.ofNullable(style);
        }

        public Optional<BlockMeta.Style> sub_format(){
            return Optional.ofNullable(subStyle);
        }

        public Optional<BlockMeta.SyntaxFormat> syntax(){
            return Optional.ofNullable(syntax);
        }

        public Optional<BlockMeta.SyntaxFormat> sub_syntax(){
            return Optional.ofNullable(subSyntax);
        }

    }

}
