package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.core.clause.*;
import com.xy.xsql.tsql.core.clause.select.*;
import com.xy.xsql.tsql.model.clause.*;
import com.xy.xsql.tsql.model.clause.select.*;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.element.TableName;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.operator.Set;
import com.xy.xsql.tsql.model.statement.dml.Select;

import java.util.Arrays;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilder extends CodeBuilder<Select> {

    public SelectBuilder(){
        super(new Select());
    }


    public WithBuilder<SelectBuilder> withWith(){
        return new WithBuilder<SelectBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

//    public QuerySpecificationBuilder<SelectBuilder> withQuery() {
//        com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification querySpecification = new com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification();
//        target.setQueryExpression(querySpecification);
//        return new QuerySpecificationBuilder<SelectBuilder>
//                (querySpecification)
//                .in(this);
//    }


    public SelectBuilder withQuery(Select.QueryExpression queryExpression) {
        target.setQueryExpression(queryExpression);
        return this;
    }

    public SelectBuilder withQuery(Select.QuerySpecification querySpecification){
        return new QueryExpressionBuilder<SelectBuilder>
                (initSet(Select.QueryExpression::new,
                        target::getQueryExpression,
                        target::setQueryExpression))
                .in(this)
                .withQuerySpecification(querySpecification)
                .and();
    }

    public QueryExpressionBuilder<SelectBuilder> withQuery(){
        return new QueryExpressionBuilder<SelectBuilder>
                (initSet(Select.QueryExpression::new,
                        target::getQueryExpression,
                        target::setQueryExpression))
                .in(this);
    }

    public OrderByBuilder<SelectBuilder> withOrderBy() {
        return new OrderByBuilder<SelectBuilder>
                (initSet(OrderBy::new,
                        target::getOrderBy,
                        target::setOrderBy))
                .in(this);
    }

    public SelectBuilder withOrderBy(OrderBy orderBy) {
        target.setOrderBy(orderBy);
        return this;
    }

    public ForBuilder<SelectBuilder> withFor() {
        return new ForBuilder<SelectBuilder>
                (initSet(For::new,
                        target::getForClause,
                        target::setForClause))
                .in(this);
    }

    public OptionBuilder<SelectBuilder> withOption() {
        return new OptionBuilder<SelectBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }




    /*
    Quick into
     */

    /**
     * Quick into SelectBuilder
     * @return
     */
    public static SelectBuilder SELECT(){
        return new SelectBuilder();
    }


//    /**
//     * Quick into
//     * @return
//     */
//    public QuerySpecificationBuilder<SelectBuilder> $() {
//        return withQuery();
//    }

    /**
     * Quick into QueryExpressionBuilder
     * And set queryExpression
     * @return
     */
    public QueryExpressionBuilder<SelectBuilder> $() {
        return withQuery();
    }

    /**
     * Quick into QuerySpecificationBuilder
     * into QueryExpressionBuilder
     * And set queryExpression
     * into QuerySpecificationBuilder
     * And set queryExpression.querySpecification
     * @return
     */
    public QuerySpecificationBuilder<SelectBuilder> $Select() {
        Select.QuerySpecification query = withQuery()
                .withQuerySpecification()
                .build();

        return new QuerySpecificationBuilder<SelectBuilder>
                (query)
                .in(this);
    }

    /**
     * Quick into OrderByBuilder
     * And set orderBy
     * @return
     */
    public OrderByBuilder<SelectBuilder> $OrderBy() {
        return withOrderBy();
    }

    /**
     * Quick into ForBuilder
     * And set for
     * @return
     */
    public ForBuilder<SelectBuilder> $For() {
        return withFor();
    }

    /**
     * Quick into OptionBuilder
     * And set option
     * @return
     */
    public OptionBuilder<SelectBuilder> $Option() {
        return withOption();
    }



    /**
     * QueryExpressionBuilder
     * TODO maybe All Quick method move to SelectBuilder
     * @param <ParentBuilder>
     */
    public static class QueryExpressionBuilder<ParentBuilder>
            extends CodeTreeBuilder<QueryExpressionBuilder<ParentBuilder>,ParentBuilder, Select.QueryExpression> {

        public QueryExpressionBuilder() {
            super(new com.xy.xsql.tsql.model.statement.dml.Select.QueryExpression());
        }

        public QueryExpressionBuilder(Select.QueryExpression queryExpression) {
            super(queryExpression);
        }


        public QueryExpressionBuilder<ParentBuilder> withQuerySpecification(Select.QuerySpecification querySpecification) {
            target.setQuerySpecification(querySpecification);
            return this;
        }

        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> withQuerySpecification(){
            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (set(Select.QuerySpecification::new,
                            target::setQuerySpecification))
                    .in(this);
        }

        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> withQueryExpression(){
            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (set(Select.QueryExpression::new,
                            target::setQueryExpression))
                    .in(this);
        }

        public UnitItemBuilder<QueryExpressionBuilder<ParentBuilder>> withUnitItem(){
            return new UnitItemBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (initNew(Select.UnionItem::new,
                            target::getUnionItems,
                            target::setUnionItems))
                    .in(this);
        }



        /*
        Quick set
         */

        /**
         * Quick into QuerySpecificationBuilder
         * set querySpecification
         * @return
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $Select(){
            return withQuerySpecification();
        }

        /**
         * Quick into QueryExpressionBuilder
         * set queryExpression
         * @return
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $_(){
            return withQueryExpression();
        }

        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $(){
            return withQueryExpression();
        }

        /**
         * Quick into QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $Union_Select(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.UNION)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick into QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $Union_All_Select(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.UNION_ALL)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick into QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $Except_Select(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.EXCEPT)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick into QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $Intersect_Select(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.INTERSECT)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick into QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $Union_(){
            Select.QueryExpression group = withUnitItem()
                    .withOperatorSet(Set.UNION)
                    .withQueryExpression()
                    .build();

            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (group)
                    .in(this);
        }

        /**
         * Quick into QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $Union_All_(){
            Select.QueryExpression group = withUnitItem()
                    .withOperatorSet(Set.UNION_ALL)
                    .withQueryExpression()
                    .build();

            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (group)
                    .in(this);
        }

        /**
         * Quick into QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $Except_(){
            Select.QueryExpression group = withUnitItem()
                    .withOperatorSet(Set.EXCEPT)
                    .withQueryExpression()
                    .build();

            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (group)
                    .in(this);
        }

        /**
         * Quick into QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $Intersect_(){
            Select.QueryExpression group = withUnitItem()
                    .withOperatorSet(Set.INTERSECT)
                    .withQueryExpression()
                    .build();

            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (group)
                    .in(this);
        }
    }


    /**
     * UnitItemBuilder
     * All Quick method move to QueryExpressionBuilder
     * @param <ParentBuilder>
     */
    public static class UnitItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<UnitItemBuilder<ParentBuilder>, ParentBuilder, Select.UnionItem> {

        public UnitItemBuilder(com.xy.xsql.tsql.model.statement.dml.Select.UnionItem unionItem) {
            super(unionItem);
        }


        public UnitItemBuilder<ParentBuilder> withOperatorSet(Set set) {
            target.setOperatorSet(set);
            return this;
        }

        public QuerySpecificationBuilder<UnitItemBuilder<ParentBuilder>> withQuerySpecification(){
            return new QuerySpecificationBuilder<UnitItemBuilder<ParentBuilder>>
                    (set(Select.QuerySpecification::new,
                            target::setQuerySpecification))
                    .in(this);
        }

        public QueryExpressionBuilder<UnitItemBuilder<ParentBuilder>> withQueryExpression(){
            return new QueryExpressionBuilder<UnitItemBuilder<ParentBuilder>>
                    (set(Select.QueryExpression::new,
                            target::setQueryExpression))
                    .in(this);
        }

    }


    /**
     * QuerySpecificationBuilder
     * Similar to [clause]SelectBuilder
     * @see com.xy.xsql.tsql.core.clause.select.SelectBuilder
     * @param <ParentBuilder>
     */
    public static class QuerySpecificationBuilder<ParentBuilder>
            extends CodeTreeBuilder<QuerySpecificationBuilder<ParentBuilder>, ParentBuilder, Select.QuerySpecification> {

        public QuerySpecificationBuilder() {
            super(new com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification());
        }

        public QuerySpecificationBuilder(com.xy.xsql.tsql.model.statement.dml.Select.QuerySpecification tar) {
            super(tar);
        }


        public QuerySpecificationBuilder<ParentBuilder> withAll() {
            target.setUseAll(true);
            return this;
        }

        public QuerySpecificationBuilder<ParentBuilder> withDistinct() {
            target.setUseDistinct(true);
            return this;
        }

        public TopBuilder<QuerySpecificationBuilder<ParentBuilder>> withTop(){
            Top top = new Top();
            target.setTop(top);
            return new TopBuilder<QuerySpecificationBuilder<ParentBuilder>>(top)
                    .in(this);
        }

        public com.xy.xsql.tsql.core.clause.select.SelectBuilder.SelectItemBuilder<QuerySpecificationBuilder<ParentBuilder>> withSelectItem(){
            com.xy.xsql.tsql.model.clause.select.Select.SelectItem item = new com.xy.xsql.tsql.model.clause.select.Select.SelectItem();
            initList(target::getSelectList,
                    target::setSelectList);
            target.getSelectList().add(item);
            return new com.xy.xsql.tsql.core.clause.select.SelectBuilder.SelectItemBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (item)
                    .in(this);
        }

        public QuerySpecificationBuilder<ParentBuilder> withSelectItem(com.xy.xsql.tsql.model.clause.select.Select.SelectItem... selectItems) {
            initAdd(Arrays.asList(selectItems),
                    target::getSelectList,
                    target::setSelectList);
            return this;
        }

        public QuerySpecificationBuilder<ParentBuilder> withInto(String newTable) {
            return new IntoBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (initSet(Into::new,
                            target::getInto,
                            target::setInto))
                    .in(this)
                    .withNewTable(new TableName(newTable))
                    .out();
        }

        public FromBuilder<QuerySpecificationBuilder<ParentBuilder>> withFrom() {
            From from = new From();
            target.setFrom(from);
            return new FromBuilder<QuerySpecificationBuilder<ParentBuilder>>(from)
                    .in(this);
        }

        public QuerySpecificationBuilder<ParentBuilder> withWhere(Where where) {
            target.setWhere(where);
            return this;
        }

        public WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> withWhere() {
            Where where = new Where();
            target.setWhere(where);
            WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> whereBuilder = new WhereBuilder<>(where);
            return whereBuilder.in(this);
        }

        public GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> withGroupBy() {
            GroupBy groupBy = new GroupBy();
            target.setGroupBy(groupBy);
            GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> groupByBuilder = new GroupByBuilder<>(groupBy);
            return groupByBuilder.in(this);
        }

        public HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> withHaving() {
            Having having = new Having();
            target.setHaving(having);
            HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> havingBuilder = new HavingBuilder<>(having);
            return havingBuilder.in(this);
        }



        /*
        Quick set/into
        Same as com.xy.xsql.tsql.core.clause.select.SelectBuilder
         */

        /**
         * Quick set useAll
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $All() {
            return withAll();
        }

        /**
         * Quick set useDistinct
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $Distinct() {
            return withDistinct();
        }

        /**
         * Quick into TopBuilder
         * And set top
         * @return
         */
        public TopBuilder<QuerySpecificationBuilder<ParentBuilder>> $Top(){
            return withTop();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $() {
            return withSelectItem()
                    .withAll()
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param tableName
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $(TableName tableName) {
            return withSelectItem()
                    .withTableAll(tableName)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param columnName
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $(ColumnName columnName) {
            return withSelectItem()
                    .withColumnName(columnName)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param columnName
         * @param columnAlias
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $(ColumnName columnName, String columnAlias) {
            return withSelectItem()
                    .withColumnName(columnName)
                    .withAs()
                    .withColumnAlias(columnAlias)
                    .and();
        }

        /**
         * Quick set selectList
         * @param columnName
         * @param expression
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $(ColumnName columnName, Expression expression) {
            return withSelectItem()
                    .withColumnName(columnName)
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param expression
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $(Expression expression) {
            return withSelectItem()
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param expression
         * @param columnAlias
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $(Expression expression, String columnAlias) {
            return withSelectItem()
                    .withExpression(expression)
                    .withAs()
                    .withColumnAlias(columnAlias)
                    .and();
        }


        /**
         * Quick set newTable
         * @param newTable
         * @return
         */
        public QuerySpecificationBuilder<ParentBuilder> $Into(String newTable) {
            return withInto(newTable);
        }

        /**
         * Quick into FromBuilder
         * And set from
         * @return
         */
        public FromBuilder<QuerySpecificationBuilder<ParentBuilder>> $From() {
            return withFrom();
        }

        /**
         * Quick into WhereBuilder
         * And set where
         * @return
         */
        public WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> $Where() {
            return withWhere();
        }

        /**
         * Quick into GroupByBuilder
         * And set groupBy
         * @return
         */
        public GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> $GroupBy() {
            return withGroupBy();
        }

        /**
         * Quick into HavingBuilder
         * And set having
         * @return
         */
        public HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> $Having() {
            return withHaving();
        }

    }

}
