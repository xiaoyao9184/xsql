package com.xy.xsql.block.core;

import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.core.builder.CodeTreeBuilder;

import java.util.Collection;
import java.util.Objects;
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
    Block Base Info
     */

    public BlockMetaBuilder<ParentBuilder,Reference> overall(String name) {
        target.setName(name);
        target.setOverall(true);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> name(String name){
        target.setName(name);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> description(String description){
        target.setDescription(description);
        return this;
    }


    /*
    Block Data&Context
     */

    public BlockMetaBuilder<ParentBuilder,Reference> data(Function<Reference,?> getter){
        target.setDataGetter(getter);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> data(Object data){
        target.setData(data);
        return this;
    }




    //default
    private static Predicate<?> required = t -> true;
    private static Predicate<?> optional = t -> true;
    private Predicate<Reference> notNull = Objects::nonNull;
    private Predicate<Reference> oneMore = r -> {
        if(r instanceof Collection){
            Collection collection = (Collection)r;
            return collection.size() >= 1;
        }
        return false;
    };




    /*
    Block Style
     */

    /**
     *
     * @return
     */
    public BlockMetaBuilder<ParentBuilder,Reference> required(){
        target.setRequired(true);
        target.addVerifier(notNull);
        return this;
    }

    /**
     * Can be optional
     * @return
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder,Reference> optional(){
        target.setOptional(true);
        return this;
    }

    /**
     *
     * @param predicate
     * @return
     */
    public BlockMetaBuilder<ParentBuilder,Reference> optional(Predicate<Reference> predicate) {
        target.setOptional(true);
        target.setOptionalFilter(predicate);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> verifier(Predicate<Reference> filter) {
        target.addVerifier(filter);
        return this;
    }


    /*
    Block Type
     */

    public BlockMetaBuilder<ParentBuilder,Reference> keyword(Enum keywords) {
        target.setKeyword(true);
        target.setData(keywords.toString());
        target.setName(keywords.toString());
        return this;
    }

    public <C extends MetaContextBlockConverter> BlockMetaBuilder<ParentBuilder, Reference> ref(Class<C> refClass) {
        target.setRefClass(refClass);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder, Reference> ref(BlockMeta meta) {
        target.setRefMeta(meta);
        return this;
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Reference>,Reference> meta() {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder, Reference>, Reference>
                (initSet(BlockMeta::new,
                        target::getRefMeta,
                        target::setRefMeta))
                .in(this);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> list() {
        target.setList(true);
        return this;
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder,Reference> list(String name) {
        target.setList(true);
        return sub(name)
                .and();
    }

    /**
     * Not allowed to modify
     * @param meta
     * @return
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Reference> list(BlockMeta meta) {
        target.setList(true);
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> repeat() {
        target.setRepeat(true);
        return this;
    }

    @Deprecated
    public BlockMetaBuilder<ParentBuilder,Reference> repeat(String name) {
        target.setRepeat(true);
        return sub(name)
                .and();
    }

    /**
     * Not allowed to modify
     * @param meta
     * @return
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Reference> repeat(BlockMeta meta) {
        target.setRepeat(true);
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder, Reference> exclusive() {
        target.setExclusive(true);
        return this;
    }


    /*
    Sub Block
     */

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub() {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(null);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub(String name) {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(name);
    }

    /**
     * Not allowed to modify
     * @param meta
     * @return
     */
    @Deprecated
    public BlockMetaBuilder<ParentBuilder, Reference> sub_meta(BlockMeta meta) {
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> sub_keyword(Enum keywords) {
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .keyword(keywords)
                .and();
    }

    public BlockMetaBuilder<ParentBuilder, Reference> sub_ref(BlockMeta meta, Object context) {
        return sub()
                .name(meta.getName())
                .description(meta.getDescription())
                .ref(meta)
                .data(context)
                .and();
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_list(String name) {
        return sub(name)
                .list();
//        return sub()
//                .list(name);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_list(BlockMeta meta) {
        return sub()
                .name(meta.getName())
                .description(meta.getDescription())
                .list()
                .ref(meta);
//        return sub()
//                .list(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_repeat(String name) {
        return sub(name)
                .repeat();
//        return sub()
//                .repeat(name);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> sub_repeat(BlockMeta meta) {
        return sub()
                .name(meta.getName())
                .description(meta.getDescription())
                .repeat()
                .ref(meta);
//        return sub()
//                .repeat(meta);
    }



    /*
    Case Block
     */

    @Deprecated
    public WhenThenReferenceBlockBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> when(Predicate<Reference> predicate) {
        exclusive();
        return new WhenThenReferenceBlockBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                ()
                .enter(this,
                        d -> {
                            initAdd(predicate,
                                    target::getCasePredicate,
                                    target::setCasePredicate);
                            initAdd(d,
                                    target::getSub,
                                    target::setSub);
                        });
    }


    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse(Predicate<Reference> predicate) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse(Predicate<Reference> predicate, String name) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return new BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(BlockMeta::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(name);
    }

    public BlockMetaBuilder<ParentBuilder,Reference> czse_meta(Predicate<Reference> predicate, BlockMeta meta) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public BlockMetaBuilder<ParentBuilder,Reference> czse_keyword(Predicate<Reference> predicate, Enum keywords) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return sub_keyword(keywords);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_list(Predicate<Reference> predicate, String name) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return sub_list(name);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_list(Predicate<Reference> predicate, BlockMeta meta) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return sub_list(meta);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_repeat(Predicate<Reference> predicate, String name) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return sub_repeat(name);
    }

    public BlockMetaBuilder<BlockMetaBuilder<ParentBuilder,Reference>,Reference> czse_repeat(Predicate<Reference> predicate, BlockMeta meta) {
        exclusive();
        initAdd(predicate,
                target::getCasePredicate,
                target::setCasePredicate);
        return sub_repeat(meta);
    }


    /*
    Style
     */

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


    @Deprecated
    public class WhenThenReferenceBlockBuilder<ParentBuilder,Reference>
            extends CodeTreeBuilder<WhenThenReferenceBlockBuilder<ParentBuilder,Reference>, ParentBuilder, BlockMeta> {

        public WhenThenReferenceBlockBuilder() {
            super(new BlockMeta());
        }

        public WhenThenReferenceBlockBuilder(BlockMeta blockMeta) {
            super(blockMeta);
        }

        public BlockMetaBuilder<ParentBuilder,Reference> then(){
            return new BlockMetaBuilder<ParentBuilder,Reference>
                    (target)
                    .in(this.back());
        }

        public BlockMetaBuilder<ParentBuilder,Reference> then(String name){
            return new BlockMetaBuilder<ParentBuilder,Reference>
                    (target)
                    .in(this.back())
                    .name(name);
        }

        public ParentBuilder then(BlockMeta blockMeta){
            this.target = blockMeta;
            return this.back();
        }

    }

}
