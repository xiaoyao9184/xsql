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
@SuppressWarnings("WeakerAccess")
public class SearchConditionBuilder<ParentBuilder>
        extends CodeTreeBuilder<SearchConditionBuilder<ParentBuilder>,ParentBuilder,SearchCondition> {

    public SearchConditionBuilder() {
        super(new SearchCondition());
    }

    public SearchConditionBuilder(SearchCondition searchCondition) {
        super(searchCondition);
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withNot(){
        target.setUseNot(true);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withPredicate(Predicate predicate){
        target.setPredicate(predicate);
        return this;
    }

    /**
     * set
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
        target.setSearchCondition(searchCondition);
        return this;
    }

    /**
     * in
     * @return PredicateBuilder
     */
    public PredicateBuilder<SearchConditionBuilder<ParentBuilder>> withPredicate(){
        return new PredicateBuilder<SearchConditionBuilder<ParentBuilder>>
                (target::setPredicate)
                .in(this);
    }

    /**
     * in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        target::setSearchCondition))
                .in(this);
    }

    /**
     * in
     * @return AndOrNotItemBuilder
     */
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndOrNotItem(){
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (initNew(SearchCondition.AndOrNotItem::new,
                        target::getAndOrList,
                        target::setAndOrList))
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $(Predicate predicate){
        return withPredicate(predicate);
    }

    /**
     * Quick set
     * @param predicate predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $Not(Predicate predicate){
        return withNot()
                .withPredicate(predicate);
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $(){
        return withSearchCondition();
    }

    /**
     * Quick in
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $Not(){
        return withNot()
                .withSearchCondition();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $And(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $Or(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $And_Not(Predicate predicate) {
        return withAndOrNotItem()
                .withAnd()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick set
     * into AndOrNotItemBuilder and set predicate
     * @param predicate andOrNotItem.predicate
     * @return THIS
     */
    public SearchConditionBuilder<ParentBuilder> $Or_Not(Predicate predicate) {
        return withAndOrNotItem()
                .withOr()
                .withNot(true)
                .withPredicate(predicate)
                .and();
    }

    /**
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $And() {
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
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $Or() {
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
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $And_Not() {
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
     * Quick in
     * And set AndOrNotItem' searchCondition
     * @return SearchConditionBuilder
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> $Or_Not() {
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


    /**
     * AndOrNotItemBuilder
     * All Quick method move to SearchConditionBuilder
     * @param <ParentBuilder>
     */
    @SuppressWarnings({"WeakerAccess", "SameParameterValue"})
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
