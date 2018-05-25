package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.CodeTreeLazyConfigBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.queries.select.GroupBy;
import com.xy.xsql.tsql.model.queries.select.GroupBy.GroupByExpression;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.ListBuilder.initAdd;
import static com.xy.xsql.core.ListBuilder.initList;
import static com.xy.xsql.core.ListBuilder.initNew;

/**
 * GroupByBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"unused", "WeakerAccess","DanglingJavadoc"})
public class GroupByBuilder<ParentBuilder>
        extends CodeTreeBuilder<GroupByBuilder<ParentBuilder>,ParentBuilder,GroupBy> {

    public GroupByBuilder(GroupBy groupBy) {
        super(groupBy);
    }

    public GroupByBuilder() {
        super(new GroupBy());
    }


    /**
     * set
     * @param item Item
     * @return THIS
     */
    public GroupByBuilder<ParentBuilder> withItem(GroupBy.Item item){
        initAdd(item,
                target::getItems,
                target::setItems);
        return this;
    }

    /**
     * in
     * @return ItemBuilder
     */
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
     * @param columnExpressions Expression
     * @return THIS
     */
    public GroupByBuilder<ParentBuilder> $(Expression columnExpressions) {
        return withItem()._Base()
                .withColumnExpression(columnExpressions)
                .and();
    }

    /**
     * Quick set
     * @param columnName ColumnName
     * @param useWith with
     * @return THIS
     */
    public GroupByBuilder<ParentBuilder> $(ColumnName columnName, boolean useWith) {
        return withItem()._ColumnName(columnName,useWith);
    }

    /**
     * Quick set
     * @return THIS
     */
    public GroupByBuilder<ParentBuilder> $_() {
        return withItem()._Total();
    }

    /**
     * Quick in
     * @return RollupItemBuilder
     */
    public RollupItemBuilder<GroupByBuilder<ParentBuilder>> $Rollup() {
        return withItem()._Rollup();
    }

    /**
     * Quick in
     * @return CubeItemBuilder
     */
    public CubeItemBuilder<GroupByBuilder<ParentBuilder>> $Cube() {
        return withItem()._Cube();
    }

    /**
     * Quick in
     * @return GroupingSetsItemBuilder
     */
    public GroupingSetsItemBuilder<GroupByBuilder<ParentBuilder>> $GroupingSets() {
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

        /**
         * Confirm type of Item
         * @return BaseItemBuilder
         */
        public BaseItemBuilder<ParentBuilder> _Base(){
            return new BaseItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        /**
         * Confirm type of Item
         * @return CubeItemBuilder
         */
        public CubeItemBuilder<ParentBuilder> _Cube(){
            return new CubeItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        /**
         * Confirm type of Item
         * @return RollupItemBuilder
         */
        public RollupItemBuilder<ParentBuilder> _Rollup(){
            return new RollupItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        /**
         * Confirm type of Item
         * @return GroupingSetsItemBuilder
         */
        public GroupingSetsItemBuilder<ParentBuilder> _GroupingSets(){
            return new GroupingSetsItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        /**
         * set
         * @return PARENT
         */
        public ParentBuilder _Total() {
            target.set(new GroupBy.TotalItem());
            return this.out();
        }

        /**
         * set
         * @param columnName ColumnName
         * @param useWith with
         * @return PARENT
         */
        public ParentBuilder _ColumnName(ColumnName columnName, boolean useWith) {
            GroupBy.ColumnNameItem item = new GroupBy.ColumnNameItem();
            item.setColumnName(columnName);
            item.setUseWithDISTRIBUTED_AGG(useWith);
            target.set(item);
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

        /**
         * set
         * @param columnExpression Expression
         * @return THIS
         */
        public BaseItemBuilder<ParentBuilder> withColumnExpression(Expression columnExpression) {
            target.setExpression(columnExpression);
            return this;
        }

        /**
         * set
         * @deprecated only support set one GroupBy.Item
         * @param columnExpression Expression
         * @return THIS
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

        public RollupItemBuilder() {
            super(new GroupBy.RollupItem());
        }

        public RollupItemBuilder(GroupBy.RollupItem item) {
            super(item);
        }

        public RollupItemBuilder(Setter<GroupBy.RollupItem> setter) {
            super(new GroupBy.RollupItem(),setter);
        }


        /**
         * in
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>> withItem() {
            return new GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>>
                    (initNew(GroupByExpression::new,
                            target::getGroupByExpressionList,
                            target::setGroupByExpressionList))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick in GroupByExpressionBuilder
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>> $() {
            return withItem();
        }

        /**
         * Quick set groupByExpressionList
         * into GroupByExpressionBuilder and get-out
         * @param expressions Expression
         * @return TSHI
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

        public CubeItemBuilder() {
            super(new GroupBy.CubeItem());
        }

        public CubeItemBuilder(GroupBy.CubeItem item) {
            super(item);
        }

        public CubeItemBuilder(Setter<GroupBy.CubeItem> setter) {
            super(new GroupBy.CubeItem(),setter);
        }

        /**
         * in
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>> withItem() {
            return new GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>>
                    (initNew(GroupByExpression::new,
                            target::getGroupByExpressionList,
                            target::setGroupByExpressionList))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick in GroupByExpressionBuilder
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>> $() {
            return withItem();
        }

        /**
         * Quick set groupByExpressionList
         * into GroupByExpressionBuilder and get-out
         * @param expressions Expression
         * @return THIS
         */
        public CubeItemBuilder<ParentBuilder> $(Expression... expressions) {
            return withItem()
                    .withColumnExpression(expressions)
                    .and();
        }

    }

    /**
     * TotalItemBuilder
     * @param <ParentBuilder>
     */
    public static class TotalItemBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<TotalItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.TotalItem, GroupBy.TotalItem> {

        public TotalItemBuilder() {
            super(new GroupBy.TotalItem());
        }

        public TotalItemBuilder(GroupBy.TotalItem item) {
            super(item);
        }

        public TotalItemBuilder(Setter<GroupBy.TotalItem> setter) {
            super(new GroupBy.TotalItem(),setter);
        }

    }

    /**
     * GroupingSetsItemBuilder
     * @param <ParentBuilder>
     */
    public static class GroupingSetsItemBuilder<ParentBuilder>
            extends CodeTreeLazyConfigBuilder<GroupingSetsItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupingSetsItem, GroupBy.GroupingSetsItem> {

        public GroupingSetsItemBuilder() {
            super(new GroupBy.GroupingSetsItem());
        }

        public GroupingSetsItemBuilder(GroupBy.GroupingSetsItem item) {
            super(item);
        }

        public GroupingSetsItemBuilder(Setter<GroupBy.GroupingSetsItem> setter) {
            super(new GroupBy.GroupingSetsItem(),setter);
        }

        /**
         * set
         * @param item GroupingSet
         * @return THIS
         */
        public GroupingSetsItemBuilder<ParentBuilder> withItem(GroupBy.GroupingSet item) {
            initAdd(item,
                    target::getGroupingSetItemList,
                    target::setGroupingSetItemList);
            return this;
        }

        /**
         * in
         * @return GroupingSetBuilder
         */
        public GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>> withItem() {
            return new GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>>
                    (initNew(GroupBy.GroupingSet::new,
                            target::getGroupingSetItemList,
                            target::setGroupingSetItemList))
                    .in(this);
        }




        /*
        Quick
         */

        /**
         * Quick in GroupingSetBuilder
         * @return GroupingSetBuilder
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

        /**
         * set
         * @param columnExpressions Expression
         * @return THIS
         */
        public GroupByExpressionBuilder<ParentBuilder> withColumnExpression(Expression... columnExpressions) {
            if(CheckUtil.isNullOrEmpty(columnExpressions)){
                return this;
            }
            initAdd(Arrays.asList(columnExpressions),
                    target::getColumnExpressionList,
                    target::setColumnExpressionList);
            return this;
        }




        /*
        Quick
         */

        /**
         * Quick set
         * @param columnExpressions Expression
         * @return THIS
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

        public GroupingSetBuilder() {
            super(new GroupBy.GroupingSet());
        }

        public GroupingSetBuilder(GroupBy.GroupingSet groupingSet) {
            super(groupingSet);
        }


        /**
         * set
         * @return THIS
         */
        public GroupingSetBuilder<ParentBuilder> withTotal() {
            target.setUseTotal(true);
            return this;
        }

        /**
         * set
         * @param item Item
         * @return THIS
         */
        public GroupingSetBuilder<ParentBuilder> withItem(GroupBy.GroupingSet.Item item){
            initAdd(item,
                    target::getGroupingSetItemList,
                    target::setGroupingSetItemList);
            return this;
        }

        /**
         * set
         * @param item Item
         * @return THIS
         */
        public GroupingSetBuilder<ParentBuilder> withItem(GroupBy.GroupingSet.Item... item){
            initList(target::getGroupingSetItemList,
                    target::setGroupingSetItemList);
            this.target.getGroupingSetItemList().addAll(Arrays.asList(item));
            return this;
        }

        /**
         * in
         * @return GroupingSetItemBuilder
         */
        public GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>> withItem(){
            initList(target::getGroupingSetItemList,
                    target::setGroupingSetItemList);
            return new GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>>
                    (target.getGroupingSetItemList()::add)
                    .in(this);
        }




        /*
        Quick set
         */

        /**
         * Quick set useTotal
         * @return THIS
         */
        public GroupingSetBuilder<ParentBuilder> $_() {
            return withTotal();
        }

        /**
         * Quick set groupByExpressionList
         * Confirm type of GroupingSet.Item:BaseItem
         * And set BaseItem columnExpressions
         * into GroupByExpressionBuilder and get-out
         * @param columnExpressions Expression
         * @return THIS
         */
        public GroupingSetBuilder<ParentBuilder> $(Expression... columnExpressions) {
            return withItem()
                    ._Base()
                    .withColumnExpression(columnExpressions)
                    .and();
        }

        /**
         * Quick in RollupItemBuilder
         * Confirm type of GroupingSet.Item:RollupItem
         * @return RollupItemBuilder
         */
        public RollupItemBuilder<GroupingSetBuilder<ParentBuilder>> $Rollup() {
            return withItem()._Rollup();
        }

        /**
         * Quick in CubeItemBuilder
         * Confirm type of GroupingSet.Item:CubeItem
         * @return CubeItemBuilder
         */
        public CubeItemBuilder<GroupingSetBuilder<ParentBuilder>> $Cube() {
            return withItem()._Cube();
        }



        //TODO
        /**
         * Quick in GroupingSetItemBuilder
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


        /**
         * set
         * @param item GroupByExpression
         */
        public ParentBuilder with(GroupBy.GroupByExpression item){
            target.set(item);
            return this.out();
        }

        /**
         * set
         * @param item RollupItem
         */
        public ParentBuilder with(GroupBy.RollupItem item){
            target.set(item);
            return this.out();
        }

        /**
         * set
         * @param item CubeItem
         */
        public ParentBuilder with(GroupBy.CubeItem item){
            target.set(item);
            return this.out();
        }


        /**
         * Confirm type of Item
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<ParentBuilder> _Base(){
            return new GroupByExpressionBuilder<ParentBuilder>
                    (this::with)
//                    ((item) -> target.set(item))
                    .in(this.out());
        }

        /**
         * Confirm type of Item
         * @return RollupItemBuilder
         */
        public RollupItemBuilder<ParentBuilder> _Rollup(){
            return new RollupItemBuilder<ParentBuilder>
                    (this::with)
//                    ((item) -> target.set(item))
                    .in(this.out());
        }

        /**
         * Confirm type of Item
         * @return CubeItemBuilder
         */
        public CubeItemBuilder<ParentBuilder> _Cube(){
            return new CubeItemBuilder<ParentBuilder>
                    (this::with)
//                    ((item) -> target.set(item))
                    .in(this.out());
        }

    }

}
