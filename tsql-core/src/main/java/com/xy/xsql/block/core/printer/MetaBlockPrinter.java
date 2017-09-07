package com.xy.xsql.block.core.printer;

import com.google.common.base.Strings;
import com.xy.xsql.block.exception.MetaException;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.xy.xsql.block.model.BlockMeta.BlockConvention.*;
import static com.xy.xsql.block.model.BlockMeta.BlockDelimiterConvention.PREFIX_COMMA;
import static com.xy.xsql.block.model.BlockMeta.BlockDelimiterConvention.PREFIX_ONE_OF;

/**
 *
 * Print Meta
 * Only need to go deep into 'Data meta'
 *
 * Created by xiaoyao9184 on 2017/6/9.
 */
@SuppressWarnings({"WeakerAccess", "unused", "SameParameterValue"})
public class MetaBlockPrinter
        implements BlockPrinter<MetaBlock,StringWriter> {

    private final Logger logger;

    public MetaBlockPrinter(){
        logger = LoggerFactory.getLogger(MetaBlockPrinter.class);
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
     * print block
     * @param block block
     * @return writer
     */
    @Override
    public StringWriter print(MetaBlock block) {
        return printMeta(block.getMeta());
    }


    /**
     * print meta
     * @param meta meta
     * @return writer
     */
    @SuppressWarnings("unchecked")
    public static StringWriter print(BlockMeta meta){
        return new MetaBlockPrinter()
                .printMeta(meta);
    }


    /**
     * Meta Print Context
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
