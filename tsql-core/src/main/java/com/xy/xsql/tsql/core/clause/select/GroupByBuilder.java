package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.CodeTreeLazyConfigBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.tsql.model.clause.select.GroupBy.GroupByExpression;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * GroupByBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class GroupByBuilder<ParentBuilder>
        extends CodeTreeBuilder<GroupByBuilder<ParentBuilder>,ParentBuilder,GroupBy> {

    public GroupByBuilder(GroupBy groupBy) {
        super(groupBy);
    }

    public GroupByBuilder() {
        super(new GroupBy());
    }


    public ItemBuilder<GroupByBuilder<ParentBuilder>> withItem(){
        initList(target::getItems,
                target::setItems);
        return new ItemBuilder<GroupByBuilder<ParentBuilder>>
                (target.getItems()::add)
                .in(this);
    }


    /*
    Quick set
     */

    /**
     * Quick set
     * @param columnExpressions
     * @return
     */
    public GroupByBuilder<ParentBuilder> $(Expression columnExpressions) {
        return withItem()._Base()
                .withColumnExpression(columnExpressions)
                .and();
    }

    /**
     * Quick set
     * @return
     */
    public GroupByBuilder<ParentBuilder> $_() {
        return withItem()._Total();
    }

    /**
     * Quick into
     * @return
     */
    public RollupItemBuilder<GroupByBuilder<ParentBuilder>> $Rollup() {
        return withItem()._Rollup();
    }

    /**
     * Quick into
     * @return
     */
    public CubeItemBuilder<GroupByBuilder<ParentBuilder>> $Cube() {
        return withItem()._Cube();
    }

    /**
     * Quick into
     * @return
     */
    public GroupingSetsItemBuilder<GroupByBuilder<ParentBuilder>> $Grouping_Sets() {
        return withItem()._GroupingSets();
    }





    /**
     * Abstract GroupBy.Item Builder
     * @param <ParentBuilder>
     */
    public static class ItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<ItemBuilder<ParentBuilder>, ParentBuilder, Setter<GroupBy.Item>> {

        public ItemBuilder(Setter<GroupBy.Item> setter) {
            super(setter);
        }


        public BaseItemBuilder<ParentBuilder> _Base(){
            return new BaseItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public CubeItemBuilder<ParentBuilder> _Cube(){
            return new CubeItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public RollupItemBuilder<ParentBuilder> _Rollup(){
            return new RollupItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public GroupingSetsItemBuilder<ParentBuilder> _GroupingSets(){
            return new GroupingSetsItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public ParentBuilder _Total() {
            target.set(new GroupBy.TotalItem());
            return this.out();
        }
    }

    /**
     * BaseItemBuilder
     * @param <ParentBuilder>
     */
    public static class BaseItemBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<BaseItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.BaseItem, GroupBy.BaseItem> {

        public BaseItemBuilder(GroupBy.BaseItem item) {
            super(item);
        }

        public BaseItemBuilder(Setter<GroupBy.BaseItem> setter) {
            super(new GroupBy.BaseItem(),setter);
        }

        public BaseItemBuilder<ParentBuilder> withColumnExpression(Expression columnExpression) {
            target.setExpression(columnExpression);
            return this;
        }

        /**
         * @deprecated only support set one GroupBy.Item
         * @param columnExpression
         * @return
         */
        @Deprecated
        public BaseItemBuilder<ParentBuilder> $(Expression columnExpression) {
            return withColumnExpression(columnExpression);
        }
    }

    /**
     * RollupItemBuilder
     * @param <ParentBuilder>
     */
    public static class RollupItemBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<RollupItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.RollupItem, GroupBy.RollupItem> {

        public RollupItemBuilder(GroupBy.RollupItem item) {
            super(item);
        }

        public RollupItemBuilder(Setter<GroupBy.RollupItem> setter) {
            super(new GroupBy.RollupItem(),setter);
        }


        public GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>> withItem() {
            return new GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>>
                    (initNew(GroupByExpression::new,
                            target::getGroupByExpressionList,
                            target::setGroupByExpressionList))
                    .in(this);
        }


        /**
         * Quick into GroupByExpressionBuilder
         * @return
         */
        public GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>> $() {
            return withItem();
        }

        /**
         * Quick set groupByExpressionList
         * into GroupByExpressionBuilder and get-out
         * @param expressions
         * @return
         */
        public RollupItemBuilder<ParentBuilder> $(Expression... expressions) {
            return withItem()
                    .withColumnExpression(expressions)
                    .and();
        }

    }

    /**
     * CubeItemBuilder
     * @param <ParentBuilder>
     */
    public static class CubeItemBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<CubeItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.CubeItem, GroupBy.CubeItem> {

        public CubeItemBuilder(GroupBy.CubeItem item) {
            super(item);
        }

        public CubeItemBuilder(Setter<GroupBy.CubeItem> setter) {
            super(new GroupBy.CubeItem(),setter);
        }

        public GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>> withItem() {
            return new GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>>
                    (initNew(GroupByExpression::new,
                            target::getGroupByExpressionList,
                            target::setGroupByExpressionList))
                    .in(this);
        }


        /**
         * Quick into GroupByExpressionBuilder
         * @return
         */
        public GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>> $() {
            return withItem();
        }

        /**
         * Quick set groupByExpressionList
         * into GroupByExpressionBuilder and get-out
         * @param expressions
         * @return
         */
        public CubeItemBuilder<ParentBuilder> $(Expression... expressions) {
            return withItem()
                    .withColumnExpression(expressions)
                    .and();
        }

    }

    /**
     * GroupingSetsItemBuilder
     * @param <ParentBuilder>
     */
    public static class GroupingSetsItemBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<GroupingSetsItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupingSetsItem, GroupBy.GroupingSetsItem> {

        public GroupingSetsItemBuilder(GroupBy.GroupingSetsItem item) {
            super(item);
        }

        public GroupingSetsItemBuilder(Setter<GroupBy.GroupingSetsItem> setter) {
            super(new GroupBy.GroupingSetsItem(),setter);
        }


        public GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>> withItem() {
            return new GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>>
                    (initNew(GroupBy.GroupingSet::new,
                            target::getGroupingSetItemList,
                            target::setGroupingSetItemList))
                    .in(this);
        }


        /**
         * Quick into GroupingSetBuilder
         * @return
         */
        public GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>> $() {
            return withItem();
        }
    }


    /**
     * GroupByExpressionBuilder
     * @param <ParentBuilder>
     */
    public static class GroupByExpressionBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<GroupByExpressionBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupByExpression, GroupBy.GroupByExpression> {

        public GroupByExpressionBuilder(GroupBy.GroupByExpression groupByExpression) {
            super(groupByExpression);
        }

        public GroupByExpressionBuilder(Setter<GroupBy.GroupByExpression> setter) {
            super(new GroupBy.GroupByExpression(),setter);
        }

        public GroupByExpressionBuilder<ParentBuilder> withColumnExpression(Expression... columnExpressions) {
            if(CheckUtil.isNullOrEmpty(columnExpressions)){
                return this;
            }
            initAdd(Arrays.asList(columnExpressions),
                    target::getColumnExpressionList,
                    target::setColumnExpressionList);
            return this;
        }


        /**
         * Quick set
         * @param columnExpressions
         * @return
         */
        public GroupByExpressionBuilder<ParentBuilder> $(Expression... columnExpressions) {
            return withColumnExpression(columnExpressions);
        }
    }

    /**
     * GroupingSetBuilder
     * @param <ParentBuilder>
     */
    public static class GroupingSetBuilder<ParentBuilder>
            extends CodeTreeBuilder<GroupingSetBuilder<ParentBuilder>,ParentBuilder,GroupBy.GroupingSet> {

        public GroupingSetBuilder(GroupBy.GroupingSet groupingSet) {
            super(groupingSet);
        }


        public GroupingSetBuilder<ParentBuilder> withTotal() {
            target.setUseTotal(true);
            return this;
        }

        public GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>> withItem(){
            initList(target::getGroupByExpressionList,
                    target::setGroupByExpressionList);
            return new GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>>
                    (target.getGroupByExpressionList()::add)
                    .in(this);
        }




        /*
        Quick set
         */

        /**
         * Quick set useTotal
         * @return
         */
        public GroupingSetBuilder<ParentBuilder> $_() {
            return withTotal();
        }

        /**
         * Quick set groupByExpressionList
         * Confirm type of GroupingSet.Item:BaseItem
         * And set BaseItem columnExpressions
         * into GroupByExpressionBuilder and get-out
         * @param columnExpressions
         * @return
         */
        public GroupingSetBuilder<ParentBuilder> $(Expression... columnExpressions) {
            return withItem()
                    ._Base()
                    .withColumnExpression(columnExpressions)
                    .and();
        }

        /**
         * Quick into RollupItemBuilder
         * Confirm type of GroupingSet.Item:RollupItem
         * @return
         */
        public RollupItemBuilder<GroupingSetBuilder<ParentBuilder>> $Rollup() {
            return withItem()._Rollup();
        }

        /**
         * Quick into CubeItemBuilder
         * Confirm type of GroupingSet.Item:CubeItem
         * @return
         */
        public CubeItemBuilder<GroupingSetBuilder<ParentBuilder>> $Cube() {
            return withItem()._Cube();
        }



        //TODO
        /**
         * Quick into GroupingSetItemBuilder
         * create a group of GroupingSetItem
         * ( <grouping_set_item> [ ,...n ] )
         * @return
         */
//        public GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>> $() {
//            return withItem();
//        }

    }

    /**
     * Abstract GroupBy.GroupingSet.Item Builder
     * @param <ParentBuilder>
     */
    public static class GroupingSetItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<GroupingSetItemBuilder<ParentBuilder>, ParentBuilder, Setter<GroupBy.GroupingSet.Item>> {

        public GroupingSetItemBuilder(Setter<GroupBy.GroupingSet.Item> setter) {
            super(setter);
        }

        //TODO lambda or method
//
//        public void set(GroupBy.GroupByExpression item){
//            target.set(item);
//        }
//
//        public void set(GroupBy.RollupItem item){
//            target.set(item);
//        }
//
//        public void set(GroupBy.CubeItem item){
//            target.set(item);
//        }


        public GroupByExpressionBuilder<ParentBuilder> _Base(){
            return new GroupByExpressionBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public RollupItemBuilder<ParentBuilder> _Rollup(){
            return new RollupItemBuilder<ParentBuilder>
                    //TODO lambda or method
//                    (this::set)
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public CubeItemBuilder<ParentBuilder> _Cube(){
            return new CubeItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

    }

}
