package com.xy.xsql.block.core;

import com.xy.xsql.block.model.ItemReferenceBlock;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.datatype.TableTypeDefinition;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.xy.xsql.core.ListBuilder.*;

/**
 * Created by xiaoyao9184 on 2017/6/5.
 * @param <ParentBuilder>
 * @param <Reference> Refer to create blocks
 */
public class ReferenceBlockBuilder<ParentBuilder,Reference>
        extends CodeTreeBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>, ParentBuilder, ReferenceBlock> {

    public ReferenceBlockBuilder() {
        super(new ReferenceBlock());
    }

    public ReferenceBlockBuilder(ReferenceBlock block) {
        super(block);
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> name(String name){
        target.setName(name);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> overall(String name) {
        target.setName(name);
        target.setOverall(true);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> description(String description){
        target.setDescription(description);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> data(Function<Reference,?> getter){
        target.setDataGetter(getter);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> data(Object data){
        target.setData(data);
        return this;
    }


    public ReferenceBlockBuilder<ParentBuilder,Reference> keyword(Enum keywords) {
        target.setKeyword(true);
        target.setData(keywords.toString());
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

    public ReferenceBlockBuilder<ParentBuilder,Reference> required(){
        target.setRequired(true);
        target.addVerifier(notNull);
        return this;
    }

    /**
     * Can be optional
     * @return
     */
    public ReferenceBlockBuilder<ParentBuilder,Reference> optional(){
        target.setOptional(true);
        return this;
    }

    /**
     *
     * @param predicate
     * @return
     */
    public ReferenceBlockBuilder<ParentBuilder,Reference> optional(Predicate<Reference> predicate) {
        target.setOptional(true);
        target.setOptionalFilter(predicate);
        return this;
    }


    public ReferenceBlockBuilder<ParentBuilder,Reference> filter(Predicate<Reference> predicate) {
        target.setOptionalFilter(predicate);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> oneMore() {
        target.setRequired(true);
        target.setMore(true);
        target.addVerifier(notNull);
        target.addVerifier(oneMore);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> more() {
        target.setMore(true);
        return this;
    }


    public ReferenceBlockBuilder<ParentBuilder, Reference> ref(Class<?> refClass) {
        target.setRefClass(refClass);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> type(Class<?> type) {
        target.setType(type);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> is(Class<?> type) {
        target.setType(type);
        return this;
    }

    @Deprecated
    public ReferenceBlockBuilder<ParentBuilder,Reference> group() {
        target.setGroup(true);
        return this;
    }


    public ReferenceBlockBuilder<ParentBuilder,Reference> check(Predicate<Reference> filter) {
        target.addVerifier(notNull);
        return this;
    }


    public ReferenceBlockBuilder<ParentBuilder,Reference> list() {
        target.setList(true);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> list(String name) {
        target.setList(true);
        return sub(name)
                .and();
    }

    public ReferenceBlockBuilder<ParentBuilder, Reference> list(ReferenceBlock meta) {
        target.setList(true);
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> repeat() {
        target.setRepeat(true);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> repeat(String name) {
        target.setRepeat(true);
        return sub(name)
                .and();
    }

    public ReferenceBlockBuilder<ParentBuilder, Reference> repeat(ReferenceBlock meta) {
        target.setRepeat(true);
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference> sub() {
        return new ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(ReferenceBlock::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(null);
    }

    public ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference> sub(String name) {
        return new ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(ReferenceBlock::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(name);
    }

    public ReferenceBlockBuilder<ParentBuilder, Reference> sub(ReferenceBlock meta) {
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> sub_keyword(Enum keywords) {
        return new ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(ReferenceBlock::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(null)
                .keyword(keywords)
                .and();
    }

    public ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference> oneOf() {
        target.setExclusive(true);
        return new ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(ReferenceBlock::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(null);
    }

    public ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference> oneOf(String name) {
        target.setExclusive(true);
        return new ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(ReferenceBlock::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(name);
    }

    public ReferenceBlockBuilder<ParentBuilder, Reference> oneOf(ReferenceBlock meta) {
        target.setExclusive(true);
        initAdd(meta,
                target::getSub,
                target::setSub);
        return this;
    }

    @Deprecated
    public ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference> item(String name) {
        return new ReferenceBlockBuilder<ReferenceBlockBuilder<ParentBuilder,Reference>,Reference>
                (initNew2(ItemReferenceBlock::new,
                        target::getSub,
                        target::setSub))
                .in(this)
                .name(name);
    }



    public ReferenceBlockBuilder<ParentBuilder,Reference> subTakeLine() {
        target.setEachSubTakeLine(true);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> headFootTakeLine() {
        target.setHeadFootTakeLine(true);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> startNewline() {
        target.setStartNewLine(true);
        return this;
    }

    public ReferenceBlockBuilder<ParentBuilder,Reference> endNewline() {
        target.setEndNewLine(true);
        return this;
    }

}
