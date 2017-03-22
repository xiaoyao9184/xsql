package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.predicate.PredicateBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * SearchConditionBuilder
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
        target.setUseNot(true);
        return this;
    }

    public PredicateBuilder<SearchConditionBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                (target::setPredicate)
                .in(this);
    }

    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        target::setSearchCondition))
                .in(this);
    }

    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndOrNotItem(){
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (initNew(SearchCondition.AndOrNotItem::new,
                        target::getAndOrList,
                        target::setAndOrList))
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $Predicate(Predicate predicate){
        target.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> $NotPredicate(Predicate predicate){
        target.setUseNot(true);
        target.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set searchCondition
     * @param searchCondition
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $SearchCondition(SearchCondition searchCondition){
        target.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * Quick set searchCondition
     * @param searchCondition
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $NotSearchCondition(SearchCondition searchCondition){
        target.setUseNot(true);
        target.setSearchCondition(searchCondition);
        return this;
    }


    /*
    Quick into
     */

    /**
     * Quick into SearchConditionBuilder
     * And set searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $SearchCondition(){
        return withSearchCondition();
    }

    /**
     * Quick into SearchConditionBuilder
     * And set searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $NotSearchCondition(){
        target.setUseNot(true);
        return withSearchCondition();
    }




    /*
    Quick set
     */

    /**
     * Quick set andOrNotItem.predicate
     * into AndOrNotItemBuilder and set predicate
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
     * Quick set andOrNotItem.predicate
     * into AndOrNotItemBuilder and set predicate
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
     * Quick set andOrNotItem.predicate
     * into AndOrNotItemBuilder and set predicate
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
     * Quick set andOrNotItem.predicate
     * into AndOrNotItemBuilder and set predicate
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


    /*
    Quick into
     */

    /**
     * TODO maybe just use $_And()
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_AndSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withSearchCondition(searchCondition)
                        .build(),
                target::getAndOrList,
                target::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_OrSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withSearchCondition(searchCondition)
                        .build(),
                target::getAndOrList,
                target::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_AndNotSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withNot(true)
                        .withSearchCondition(searchCondition)
                        .build(),
                target::getAndOrList,
                target::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }

    /**
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_OrNotSearchCondition() {
        SearchCondition searchCondition = new SearchCondition();
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withNot(true)
                        .withSearchCondition(searchCondition)
                        .build(),
                target::getAndOrList,
                target::setAndOrList);
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (searchCondition)
                .in(this);
    }




    /*
    Quick set
     */

    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $(Predicate predicate){
        target.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set predicate
     * @param predicate
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<ParentBuilder> $Not(Predicate predicate){
        target.setUseNot(true);
        target.setPredicate(predicate);
        return this;
    }

    /**
     * Quick into SearchConditionBuilder
     * And set searchCondition
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_(){
        return withSearchCondition();
    }

    /**
     * Quick into SearchConditionBuilder
     * And set searchCondition
     * @return
     */
    @Deprecated
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $_Not(){
        return withSearchCondition()
                .withNot();
    }


    /**
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' predicate
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' predicate
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' predicate
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' predicate
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
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
     * Quick into SearchConditionBuilder
     * And set AndOrNotItem' searchCondition
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
     * AndOrNotItemBuilder
     * All Quick method move to SearchConditionBuilder
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
            target.setUseAnd(true);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withOr() {
            target.setUseAnd(false);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withNot(boolean useNot) {
            target.setUseNot(useNot);
            return this;
        }

        public AndOrNotItemBuilder<ParentBuilder> withPredicate(Predicate predicate){
            target.setPredicate(predicate);
            return this;
        }

        public PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>> withPredicate(){
            return new PredicateBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (target::setPredicate)
                    .in(this);
        }

        public SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<AndOrNotItemBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            target::setSearchCondition))
                    .in(this);
        }

        public AndOrNotItemBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
            target.setSearchCondition(searchCondition);
            return this;
        }
    }

}
