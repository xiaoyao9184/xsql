package com.xy.xsql.block.core.meta;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.builder.CodeTreeBuilder;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew2;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 * @param <ParentBuilder>
 * @param <Reference> Refer to create blocks
 */
@SuppressWarnings("WeakerAccess")
public class BlockMetaBuilder<ParentBuilder,Reference>
        extends CodeTreeBuilder<BlockMetaBuilder<ParentBuilder,Reference>, ParentBuilder, BlockMeta> {

    public BlockMetaBuilder() {
        super(new BlockMeta());
    }

    public BlockMetaBuilder(BlockMeta block) {
        super(block);
    }


    /*
    Base
     */

    /**
     * set name
     * @param name name
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> name(String name){
        target.setName(name);
        return this;
    }

    /**
     * set description
     * @param description description
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> description(String description){
        target.setDescription(description);
        return this;
    }

    /*
    Data
     */

    /**
     * set data
     * @param getter data getter
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> data(Function<Reference,?> getter){
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
    public BlockMetaBuilder<ParentBuilder,Reference> overall(String name) {
        target.setOverall(true);
        target.setName(name);
        return this;
    }

    /**
     * set optional meta
     * @param predicate predicate
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> optional(Predicate<Reference> predicate) {
        target.setOptionalPredicate(predicate);
        return this.style().optional(true);
    }

    /**
     * set keywords meta
     * @param keywords keywords
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> keyword(String keywords) {
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
    public BlockMetaBuilder<ParentBuilder,Reference> keyword(Enum keywords) {
        return keyword(keywords.toString());
    }

    /**
     * set name converter reference meta
     * @param converterClass converter class
     * @param <C> converter type
     * @return THIS
     */
    public <C extends ModelMetaBlockConverter> BlockMetaBuilder<ParentBuilder, Reference> ref(Class<C> converterClass) {
        target.setReferenceConverter(converterClass);
//        if(target.isNamedReference()){
//            this.style_reference();
//        }
        //TODO must have name
        this.style_reference();
        return this;
    }

    /**
     * set anonymous reference meta
     * @param meta meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Reference> ref(BlockMeta meta) {
        target.setReferenceMeta(meta);
        if(target.isNamedReference()){
            this.style_reference();
        }
        return this;
    }

    /**
     * build anonymous reference meta
     * @return BlockMetaBuilder
     */
    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Reference>,Reference> ref() {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Reference>, Reference>
                (initSet(BlockMeta::new,
                        target::getReferenceMeta,
                        target::setReferenceMeta))
                .in(this);
    }

    /**
     * set list collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> list() {
        target.setList(true);
        return this;
    }

    /**
     * set repeat collection meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> repeat() {
        target.setRepeat(true);
        return this;
    }


    /*
    Sub meta
     */

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub() {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub(String name) {
        return sub()
                .name(name);
    }

    public BlockMetaBuilder<ParentBuilder, Reference> sub_ref(BlockMeta meta) {
        return sub()
                .description(meta.getDescription())
                .ref(meta)
                .data(d -> d)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Reference> sub_ref(BlockMeta meta, Function<Reference,?> modelGetter) {
        return sub()
                .description(meta.getDescription())
                .ref(meta)
                .data(modelGetter)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder,Reference> sub_keyword(Enum keywords) {
        return sub()
                .keyword(keywords)
                .and();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_list(String name) {
        return sub()
                .name(name)
                .list();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_list(BlockMeta meta) {
        return sub()
                .description(meta.getDescription())
                .list()
                .ref(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_repeat(String name) {
        return sub()
                .name(name)
                .repeat();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_repeat(BlockMeta meta) {
        return sub()
                .description(meta.getDescription())
                .repeat()
                .ref(meta);
    }


    /*
    Case meta
     */

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse(Predicate<Reference> predicate) {
        initAdd(predicate,
                target::getExclusivePredicate,
                target::setExclusivePredicate);
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse(Predicate<Reference> predicate, String name) {
        return czse(predicate)
                .name(name);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> czse_ref(Predicate<Reference> predicate, BlockMeta meta) {
        return czse(predicate)
                .description(meta.getDescription())
                .ref(meta)
                .data(d -> d)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Reference> czse_ref(Predicate<Reference> predicate, BlockMeta meta, Function<Reference,?> modelGetter) {
        return czse(predicate)
                .description(meta.getDescription())
                .ref(meta)
                .data(modelGetter)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder,Reference> czse_keyword(Predicate<Reference> predicate, Enum keywords) {
        return czse(predicate)
                .keyword(keywords)
                .and();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_list(Predicate<Reference> predicate, String name) {
        return czse(predicate)
                .name(name)
                .list();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_list(Predicate<Reference> predicate, BlockMeta meta) {
        return czse(predicate)
                .description(meta.getDescription())
                .list()
                .ref(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_repeat(Predicate<Reference> predicate, String name) {
        return czse(predicate)
                .name(name)
                .repeat();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_repeat(Predicate<Reference> predicate, BlockMeta meta) {
        return czse(predicate)
                .description(meta.getDescription())
                .repeat()
                .ref(meta);
    }


    /*
    Format
     */

    public FormatBuilder<BlockMetaBuilder<ParentBuilder,Reference>> format() {
        return new FormatBuilder<BlockMetaBuilder<ParentBuilder,Reference>>
                (initSet(BlockMeta.Format::new,
                        target::getFormat,
                        target::setFormat))
                .in(this);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> format_line() {
        return format().line();
    }

    public BlockMetaBuilder<ParentBuilder,Reference> format_indentation(int level) {
        format().line();
        return format().indentation(level);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> format_indentation_right() {
        format().line();
        return format().indentation(1);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> format_indentation_left() {
        format().line();
        return format().indentation(-1);
    }

    /*
    Style
     */

    public StyleBuilder<BlockMetaBuilder<ParentBuilder,Reference>> style() {
        return new StyleBuilder<BlockMetaBuilder<ParentBuilder,Reference>>
                (initSet(BlockMeta.Style::new,
                        target::getStyle,
                        target::setStyle))
                .in(this);
    }

    /**
     * style_required style
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_required(){
        return this.style().required(true);
    }

    /**
     * optional style
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_optional() {
        return style().optional(true);
    }

    /**
     * reference style
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_reference() {
        return style().reference(true);
    }

    /**
     * remove reference style
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_remove_reference() {
        return style().reference(false);
    }

    /**
     * use line delimiter for each sub
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_sub_line_delimiter() {
        return style().subLineDelimiter(true);
    }

    /**
     * use line delimiter for optional and required
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_convention_line_delimiter() {
        return style().conventionLineDelimiter(true);
    }

    /**
     * use
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_start_new_line() {
        return style().startNewLine(true);
    }

    /**
     * use
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder,Reference> style_end_new_line() {
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

        public ParentBuilder line(){
            target.setNewLine(true);
            return and();
        }

        public ParentBuilder indentation(int level){
            target.setIndentation(level);
            return and();
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
