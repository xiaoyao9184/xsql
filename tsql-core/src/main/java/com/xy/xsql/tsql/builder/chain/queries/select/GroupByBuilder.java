package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.core.builder.parent.ParentHoldBuilder;
import com.xy.xsql.core.builder.parent.ParentHoldLazyConfigBuilder;
import com.xy.xsql.core.lambda.Getter;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.queries.select.GroupBy;
import com.xy.xsql.tsql.model.queries.select.GroupBy.GroupByExpression;
import com.xy.xsql.util.CheckUtil;

import java.util.Arrays;

import static com.xy.xsql.core.handler.list.ListHandler.list;
import static com.xy.xsql.core.handler.object.SupplierObjectHandler.object;

/**
 * GroupByBuilder
 * Created by xiaoyao9184 on 2016/12/28.
 */
@SuppressWarnings({"unused", "WeakerAccess","DanglingJavadoc"})
public class GroupByBuilder<ParentBuilder>
        extends ParentHoldBuilder<GroupByBuilder<ParentBuilder>,ParentBuilder,GroupBy> {

    public GroupByBuilder() {
        super(new GroupBy());
    }

    public GroupByBuilder(GroupBy target) {
        super(target);
    }


    /**
     * set
     * @param item Item
     * @return THIS
     */
    public GroupByBuilder<ParentBuilder> withItem(GroupBy.Item item){
        list(target::getItems, target::setItems)
                .add(item);
        return this;
    }

    /**
     * in
     * @return ItemBuilder
     */
    public ItemBuilder<GroupByBuilder<ParentBuilder>> withItem(){
        list(target::getItems, target::setItems).init();
        return new ItemBuilder<GroupByBuilder<ParentBuilder>>()
                .enter(this, Getter.empty(), target.getItems()::add);
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
            extends ParentHoldLazyConfigBuilder<ItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.Item> {

        public ItemBuilder() {}

        /**
         * Confirm type of Item
         * @return BaseItemBuilder
         */
        public BaseItemBuilder<ParentBuilder> _Base(){
            return new BaseItemBuilder<ParentBuilder>
                    (object(GroupBy.BaseItem::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return CubeItemBuilder
         */
        public CubeItemBuilder<ParentBuilder> _Cube(){
            return new CubeItemBuilder<ParentBuilder>
                    (object(GroupBy.CubeItem::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return RollupItemBuilder
         */
        public RollupItemBuilder<ParentBuilder> _Rollup(){
            return new RollupItemBuilder<ParentBuilder>
                    (object(GroupBy.RollupItem::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return GroupingSetsItemBuilder
         */
        public GroupingSetsItemBuilder<ParentBuilder> _GroupingSets(){
            return new GroupingSetsItemBuilder<ParentBuilder>
                    (object(GroupBy.GroupingSetsItem::new).set(this::init))
                    .in(this.and());
        }

        /**
         * set
         * @return PARENT
         */
        public ParentBuilder _Total() {
            return new TotalItemBuilder<ParentBuilder>
                    (object(GroupBy.TotalItem::new).set(this::init))
                    .in(this.and())
                    .and();
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
            item.setUseWithDistributedAgg(useWith);
            target = item;
            return this.and();
        }
    }

    /**
     * BaseItemBuilder
     * @param <ParentBuilder>
     */
    public static class BaseItemBuilder<ParentBuilder>
            extends ParentHoldBuilder<BaseItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.BaseItem> {

        public BaseItemBuilder() {
            super(new GroupBy.BaseItem());
        }

        public BaseItemBuilder(GroupBy.BaseItem target) {
            super(target);
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
            extends ParentHoldBuilder<RollupItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.RollupItem> {

        public RollupItemBuilder() {
            super(new GroupBy.RollupItem());
        }

        public RollupItemBuilder(GroupBy.RollupItem target) {
            super(target);
        }

        /**
         * in
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>> withItem() {
            return new GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>>
                    (list(target::getGroupByExpressionList, target::setGroupByExpressionList)
                            .addNew(GroupByExpression::new))
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
            extends ParentHoldBuilder<CubeItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.CubeItem> {

        public CubeItemBuilder() {
            super(new GroupBy.CubeItem());
        }

        public CubeItemBuilder(GroupBy.CubeItem target) {
            super(target);
        }

        /**
         * in
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>> withItem() {
            return new GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>>
                    (list(target::getGroupByExpressionList, target::setGroupByExpressionList)
                            .addNew(GroupByExpression::new))
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
            extends ParentHoldBuilder<TotalItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.TotalItem> {

        public TotalItemBuilder() {
            super(new GroupBy.TotalItem());
        }

        public TotalItemBuilder(GroupBy.TotalItem target) {
            super(target);
        }

    }

    /**
     * GroupingSetsItemBuilder
     * @param <ParentBuilder>
     */
    public static class GroupingSetsItemBuilder<ParentBuilder>
            extends ParentHoldBuilder<GroupingSetsItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupingSetsItem> {

        public GroupingSetsItemBuilder() {
            super(new GroupBy.GroupingSetsItem());
        }

        public GroupingSetsItemBuilder(GroupBy.GroupingSetsItem target) {
            super(target);
        }

        /**
         * set
         * @param item GroupingSet
         * @return THIS
         */
        public GroupingSetsItemBuilder<ParentBuilder> withItem(GroupBy.GroupingSet item) {
            list(target::getGroupingSetItemList, target::setGroupingSetItemList)
                    .add(item);
            return this;
        }

        /**
         * in
         * @return GroupingSetBuilder
         */
        public GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>> withItem() {
            return new GroupingSetBuilder<GroupingSetsItemBuilder<ParentBuilder>>
                    (list(target::getGroupingSetItemList, target::setGroupingSetItemList)
                            .addNew(GroupBy.GroupingSet::new))
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
            extends ParentHoldBuilder<GroupByExpressionBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupByExpression> {

        public GroupByExpressionBuilder() {
            super(new GroupBy.GroupByExpression());
        }

        public GroupByExpressionBuilder(GroupBy.GroupByExpression target) {
            super(target);
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
            list(target::getColumnExpressionList, target::setColumnExpressionList)
                    .addAll(columnExpressions);
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
            extends ParentHoldBuilder<GroupingSetBuilder<ParentBuilder>,ParentBuilder,GroupBy.GroupingSet> {

        public GroupingSetBuilder() {
            super(new GroupBy.GroupingSet());
        }

        public GroupingSetBuilder(GroupBy.GroupingSet target) {
            super(target);
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
            list(target::getGroupingSetItemList, target::setGroupingSetItemList)
                    .add(item);
            return this;
        }

        /**
         * set
         * @param item Item
         * @return THIS
         */
        public GroupingSetBuilder<ParentBuilder> withItem(GroupBy.GroupingSet.Item... item){
            list(target::getGroupingSetItemList, target::setGroupingSetItemList).init();
            this.target.getGroupingSetItemList().addAll(Arrays.asList(item));
            return this;
        }

        /**
         * in
         * @return GroupingSetItemBuilder
         */
        public GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>> withItem(){
            list(target::getGroupingSetItemList, target::setGroupingSetItemList).init();
            return new GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>>()
                    .enter(this, Getter.empty(), target.getGroupingSetItemList()::add);
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
            extends ParentHoldLazyConfigBuilder<GroupingSetItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupingSet.Item> {

        public GroupingSetItemBuilder() {}

        /**
         * Confirm type of Item
         * @return GroupByExpressionBuilder
         */
        public GroupByExpressionBuilder<ParentBuilder> _Base(){
            return new GroupByExpressionBuilder<ParentBuilder>
                    (object(GroupByExpression::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return RollupItemBuilder
         */
        public RollupItemBuilder<ParentBuilder> _Rollup(){
            return new RollupItemBuilder<ParentBuilder>
                    (object(GroupBy.RollupItem::new).set(this::init))
                    .in(this.and());
        }

        /**
         * Confirm type of Item
         * @return CubeItemBuilder
         */
        public CubeItemBuilder<ParentBuilder> _Cube(){
            return new CubeItemBuilder<ParentBuilder>
                    (object(GroupBy.CubeItem::new).set(this::init))
                    .in(this.and());
        }

    }

}
