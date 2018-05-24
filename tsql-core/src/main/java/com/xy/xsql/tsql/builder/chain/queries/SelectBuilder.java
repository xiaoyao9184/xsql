package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.ForBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.GroupByBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.HavingBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.OrderByBuilder;
import com.xy.xsql.tsql.builder.chain.queries.select.*;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.datatypes.table.TableName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.elements.operators.Set;
import com.xy.xsql.tsql.model.queries.*;
import com.xy.xsql.tsql.model.queries.select.*;
import com.xy.xsql.tsql.model.queries.Select;

import java.util.Arrays;
import java.util.List;

import static com.xy.xsql.core.FiledBuilder.initSet;
import static com.xy.xsql.core.FiledBuilder.set;
import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * SelectBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"WeakerAccess", "unused", "UnusedReturnValue"})
public class SelectBuilder extends CodeBuilder<Select> {

    public SelectBuilder(){
        super(new Select());
    }

    /**
     * set
     * @param with With
     * @return THIS
     */
    public SelectBuilder withWith(With with){
        this.target.setWith(with);
        return this;
    }

    /**
     * in
     * @return WithBuilder
     */
    public WithBuilder<SelectBuilder> withWith(){
        return new WithBuilder<SelectBuilder>
                (initSet(With::new,
                        target::getWith,
                        target::setWith))
                .in(this);
    }

    /**
     * set
     * @param queryExpression QueryExpression
     * @return THIS
     */
    public SelectBuilder withQuery(Select.QueryExpression queryExpression) {
        target.setQueryExpression(queryExpression);
        return this;
    }

    /**
     * set
     * @param querySpecification QuerySpecification
     * @return THIS
     */
    public SelectBuilder withQuery(Select.QuerySpecification querySpecification){
        return new QueryExpressionBuilder<SelectBuilder>
                (initSet(Select.QueryExpression::new,
                        target::getQueryExpression,
                        target::setQueryExpression))
                .in(this)
                .withQuerySpecification(querySpecification)
                .and();
    }

//    public QuerySpecificationBuilder<SelectBuilder> withQuery() {
//        com.xy.xsql.tsql.model.queries.Select.QuerySpecification querySpecification = new com.xy.xsql.tsql.model.queries.Select.QuerySpecification();
//        target.setQueryExpression(querySpecification);
//        return new QuerySpecificationBuilder<SelectBuilder>
//                (querySpecification)
//                .in(this);
//    }

    /**
     * in
     * @return QueryExpressionBuilder
     */
    public QueryExpressionBuilder<SelectBuilder> withQuery(){
        return new QueryExpressionBuilder<SelectBuilder>
                (initSet(Select.QueryExpression::new,
                        target::getQueryExpression,
                        target::setQueryExpression))
                .in(this);
    }

    /**
     * set
     * @param orderBy OrderBy
     * @return THIS
     */
    public SelectBuilder withOrderBy(OrderBy orderBy) {
        target.setOrderBy(orderBy);
        return this;
    }

    /**
     * in
     * @return OrderByBuilder
     */
    public OrderByBuilder<SelectBuilder> withOrderBy() {
        return new OrderByBuilder<SelectBuilder>
                (initSet(OrderBy::new,
                        target::getOrderBy,
                        target::setOrderBy))
                .in(this);
    }

    /**
     * set
     * @param for_ For
     * @return THIS
     */
    public SelectBuilder withFor(For for_) {
        this.target.setForClause(for_);
        return this;
    }

    /**
     * in
     * @return ForBuilder
     */
    public ForBuilder<SelectBuilder> withFor() {
        return new ForBuilder<SelectBuilder>
                (initSet(For::new,
                        target::getForClause,
                        target::setForClause))
                .in(this);
    }

    /**
     * set
     * @param option Option
     * @return THIS
     */
    public SelectBuilder withOption(Option option) {
        this.target.setOption(option);
        return this;
    }

    /**
     * in
     * @return OptionBuilder
     */
    public OptionBuilder<SelectBuilder> withOption() {
        return new OptionBuilder<SelectBuilder>
                (initSet(Option::new,
                        target::getOption,
                        target::setOption))
                .in(this);
    }




    /*
    Quick
     */

    /**
     * Quick in QueryExpressionBuilder
     * And set queryExpression
     * @return QueryExpressionBuilder
     */
    public QueryExpressionBuilder<SelectBuilder> $() {
        return withQuery();
    }

    /**
     * Quick in QuerySpecificationBuilder
     * into QueryExpressionBuilder
     * And set queryExpression
     * into QuerySpecificationBuilder
     * And set queryExpression.querySpecification
     * @return QuerySpecificationBuilder
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
     * Quick in OrderByBuilder
     * And set orderBy
     * @return OrderByBuilder
     */
    public OrderByBuilder<SelectBuilder> $OrderBy() {
        return withOrderBy();
    }

    /**
     * Quick in ForBuilder
     * And set for
     * @return ForBuilder
     */
    public ForBuilder<SelectBuilder> $For() {
        return withFor();
    }

