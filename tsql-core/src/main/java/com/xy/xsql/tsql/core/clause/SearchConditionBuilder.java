package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.predicate.PredicateBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SearchConditionBuilder<ParentBuilder>
        extends CodeTreeBuilder<SearchConditionBuilder<ParentBuilder>,ParentBuilder,SearchCondition> {

    public SearchConditionBuilder() {
        super(new SearchCondition());
    }

    public SearchConditionBuilder(SearchCondition searchCondition) {
        super(searchCondition);
    }

    public SearchConditionBuilder<ParentBuilder> withNot(){
        tar.setUseNot(true);
        return this;
    }

    public PredicateBuilder<SearchConditionBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                (tar::setPredicate)
                .in(this);
    }

    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        tar::setSearchCondition))
                .in(this);
    }

    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndOrNotItem(){
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (initNew(SearchCondition.AndOrNotItem::new,
                        tar::getAndOrList,
                        tar::setAndOrList))
                .in(this);
    }


    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $Predicate(Predicate predicate){
        tar.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set searchCondition
     * @param searchCondition
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $SearchCondition(SearchCondition searchCondition){
        tar.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $NotPredicate(Predicate predicate){
        tar.setUseNot(true);
        tar.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set searchCondition
     * @param searchCondition
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $NotSearchCondition(SearchCondition searchCondition){
        tar.setUseNot(true);
        tar.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $_AndPredicate(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $_OrPredicate(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $_AndNotPredicate(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $_OrNotPredicate(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }


    /**
     * TODO maybe just use $_And()
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_AndSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_OrSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_AndNotSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withNot(true)
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_OrNotSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withNot(true)
                        .withSearchCondition(searchCondition)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }












    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $(Predicate predicate){
        tar.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $Not(Predicate predicate){
        tar.setUseNot(true);
        tar.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_(){
        return withSearchCondition();
    }

    /**
     * Quick set searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_Not(){
        return withSearchCondition()
                .withNot();
    }


    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $_And(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $_Or(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $_AndNot(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick inout set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $_OrNot(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick in AndOrNotItem' SearchCondition'
     * @see #and()
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>> $__And() {
        return withAndOrNotItem()
                .withAnd()
                .withSearchCondition();
    }

    /**
     * Quick in AndOrNotItem' SearchCondition'
     * @see #and()
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>> $__Or() {
        return withAndOrNotItem()
                .withOr()
                .withSearchCondition();
    }

    /**
     * Quick in AndOrNotItem' SearchCondition'
     * @see #and()
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>> $__AndNot() {
        return withAndOrNotItem()
                .withAnd()
                .withNot(true)
                .withSearchCondition();
    }

    /**
     * Quick in AndOrNotItem' SearchCondition'
     * @see #and()
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>> $__OrNot() {
        return withAndOrNotItem()
                .withOr()
                .withNot(true)
                .withSearchCondition();
    }












    /**
     * { AND | OR } [ NOT ] { <predicate> | ( <search_condition> ) }
     * @param <ParentBuilder>
     */
    public static class AndOrNotItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<AndOrNotItemBuilder<ParentBuilder>,ParentBuilder,SearchCondition.AndOrNotItem> {

        public AndOrNotItemBuilder() {
            super(new SearchCondition.AndOrNotItem());
        }

        public AndOrNotItemBuilder(SearchCondition.AndOrNotItem andOr) {
            super(andOr);
        }


        public AndOrNotItemBuilder<ParentBuilder> withAnd() {
            tar.setUseAnd(true);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withOr() {
            tar.setUseAnd(false);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withNot(boolean useNot) {
            tar.setUseNot(useNot);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withPredicate(Predicate predicate){
            tar.setPredicate(predicate);
            return this;
        }

        public PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>> withPredicate(){
            return new PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (tar::setPredicate)
                    .in(this);
        }

        public SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            tar::setSearchCondition))
                    .in(this);
        }

        public AndOrNotItemBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
            tar.setSearchCondition(searchCondition);
            return this;
        }
    }

}
