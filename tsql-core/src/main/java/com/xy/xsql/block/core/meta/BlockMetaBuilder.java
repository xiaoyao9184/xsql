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
        target.setOptional(true);
        target.setOptionalPredicate(predicate);
        return this;
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
        return this;
    }

    /**
     * set anonymous reference meta
     * @param meta meta
     * @return THIS
     */
    public BlockMetaBuilder<ParentBuilder, Reference> ref(BlockMeta meta) {
        target.setReferenceMeta(meta);
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
                .name(meta.getName())
                .description(meta.getDescription())
                .ref(meta)
                .data(d -> d)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Reference> sub_ref(BlockMeta meta, Function<Reference,?> modelGetter) {
        return sub()
                .name(meta.getName())
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
                .name(meta.getName())
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
                .name(meta.getName())
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
                .name(meta.getName())
                .description(meta.getDescription())
                .ref(meta)
                .data(d -> d)
                .and();
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        //TODO TEST
//        return sub_ref(meta);

//        initAdd(meta,
//                target::getSub,
//                target::setSub);
//        return this;
    }

    public BlockMetaBuilder<ParentBuilder, Reference> czse_ref(Predicate<Reference> predicate, BlockMeta meta, Function<Reference,?> modelGetter) {
        return czse(predicate)
                .name(meta.getName())
                .description(meta.getDescription())
                .ref(meta)
                .data(modelGetter)
                .and();
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        return sub_ref(meta, data);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> czse_keyword(Predicate<Reference> predicate, Enum keywords) {
        return czse(predicate)
                .keyword(keywords)
                .and();
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        return sub_keyword(keywords);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_list(Predicate<Reference> predicate, String name) {
        return czse(predicate)
                .name(name)
                .list();
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        return sub_list(name);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_list(Predicate<Reference> predicate, BlockMeta meta) {
        return czse(predicate)
                .name(meta.getName())
                .description(meta.getDescription())
                .list()
                .ref(meta);
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        return sub_list(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_repeat(Predicate<Reference> predicate, String name) {
        return czse(predicate)
                .name(name)
                .repeat();
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        return sub_repeat(name);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_repeat(Predicate<Reference> predicate, BlockMeta meta) {
        return czse(predicate)
                .name(meta.getName())
                .description(meta.getDescription())
                .repeat()
                .ref(meta);
//        exclusive();
//        initAdd(predicate,
//                target::getExclusivePredicate,
//                target::setExclusivePredicate);
//        return sub_repeat(meta);
    }


    /*
    Style
     */

    public BlockMetaBuilder<ParentBuilder,Reference> required(){
        target.setRequired(true);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> optional(){
        target.setOptional(true);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> subTakeLine() {
        target.setEachSubTakeLine(true);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> headFootTakeLine() {
        target.setHeadFootTakeLine(true);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> startNewline() {
        target.setStartNewLine(true);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> endNewline() {
        target.setEndNewLine(true);
        return this;
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


    /**
     * FormatBuilder
     * @param <ParentBuilder>
     */
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

}