    /**
     * Quick in OptionBuilder
     * And set option
     * @return OptionBuilder
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
            super(new Select.QueryExpression());
        }

        public QueryExpressionBuilder(Select.QueryExpression queryExpression) {
            super(queryExpression);
        }

        /**
         * set
         * @param querySpecification QuerySpecification
         * @return THIS
         */
        public QueryExpressionBuilder<ParentBuilder> withQuerySpecification(Select.QuerySpecification querySpecification) {
            target.setQuerySpecification(querySpecification);
            return this;
        }

        /**
         * in
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> withQuerySpecification(){
            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (set(Select.QuerySpecification::new,
                            target::setQuerySpecification))
                    .in(this);
        }

        /**
         * in
         * @return QueryExpressionBuilder
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> withQueryExpression(){
            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (set(Select.QueryExpression::new,
                            target::setQueryExpression))
                    .in(this);
        }

        /**
         * in
         * @return UnitItemBuilder
         */
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
         * Quick in QuerySpecificationBuilder
         * set querySpecification
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $Select(){
            return withQuerySpecification();
        }

        /**
         * Quick in QueryExpressionBuilder
         * set queryExpression
         * @return QueryExpressionBuilder
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $_(){
            return withQueryExpression();
        }

        /**
         * Quick in QueryExpressionBuilder
         * set queryExpression
         * @return QueryExpressionBuilder
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $(){
            return withQueryExpression();
        }

        /**
         * Quick in QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $UnionSelect(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.UNION)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick in QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $UnionAllSelect(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.UNION_ALL)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick in QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $ExceptSelect(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.EXCEPT)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick in QuerySpecificationBuilder
         * And set UnitItem' querySpecification
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>> $IntersectSelect(){
            Select.QuerySpecification query = withUnitItem()
                    .withOperatorSet(Set.INTERSECT)
                    .withQuerySpecification()
                    .build();

            return new QuerySpecificationBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (query)
                    .in(this);
        }

        /**
         * Quick in QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return QueryExpressionBuilder
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
         * Quick in QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return QueryExpressionBuilder
         */
        public QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>> $UnionAll_(){
            Select.QueryExpression group = withUnitItem()
                    .withOperatorSet(Set.UNION_ALL)
                    .withQueryExpression()
                    .build();

            return new QueryExpressionBuilder<QueryExpressionBuilder<ParentBuilder>>
                    (group)
                    .in(this);
        }

        /**
         * Quick in QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return QueryExpressionBuilder
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
         * Quick in QueryExpressionBuilder
         * And set UnitItem' queryExpression
         * @return QueryExpressionBuilder
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

        public UnitItemBuilder(Select.UnionItem unionItem) {
            super(unionItem);
        }

        /**
         * set
         * @param set Set
         * @return THIS
         */
        public UnitItemBuilder<ParentBuilder> withOperatorSet(Set set) {
            target.setOperatorSet(set);
            return this;
        }

        /**
         * set
         * @param querySpecification QuerySpecification
         * @return THIS
         */
        public UnitItemBuilder<ParentBuilder> withQuerySpecification(Select.QuerySpecification querySpecification){
            this.target.setQuerySpecification(querySpecification);
            return this;
        }

        /**
         * in
         * @return QuerySpecificationBuilder
         */
        public QuerySpecificationBuilder<UnitItemBuilder<ParentBuilder>> withQuerySpecification(){
            return new QuerySpecificationBuilder<UnitItemBuilder<ParentBuilder>>
                    (set(Select.QuerySpecification::new,
                            target::setQuerySpecification))
                    .in(this);
        }

        /**
         * set
         * @param queryExpression QueryExpression
         * @return THIS
         */
        public UnitItemBuilder<ParentBuilder> withQueryExpression(Select.QueryExpression queryExpression){
            this.target.setQueryExpression(queryExpression);
            return this;
        }

