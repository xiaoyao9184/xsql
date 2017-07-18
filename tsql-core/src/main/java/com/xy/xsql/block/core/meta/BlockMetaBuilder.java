package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.builder.CodeTreeBuilder;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.xy.xsql.block.model.BlockMeta.BlockConvention.EMPTY;
import static com.xy.xsql.block.model.BlockMeta.BlockConvention.LINE;
import static com.xy.xsql.block.model.BlockMeta.BlockDelimiterConvention.NO_PREFIX_COMMA;
import static com.xy.xsql.block.model.BlockMeta.BlockDelimiterConvention.NO_PREFIX_ONE_OF;
import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew2;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 * @param <ParentBuilder> ParentBuilder
 * @param <Scope> Model Scope
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue", "unused"})
public class BlockMetaBuilder<ParentBuilder, Scope>
        extends CodeTreeBuilder<BlockMetaBuilder<ParentBuilder, Scope>, ParentBuilder, BlockMeta> {

    public BlockMetaBuilder() {
        super(new BlockMeta());
    }

    public BlockMetaBuilder(BlockMeta meta) {
        super(meta);
    }


    /*
    Base
     */

    /**
     * set name
     * @param name name
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> name(String name){
        target.setName(name);
        return this;
    }

    /**
     * set description
     * @param description description
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> description(String description){
        target.setDescription(description);
        return this;
    }

    /*
    Data
     */

    /**
     * set scope getter
     * @param getter scope getter
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> scope(Function<Scope,?> getter){
        target.setScopeGetter(getter);
        return this;
    }

    /*
    Type
     */

    /**
     * set overall meta
     * @param name name
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> overall(String name) {
        target.setOverall(true);
        target.setName(name);
        return this;
    }

    /**
     * set optional meta
     * @param predicate predicate
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> optional(Predicate<Scope> predicate) {
        target.setOptionalPredicate(predicate);
        return this.syntax_optional();
    }

    /**
     * set keywords meta
     * @param keywords keywords
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> keyword(String keywords) {
        target.setKeyword(true);
        target.setName(keywords);
        target.setScopeGetter(d -> keywords);
        return this;
    }

    /**
     * set keywords meta
     * @param keywords keywords
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> keyword(Enum keywords) {
        return keyword(keywords.toString());
    }

    /**
     * set name converter reference meta
     * @param converterClass converter class
     * @param <C> converter type
     * @return THIS
     */
    public <C extends ModelMetaBlockConverter> BlockMetaBuilder<ParentBuilder, Scope> ref(Class<C> converterClass) {
        target.setReferenceConverter(converterClass);
//        if(target.isNamedReference()){
//            this.syntax_reference();
//        }
        //TODO must have name
        this.syntax_reference();
        return this;
    }

    /**
     * set anonymous reference meta
     * @param meta meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> ref(BlockMeta meta) {
        target.setReferenceMeta(meta);
        if(target.isNamedReference()){
            this.syntax_reference();
        }
        return this;
    }

    /**
     * build anonymous reference meta
     * @return BlockMetaBuilder
     */
    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> ref() {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope>
                (initSet(BlockMeta::new,
                        target::getReferenceMeta,
                        target::setReferenceMeta))
                .in(this);
    }

    /**
     * set list collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> list() {
        target.setList(true);
        return this;
    }

    /**
     * set repeat collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> repeat() {
        target.setRepeat(true);
        return this;
    }


    /*
    Sub meta
     */

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> sub() {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> sub(String name) {
        return sub()
                .name(name);
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_ref(BlockMeta meta) {
        return sub()
                .description(meta.getDescription())
                .ref(meta)
                .scope(d -> d)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_ref(BlockMeta meta, Function<Scope,?> scopeGetter) {
        return sub()
                .description(meta.getDescription())
                .ref(meta)
                .scope(scopeGetter)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_keyword(Enum keywords) {
        return sub()
                .keyword(keywords)
                .and();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> sub_list(String name) {
        return sub()
                .name(name)
                .list();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> sub_list(BlockMeta meta) {
        return sub()
                .description(meta.getDescription())
                .list()
                .ref(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> sub_repeat(String name) {
        return sub()
                .name(name)
                .repeat();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> sub_repeat(BlockMeta meta) {
        return sub()
                .description(meta.getDescription())
                .repeat()
                .ref(meta);
    }


    /*
    Case meta
     */

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> czse(Predicate<Scope> predicate) {
        initAdd(predicate,
                target::getExclusivePredicate,
                target::setExclusivePredicate);
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> czse(Predicate<Scope> predicate, String name) {
        return czse(predicate)
                .name(name);
    }

    public BlockMetaBuilder<ParentBuilder, Scope> czse_ref(Predicate<Scope> predicate, BlockMeta meta) {
        return czse(predicate)
                .description(meta.getDescription())
                .ref(meta)
                .scope(d -> d)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> czse_ref(Predicate<Scope> predicate, BlockMeta meta, Function<Scope,?> scopeGetter) {
        return czse(predicate)
                .description(meta.getDescription())
                .ref(meta)
                .scope(scopeGetter)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> czse_keyword(Predicate<Scope> predicate, Enum keywords) {
        return czse(predicate)
                .keyword(keywords)
                .and();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> czse_list(Predicate<Scope> predicate, String name) {
        return czse(predicate)
                .name(name)
                .list();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> czse_list(Predicate<Scope> predicate, BlockMeta meta) {
        return czse(predicate)
                .description(meta.getDescription())
                .list()
                .ref(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> czse_repeat(Predicate<Scope> predicate, String name) {
        return czse(predicate)
                .name(name)
                .repeat();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Scope>, Scope> czse_repeat(Predicate<Scope> predicate, BlockMeta meta) {
        return czse(predicate)
                .description(meta.getDescription())
                .repeat()
                .ref(meta);
    }


    /*
    Format
     */

    public FormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>> format() {
        return new FormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>>
                (initSet(BlockMeta.Format::new,
                        target::getFormat,
                        target::setFormat))
                .in(this);
    }

    public FormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>> sub_format() {
        return new FormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>>
                (initSet(BlockMeta.Format::new,
                        target::getSubFormat,
                        target::setSubFormat))
                .in(this);
    }


    /**
     * use line of delimiter
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> format_line() {
        return format()
                .line()
                .indentation(0)
                .delimiter(true)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> format_line(boolean emptyDelimiter) {
        if(emptyDelimiter){
            return format()
                    .line()
                    .indentation(0)
                    .delimiter(EMPTY.toString())
                    .and();
        }
        return format()
                .line()
                .indentation(0)
                .delimiter(true)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> format_line_empty_delimiter() {
        return format()
                .line()
                .indentation(0)
                .delimiter(EMPTY.toString())
                .and();
    }

    /**
     * use line with empty delimiter
     * @deprecated Only enable the line flag will inherit the parent indentation
     * @see #format_line_empty_delimiter()
     * @return THIS
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> format_line_delimiter() {
        return format()
                .delimiter(LINE.toString())
                .and();
        //can not exclude indentation
//        return format()
//                .line()
//                .delimiter(EMPTY.toString())
//                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> format_empty_delimiter() {
        return format()
                .delimiter(EMPTY.toString())
                .and();
    }

//    public BlockMetaBuilder<ParentBuilder, Scope> format_indentation() {
//        return format()
//                .line()
//                .indentation(0)
//                .delimiter(EMPTY.toString())
//                .and();
//    }

    public BlockMetaBuilder<ParentBuilder, Scope> format_indentation_right() {
        return format()
                .line()
                .indentation(1)
                .delimiter(EMPTY.toString())
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> format_indentation_left() {
        return format()
                .line()
                .indentation(-1)
                .delimiter(EMPTY.toString())
                .and();
    }



    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_line() {
        return sub_format()
                .line()
                .indentation(0)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_line(boolean emptyDelimiter) {
        if(emptyDelimiter){
            return sub_format()
                    .line()
                    .indentation(0)
                    .delimiter(EMPTY.toString())
                    .and();
        }
        return sub_format()
                .line()
                .indentation(0)
                .delimiter(true)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_line_empty_delimiter() {
        return sub_format()
                .line()
                .indentation(0)
                .delimiter(EMPTY.toString())
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_indentation_right() {
        return sub_format()
                .line()
                .indentation(1)
                .delimiter(EMPTY.toString())
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_indentation_left() {
        return sub_format()
                .line()
                .indentation(-1)
                .delimiter(EMPTY.toString())
                .and();
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_line_delimiter() {
        return sub_format()
                .delimiter(LINE.toString())
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> sub_format_empty_delimiter() {
        return sub_format()
                .delimiter(EMPTY.toString())
                .and();
    }


     /*
    Syntax
     */

    public SyntaxFormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>> syntax() {
        return new SyntaxFormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>>
                (initSet(BlockMeta.SyntaxFormat::new,
                        target::getSyntaxFormat,
                        target::setSyntaxFormat))
                .in(this);
    }

    public SyntaxFormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>> sub_syntax() {
        return new SyntaxFormatBuilder<BlockMetaBuilder<ParentBuilder, Scope>>
                (initSet(BlockMeta.SyntaxFormat::new,
                        target::getSubSyntaxFormat,
                        target::setSubSyntaxFormat))
                .in(this);
    }


    public BlockMetaBuilder<ParentBuilder,Scope> syntax_required() {
        return syntax()
                .required(true)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder,Scope> syntax_optional() {
        return syntax()
                .optional(true)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> syntax_reference() {
        return syntax()
                .reference(true)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Scope> syntax_reference_remove() {
        return syntax()
                .reference(false)
                .and();
    }

    /**
     * use new line
     * for NOT Collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_line() {
        return syntax()
                .line()
                .indentation(0)
                .delimiter(EMPTY.toString())
                .and();
    }

    /**
     * use new line with indentation
     * for NOT Collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_indentation_right() {
        return syntax()
                .line()
                .indentation(1)
                .delimiter(EMPTY.toString())
                .and();
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_line_empty_delimiter() {
        return syntax()
                .line()
                .indentation(0)
                .delimiter(EMPTY.toString())
                .and();
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_empty_delimiter() {
        return syntax()
                .delimiter(EMPTY.toString())
                .and();
    }



    public BlockMetaBuilder<ParentBuilder,Scope> syntax_context_indentation() {
        return syntax()
                .indentation_content()
                .and();
    }


    /**
     * sub use new line
     * for Collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_sub_line() {
        String delimiter;
        //noinspection Duplicates
        if(target.isList()){
            delimiter = NO_PREFIX_COMMA.toString();
        }else if(target.isExclusive()){
            delimiter = NO_PREFIX_ONE_OF.toString();
        }else{
            delimiter = EMPTY.toString();
        }
        return sub_syntax()
                .line()
                .indentation(0)
                .delimiter(delimiter)
                .and();
    }

    /**
     * sub use new line with indentation
     * for Collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_sub_indentation_right() {
        String delimiter;
        //noinspection Duplicates
        if(target.isList()){
            delimiter = NO_PREFIX_COMMA.toString();
        }else if(target.isExclusive()){
            delimiter = NO_PREFIX_ONE_OF.toString();
        }else{
            delimiter = EMPTY.toString();
        }
        return sub_syntax()
                .line()
                .indentation(1)
                .delimiter(delimiter)
                .and();
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_sub_line_empty_delimiter() {
        return sub_syntax()
                .line()
                .indentation(0)
                .delimiter(EMPTY.toString())
                .and();
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> syntax_sub_empty_delimiter() {
        return sub_syntax()
                .delimiter(EMPTY.toString())
                .and();
    }

    /*
    Style
     */

    @Deprecated
    public StyleBuilder<BlockMetaBuilder<ParentBuilder, Scope>> style() {
        return new StyleBuilder<BlockMetaBuilder<ParentBuilder, Scope>>
                (initSet(BlockMeta.Style::new,
                        target::getStyle,
                        target::setStyle))
                .in(this);
    }


    /**
     * use line delimiter for each sub
     * @return THIS
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> style_sub_line_delimiter() {
        return style().subLineDelimiter(true);
    }

    /**
     * use line delimiter for optional and required
     * @return THIS
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> style_convention_line_delimiter() {
        return style().conventionLineDelimiter(true);
    }

    /**
     * use
     * @return THIS
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> style_start_new_line() {
        return style().startNewLine(true);
    }

    /**
     * use
     * @return THIS
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Scope> style_end_new_line() {
        return style().endNewLine(true);
    }



    /**
     * FormatBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"TypeParameterHidesVisibleType", "unused"})
    public class FormatBuilder<ParentBuilder>
            extends CodeTreeBuilder<FormatBuilder<ParentBuilder>, ParentBuilder, BlockMeta.Format> {

        public FormatBuilder() {
            super(new BlockMeta.Format());
        }

        public FormatBuilder(BlockMeta.Format format) {
            super(format);
        }


        public FormatBuilder<ParentBuilder> line(boolean use){
            target.setNewLine(use);
            return this;
        }

        public FormatBuilder<ParentBuilder> line(){
            target.setNewLine(true);
            return this;
        }

        public FormatBuilder<ParentBuilder> indentation(String indentation){
            target.setIndentationChar(indentation);
            return this;
        }

        public FormatBuilder<ParentBuilder> indentation(int level){
            target.setIndentation(level);
            return this;
        }

        public FormatBuilder<ParentBuilder> delimiter(boolean useDefault) {
            target.setUseDefaultDelimiter(useDefault);
            return this;
        }

        public FormatBuilder<ParentBuilder> delimiter(String delimiter){
            target.setUseDefaultDelimiter(false);
            target.setDelimiterChar(delimiter);
            return this;
        }
    }


    /**
     * StyleBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "TypeParameterHidesVisibleType", "unused"})
    public class SyntaxFormatBuilder<ParentBuilder>
            extends CodeTreeBuilder<SyntaxFormatBuilder<ParentBuilder>, ParentBuilder, BlockMeta.SyntaxFormat> {

        public SyntaxFormatBuilder() {
            super(new BlockMeta.SyntaxFormat());
        }

        public SyntaxFormatBuilder(BlockMeta.SyntaxFormat syntaxFormat) {
            super(syntaxFormat);
        }


        public SyntaxFormatBuilder<ParentBuilder> required(boolean flag) {
            target.setRequired(flag);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> optional(boolean flag) {
            target.setOptional(flag);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> reference(boolean flag) {
            target.setReference(flag);
            return this;
        }


        public SyntaxFormatBuilder<ParentBuilder> line(boolean use){
            target.setNewLine(use);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> line(){
            target.setNewLine(true);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> indentation_content(){
            target.setIndentationContent(true);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> indentation(String indentation){
            target.setIndentationChar(indentation);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> indentation(int level){
            target.setIndentation(level);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> delimiter(boolean useDefault) {
            target.setUseDefaultDelimiter(useDefault);
            return this;
        }

        public SyntaxFormatBuilder<ParentBuilder> delimiter(String delimiter){
            target.setUseDefaultDelimiter(false);
            target.setDelimiterChar(delimiter);
            return this;
        }
    }


    /**
     * StyleBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"SameParameterValue", "TypeParameterHidesVisibleType", "unused"})
    public class StyleBuilder<ParentBuilder>
            extends CodeTreeBuilder<StyleBuilder<ParentBuilder>, ParentBuilder, BlockMeta.Style> {

        public StyleBuilder() {
            super(new BlockMeta.Style());
        }

        public StyleBuilder(BlockMeta.Style style) {
            super(style);
        }


        public ParentBuilder required(boolean flag) {
            target.setRequired(flag);
            return and();
        }

        public ParentBuilder optional(boolean flag) {
            target.setOptional(flag);
            return and();
        }

        public ParentBuilder reference(boolean flag) {
            target.setReference(flag);
            return and();
        }

        public ParentBuilder subLineDelimiter(boolean flag) {
            target.setSubLineDelimiter(flag);
            return and();
        }

        public ParentBuilder conventionLineDelimiter(boolean flag) {
            target.setConventionLineDelimiter(flag);
            return and();
        }

        public ParentBuilder startNewLine(boolean flag) {
            target.setStartNewLine(flag);
            return and();
        }

        public ParentBuilder endNewLine(boolean flag) {
            target.setEndNewLine(flag);
            return and();
        }
    }

}
