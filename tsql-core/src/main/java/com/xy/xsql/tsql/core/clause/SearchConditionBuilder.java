package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.core.CodeTreeBuilder;
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
                ()
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
    public SearchConditionBuilder<ParentBuilder> withPredicate(Predicate predicate){
        tar.setPredicate(predicate);
        return this;
    }

    /**
     * Quick set searchCondition
     * @param searchCondition
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withSearchCondition(SearchCondition searchCondition){
        tar.setSearchCondition(searchCondition);
        return this;
    }


    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAnd() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withAnd()
                .in(this);
    }

    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to or() , and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withOr() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withOr()
                .in(this);
    }

    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to andNot() , and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withAndNot() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withAnd()
                .withNot(true)
                .in(this);
    }

    /**
     * Quick in AndOrNotItemBuilder
     * @deprecated cant simple method name to orNot() , and() conflict
     * @see #and()
     * @return
     */
    @Deprecated
    public AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>> withOrNot() {
        SearchCondition.AndOrNotItem andOrNotItem = new SearchCondition.AndOrNotItem();
        initAdd(andOrNotItem,
                tar::getAndOrList,
                tar::setAndOrList);
        return new AndOrNotItemBuilder<SearchConditionBuilder<ParentBuilder>>
                (andOrNotItem)
                .withOr()
                .withNot(true)
                .in(this);
    }





    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withAnd(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withOr(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withAndPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withOrPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withAndNotPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withAnd()
                        .withNot(true)
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }

    /**
     * Quick set AndOrNotItem' predicate
     * @param predicate
     * @return
     */
    public SearchConditionBuilder<ParentBuilder> withOrNotPredicate(Predicate predicate) {
        initAdd(new AndOrNotItemBuilder<Void>()
                        .withOr()
                        .withNot(true)
                        .withPredicate(predicate)
                        .build(),
                tar::getAndOrList,
                tar::setAndOrList);
        return this;
    }


    /**
     * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
     * @return
     */
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withAndSearchCondition() {
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withOrSearchCondition() {
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withAndNotSearchCondition() {
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
    public SearchConditionBuilder<SearchConditionBuilder<ParentBuilder>> withOrNotSearchCondition() {
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
                    ()
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