        /**
         * in
         * @return QueryExpressionBuilder
         */
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
     * @see com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder
     * @param <ParentBuilder>
     */
    public static class QuerySpecificationBuilder<ParentBuilder>
            extends CodeTreeBuilder<QuerySpecificationBuilder<ParentBuilder>, ParentBuilder, Select.QuerySpecification> {

        public QuerySpecificationBuilder() {
            super(new Select.QuerySpecification());
        }

        public QuerySpecificationBuilder(Select.QuerySpecification tar) {
            super(tar);
        }

        /**
         * set
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withAll() {
            target.setUseAll(true);
            return this;
        }

        /**
         * set
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withDistinct() {
            target.setUseDistinct(true);
            return this;
        }

        /**
         * set
         * @param top Top
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withTop(Top top){
            this.target.setTop(top);
            return this;
        }

        /**
         * in
         * @return TopBuilder
         */
        public TopBuilder<QuerySpecificationBuilder<ParentBuilder>> withTop(){
            Top top = new Top();
            target.setTop(top);
            return new TopBuilder<QuerySpecificationBuilder<ParentBuilder>>(top)
                    .in(this);
        }

        /**
         * set
         * @param selectItems SelectItem
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withSelectItem(com.xy.xsql.tsql.model.queries.select.Select.SelectItem... selectItems) {
            initAdd(Arrays.asList(selectItems),
                    target::getSelectList,
                    target::setSelectList);
            return this;
        }

        /**
         * set
         * @param selectItems SelectItem
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withSelectItem(List<com.xy.xsql.tsql.model.queries.select.Select.SelectItem> selectItems) {
            initAdd(selectItems,
                    target::getSelectList,
                    target::setSelectList);
            return this;
        }

        /**
         * in
         * @return SelectItemBuilder
         */
        public com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder.SelectItemBuilder<QuerySpecificationBuilder<ParentBuilder>> withSelectItem(){
            com.xy.xsql.tsql.model.queries.select.Select.SelectItem item = new com.xy.xsql.tsql.model.queries.select.Select.SelectItem();
            initList(target::getSelectList,
                    target::setSelectList);
            target.getSelectList().add(item);
            return new com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder.SelectItemBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (item)
                    .in(this);
        }

        /**
         * set
         * @param newTable new table
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withInto(String newTable) {
            return new IntoBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (initSet(Into::new,
                            target::getInto,
                            target::setInto))
                    .in(this)
                    .withNewTable(new TableName(newTable))
                    .out();
        }

        /**
         * set
         * @param from From
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withFrom(From from) {
            this.target.setFrom(from);
            return this;
        }

        /**
         * in
         * @return FromBuilder
         */
        public FromBuilder<QuerySpecificationBuilder<ParentBuilder>> withFrom() {
            From from = new From();
            target.setFrom(from);
            return new FromBuilder<QuerySpecificationBuilder<ParentBuilder>>(from)
                    .in(this);
        }

        /**
         * set
         * @param where Where
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withWhere(Where where) {
            target.setWhere(where);
            return this;
        }

        /**
         * in
         * @return WhereBuilder
         */
        public WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> withWhere() {
            return new WhereBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (initSet(Where::new,
                            target::getWhere,
                            target::setWhere))
                    .in(this);
        }

        /**
         * set
         * @param groupBy GroupBy
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withGroupBy(GroupBy groupBy) {
            this.target.setGroupBy(groupBy);
            return this;
        }

        /**
         * in
         * @return GroupByBuilder
         */
        public GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> withGroupBy() {
            return new GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (initSet(GroupBy::new,
                            target::getGroupBy,
                            target::setGroupBy))
                    .in(this);
        }

        /**
         * set
         * @param having Having
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> withHaving(Having having) {
            this.target.setHaving(having);
            return this;
        }

        /**
         * in
         * @return HavingBuilder
         */
        public HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> withHaving() {
            return new HavingBuilder<QuerySpecificationBuilder<ParentBuilder>>
                    (initSet(Having::new,
                            target::getHaving,
                            target::setHaving))
                    .in(this);
        }




        /*
        Quick set/into
        Same as com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilder
         */

        /**
         * Quick set useAll
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $All() {
            return withAll();
        }

        /**
         * Quick set useDistinct
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $Distinct() {
            return withDistinct();
        }

        /**
         * Quick in TopBuilder
         * And set top
         * @return TopBuilder
         */
        public TopBuilder<QuerySpecificationBuilder<ParentBuilder>> $Top(){
            return withTop();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $() {
            return withSelectItem()
                    .withAll()
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param tableName TableName
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $(TableName tableName) {
            return withSelectItem()
                    .withTableAll(tableName)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param columnName ColumnName
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $(ColumnName columnName) {
            return withSelectItem()
                    .withColumnName(columnName)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param columnName ColumnName
         * @param columnAlias column alias
         * @return THIS
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
         * @param columnName ColumnName
         * @param expression Expression
         * @return THIS
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
         * @param expression Expression
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $(Expression expression) {
            return withSelectItem()
                    .withExpression(expression)
                    .and();
        }

        /**
         * Quick set selectList
         * into SelectItemBuilder get-out
         * @param expression Expression
         * @param columnAlias column alias
         * @return THIS
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
         * @param newTable new table
         * @return THIS
         */
        public QuerySpecificationBuilder<ParentBuilder> $Into(String newTable) {
            return withInto(newTable);
        }

        /**
         * Quick in FromBuilder
         * And set from
         * @return FromBuilder
         */
        public FromBuilder<QuerySpecificationBuilder<ParentBuilder>> $From() {
            return withFrom();
        }

        /**
         * Quick in WhereBuilder
         * And set where
         * @return WhereBuilder
         */
        public WhereBuilder<QuerySpecificationBuilder<ParentBuilder>> $Where() {
            return withWhere();
        }

        /**
         * Quick in GroupByBuilder
         * And set groupBy
         * @return GroupByBuilder
         */
        public GroupByBuilder<QuerySpecificationBuilder<ParentBuilder>> $GroupBy() {
            return withGroupBy();
        }

        /**
         * Quick in HavingBuilder
         * And set having
         * @return HavingBuilder
         */
        public HavingBuilder<QuerySpecificationBuilder<ParentBuilder>> $Having() {
            return withHaving();
        }

    }

}
