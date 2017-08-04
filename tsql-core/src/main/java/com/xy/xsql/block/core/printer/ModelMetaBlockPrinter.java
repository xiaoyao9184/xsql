package com.xy.xsql.block.core.printer;

import com.google.common.base.Strings;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.block.exception.MetaException;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.ModelMetaBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
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

    private final Logger logger;
//    private StringWriter writer;

    public ModelMetaBlockPrinter(){
//        writer = new StringWriter();
        logger = LoggerFactory.getLogger(ModelMetaBlockPrinter.class);
    }


    /**
     * print meta
     * @param meta meta
     * @return writer
     */
    public StringWriter printMeta(BlockMeta meta) {
        Context context = new Context();
        context.setPrintOverall(true);
        printMeta(meta,context);
        return context.getWriter();

//        //Syntax format
//        String overall = EMPTY.toString();
//        if(blockMeta.isOverall()) {
//            overall = new StringBuilder()
//                    .append(REFERENCE_START.toString())
//                    .append(blockMeta.getName())
//                    .append(REFERENCE_END.toString())
//                    .append(BLANKS.toString())
//                    .append(REFERENCE_LABEL.toString())
//                    .append(LINE.toString())
//                    .toString();
//        }
//
//        String startOptional = blockMeta.syntax()
//                .filter(BlockMeta.SyntaxFormat::isOptional)
//                .map(style -> OPTIONAL_START.toString())
//                .orElse(EMPTY.toString());
//        String startRequired = blockMeta.syntax()
//                .filter(BlockMeta.SyntaxFormat::isRequired)
//                .map(style -> REQUIRED_START.toString())
//                .orElse(EMPTY.toString());
//
//
//        String endOptional = blockMeta.syntax()
//                .filter(BlockMeta.SyntaxFormat::isOptional)
//                .map(style -> OPTIONAL_END.toString())
//                .orElse(EMPTY.toString());
//        String endRequired = blockMeta.syntax()
//                .filter(BlockMeta.SyntaxFormat::isRequired)
//                .map(style -> REQUIRED_END.toString())
//                .orElse(EMPTY.toString());
//
//        return new StringWriter()
//                .append(overall)
//                .append(startOptional)
//                .append(startOptional)
//                .append(context.getWriter().toString())
//                .append(endOptional)
//                .append(endRequired);
    }

    /**
     * print meta
     * @param meta meta
     * @param context context
     */
    public void printMeta(BlockMeta meta, Context context) {
        //fill context
        context.setMeta(meta);
        meta.syntax()
                .ifPresent(syntax -> {
                    context.setSyntax(syntax);
                    context.addLevel(syntax.getIndentation());
                });
        meta.sub_syntax()
                .ifPresent(syntax -> {
                    context.setSubSyntax(syntax);
                });

        //Syntax Format
        String overall = EMPTY.toString();
        if(meta.isOverall() &&
                context.isPrintOverall()) {
            overall = new StringBuilder()
                    .append(REFERENCE_START.toString())
                    .append(meta.getName())
                    .append(REFERENCE_END.toString())
                    .append(BLANKS.toString())
                    .append(REFERENCE_LABEL.toString())
                    .append(LINE.toString())
                    .toString();
        }

        String startOptional = meta.syntax()
                .filter(BlockMeta.SyntaxFormat::isOptional)
                .map(style -> OPTIONAL_START.toString())
                .orElse(EMPTY.toString());
        String startRequired = meta.syntax()
                .filter(BlockMeta.SyntaxFormat::isRequired)
                .map(style -> REQUIRED_START.toString())
                .orElse(EMPTY.toString());
        String startOptionalRequiredDelimiter = meta.syntax()
                .filter(s -> s.isRequired() || s.isOptional())
                .map(style -> BLANKS.toString())
                .orElse(EMPTY.toString());

        String startIndentation = meta.syntax()
                .filter(BlockMeta.SyntaxFormat::isIndentationContent)
                .map(style ->{
                    context.addLevel(1);
                    return LINE.toString() +
                            Strings.repeat(
                                    style.getIndentationChar(),
                                    context.getAbsoluteLevel());
                })
                .orElse(startOptionalRequiredDelimiter);

        context.getWriter()
                .append(overall)
                .append(startOptional)
                .append(startRequired)
                .append(startIndentation);

        //Syntax Content
        if(meta.isData()){
            //use Name
            String name = meta.syntax()
                    .filter(BlockMeta.SyntaxFormat::isReference)
                    .map(s ->
                            REFERENCE_START.toString() +
                            meta.getName() +
                            REFERENCE_END.toString())
                    .orElse(meta.getName());
            context.getWriter().append(name);
        }else if(meta.isAnonymousReference()){
            //no use name
            BlockMeta refMeta;
            if(meta.isReferenceMeta()){
                //Reference Meta
                refMeta = meta.getReferenceMeta();
            }else{
                //Reference Converter
                refMeta = MetaManager
                        .byConverter(meta.getReferenceConverter())
                        .meta()
                        .orElseThrow(MetaException::miss_meta);
            }
            printMeta(refMeta,context);
        }else if(meta.isOverall() ||
                meta.isVirtual()) {
            //Sub
            String delimiter;
            if(meta.isList()){
                delimiter = PREFIX_COMMA.toString();
            }else if(meta.isExclusive()){
                delimiter = PREFIX_ONE_OF.toString();
            }else{
                delimiter = BLANKS.toString();
            }
            printMeta(meta.getSub(),false,delimiter,context);
        }

        //Syntax Format
        String endIndentation = meta.syntax()
                .filter(BlockMeta.SyntaxFormat::isIndentationContent)
                .map(style ->{
                    context.addLevel(-1);
                    return LINE.toString() +
                            Strings.repeat(
                                    style.getIndentationChar(),
                                    context.getAbsoluteLevel());
                })
                .orElse(startOptionalRequiredDelimiter);

        String endOptional = meta.syntax()
                .filter(BlockMeta.SyntaxFormat::isOptional)
                .map(style -> OPTIONAL_END.toString())
                .orElse(EMPTY.toString());
        String endRequired = meta.syntax()
                .filter(BlockMeta.SyntaxFormat::isRequired)
                .map(style -> REQUIRED_END.toString())
                .orElse(EMPTY.toString());

        //list or repeat
        String repeat = EMPTY.toString();
        if(!meta.isVirtual()){
            if(meta.isList() &&
                    meta.isRepeat()){
                repeat = BLANKS.toString() + REPEATED_COMMA_BLANKS.toString();
            }else if(meta.isList()){
                repeat = BLANKS.toString() + REPEATED_COMMA.toString();
            }else if(meta.isRepeat()){
                repeat = BLANKS.toString() + REPEATED_BLANKS.toString();
            }
        }

        context.getWriter()
                .append(endIndentation)
                .append(endRequired)
                .append(endOptional)
                .append(repeat);
    }

    /**
     * print meta collection
     * @param collectionMeta meta collection
     * @param printOverall printOverall
     * @param delimiter delimiter
     * @param context context
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
                    itemContext.setLevel(context.getAbsoluteLevel());
                    printMeta(sub,itemContext);
                    return itemContext;
                })
                //joining
                .flatMap(itemContext -> {
                    //If the format is defined, the parent default format is ignored
                    //TODO must merge
//                    String itemDefaultLine = itemContext.syntax()
//                            .map(f -> EMPTY.toString())
//                            .orElse(defaultLine);
//                    String itemDefaultIndentation = itemContext.syntax()
//                            .map(f -> EMPTY.toString())
//                            .orElse(defaultIndentation);
//                    String itemDefaultDelimiter = itemContext.syntax()
//                            .map(f -> delimiter)
//                            .orElse(defaultDelimiter);

                    String itemDefaultLine = defaultLine;
                    String itemDefaultIndentation = defaultIndentation;
                    String itemDefaultDelimiter = defaultDelimiter;

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

                    return Stream.of(
                            itemDelimiter,
                            itemContext.getWriter().toString());
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
                .meta()
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
                logger.warn("Exclusive Meta maybe hide default predicate!");
                //hide exclusive
                /**
                 * {@link com.xy.xsql.block.tsql.core.clause.select.GroupByConverter.ColumnNameItemConverter}
                 */
                Optional<BlockMeta> optional = MetaManager
                        .byModel(model.getClass())
                        .meta();
                if(!optional.isPresent()){
                    throw MetaException.nothing_pass_exclusive(meta);
                }else{
                    if(optional.get().equals(meta)){
                        throw MetaException.hide_exclusive_cycle(meta);
                    }

                    printModel(optional.get(), model, writer);
                }
            }
        }else if(meta.isReference()){
            //Reference child
            BlockMeta refMeta;
            if(meta.isReferenceConverter()){
                Optional<BlockMeta> optional = MetaManager
                        .byConverter(meta.getReferenceConverter())
                        .meta();
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
                    .meta();

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
                                    .meta();
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
            return clone;
        }

        public int getAbsoluteLevel() {
            return level < 0 ? 0 : level;
        }

        public void addLevel(int formatLevel) {
            level = level + formatLevel;
        }

        public Optional<BlockMeta.SyntaxFormat> syntax(){
            return Optional.ofNullable(syntax);
        }

        public Optional<BlockMeta.SyntaxFormat> sub_syntax(){
            return Optional.ofNullable(subSyntax);
        }

    }

}
