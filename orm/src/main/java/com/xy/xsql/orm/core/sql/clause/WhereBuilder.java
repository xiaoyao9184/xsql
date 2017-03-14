package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.orm.core.CodeTreeBuilder;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;
import com.xy.xsql.orm.data.sql.element.predicate.Predicate;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class WhereBuilder<ParentBuilder>
        extends CodeTreeBuilder<WhereBuilder<ParentBuilder>,ParentBuilder,Where> {

    public WhereBuilder() {
        super(new Where());
    }

    public WhereBuilder(Where where) {
        super(where);
    }

    public SearchConditionBuilder<WhereBuilder<ParentBuilder>> withSearchCondition(){
        return new SearchConditionBuilder<WhereBuilder<ParentBuilder>>
                (set(SearchCondition::new,
                        tar::setSearchCondition))
                .in(this);
    }

    /**
     * Quick like SearchConditionBuilder
     * @param <ParentBuilder>
     */
    public static class QuickBuilder<ParentBuilder>
            extends CodeTreeBuilder<QuickBuilder<ParentBuilder>,ParentBuilder,Where> {

        private SearchCondition searchCondition;

        public QuickBuilder() {
            super(new Where());
            searchCondition =
                    initSet(SearchCondition::new,
                            tar::getSearchCondition,
                            tar::setSearchCondition);
        }

        public QuickBuilder(Where where) {
            super(where);
            searchCondition =
                    initSet(SearchCondition::new,
                            tar::getSearchCondition,
                            tar::setSearchCondition);
        }

        /**
         * Quick set in SearchConditionBuilder
         * @return
         */
        public QuickBuilder<ParentBuilder> withNot(){
            new SearchConditionBuilder<SearchCondition>
                    (searchCondition)
                    .withNot()
                    .build();
            return this;
        }

        /**
         * Quick set in SearchConditionBuilder
         * @return
         */
        public QuickBuilder<ParentBuilder> withPredicate(Predicate predicate){
            new SearchConditionBuilder<SearchCondition>
                    (searchCondition)
                    .withPredicate(predicate)
                    .build();
            return this;
        }

        /**
         * Quick in SearchConditionBuilder
         * @return
         */
        public SearchConditionBuilder<QuickBuilder<ParentBuilder>> withSearchCondition(){
            return new SearchConditionBuilder<QuickBuilder<ParentBuilder>>
                    (set(SearchCondition::new,
                            searchCondition::setSearchCondition))
                    .in(this);
        }

        /**
         * Quick in SearchConditionBuilder
         * @return
         */
        @Deprecated
        public SearchConditionBuilder.AndOrNotItemBuilder<QuickBuilder<ParentBuilder>> withAndOrNotItem(){
            return new SearchConditionBuilder.AndOrNotItemBuilder<QuickBuilder<ParentBuilder>>
                    (initNew(SearchCondition.AndOrNotItem::new,
                            searchCondition::getAndOrList,
                            searchCondition::setAndOrList))
                    .in(this);
        }


        /**
         * Quick set AndOrNotItem' predicate
         * @param predicate
         * @return
         */
        public QuickBuilder<ParentBuilder> withAndPredicate(Predicate predicate) {
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withAnd()
                            .withPredicate(predicate)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return this;
        }

        /**
         * Quick set AndOrNotItem' predicate
         * @param predicate
         * @return
         */
        public QuickBuilder<ParentBuilder> withOrPredicate(Predicate predicate) {
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withOr()
                            .withPredicate(predicate)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return this;
        }

        /**
         * Quick set AndOrNotItem' predicate
         * @param predicate
         * @return
         */
        public QuickBuilder<ParentBuilder> withAndNotPredicate(Predicate predicate) {
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withAnd()
                            .withNot(true)
                            .withPredicate(predicate)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return this;
        }

        /**
         * Quick set AndOrNotItem' predicate
         * @param predicate
         * @return
         */
        public QuickBuilder<ParentBuilder> withOrNotPredicate(Predicate predicate) {
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withOr()
                            .withNot(true)
                            .withPredicate(predicate)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return this;
        }


        /**
         * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
         * @return
         */
        public SearchConditionBuilder<QuickBuilder<ParentBuilder>> withAndSearchCondition() {
            SearchCondition searchCondition = new SearchCondition();
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withAnd()
                            .withSearchCondition(searchCondition)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return new SearchConditionBuilder<QuickBuilder<ParentBuilder>>
                    (searchCondition)
                    .in(this);
        }

        /**
         * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
         * @return
         */
        public SearchConditionBuilder<QuickBuilder<ParentBuilder>> withOrSearchCondition() {
            SearchCondition searchCondition = new SearchCondition();
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withOr()
                            .withSearchCondition(searchCondition)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return new SearchConditionBuilder<QuickBuilder<ParentBuilder>>
                    (searchCondition)
                    .in(this);
        }

        /**
         * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
         * @return
         */
        public SearchConditionBuilder<QuickBuilder<ParentBuilder>> withAndNotSearchCondition() {
            SearchCondition searchCondition = new SearchCondition();
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withAnd()
                            .withNot(true)
                            .withSearchCondition(searchCondition)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return new SearchConditionBuilder<QuickBuilder<ParentBuilder>>
                    (searchCondition)
                    .in(this);
        }

        /**
         * Quick in SearchConditionBuilder set AndOrNotItem' searchCondition
         * @return
         */
        public SearchConditionBuilder<QuickBuilder<ParentBuilder>> withOrNotSearchCondition() {
            SearchCondition searchCondition = new SearchCondition();
            initAdd(new SearchConditionBuilder.AndOrNotItemBuilder<Void>()
                            .withOr()
                            .withNot(true)
                            .withSearchCondition(searchCondition)
                            .build(),
                    searchCondition::getAndOrList,
                    searchCondition::setAndOrList);
            return new SearchConditionBuilder<QuickBuilder<ParentBuilder>>
                    (searchCondition)
                    .in(this);
        }
    }
}
