package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.builder.CodeTreeBuilder;
import com.xy.xsql.core.builder.CodeTreeLazyConfigBuilder;
import com.xy.xsql.core.lambda.Setter;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.tsql.model.clause.select.GroupBy.GroupByExpression;
import com.xy.xsql.tsql.model.expression.Expression;

import java.util.ArrayList;

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


    public GroupByItemBuilder<GroupByBuilder<ParentBuilder>> withItem(){
        initList(target::getItems,
                target::setItems);
        return new GroupByItemBuilder<GroupByBuilder<ParentBuilder>>
                (target.getItems()::add)
                .in(this);
    }


    /**
     * Abstract GroupBy.Item Builder
     * @param <ParentBuilder>
     */
    public static class GroupByItemBuilder<ParentBuilder>
            extends CodeTreeBuilder<GroupByItemBuilder<ParentBuilder>, ParentBuilder, Setter<GroupBy.Item>> {

        public GroupByItemBuilder(Setter<GroupBy.Item> setter) {
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

        public GroupingSetsItemBuilder<ParentBuilder> _GroupingSet(){
            return new GroupingSetsItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
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


        public GroupByExpressionBuilder<ParentBuilder> withColumnExpression(Expression columnExpression) {
            if(target.getColumnExpressionList() == null){
                target.setColumnExpressionList(new ArrayList<Expression>());
            }
            target.getColumnExpressionList().add(columnExpression);
            return this;
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


        public GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>> withItem(){
            initList(target::getGroupByExpressionList,
                    target::setGroupByExpressionList);
            return new GroupingSetItemBuilder<GroupingSetBuilder<ParentBuilder>>
                    (target.getGroupByExpressionList()::add)
                    .in(this);
        }
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

        public RollupItemBuilder<ParentBuilder> _Cube(){
            return new RollupItemBuilder<ParentBuilder>
                    //TODO lambda or method
//                    (this::set)
                    ((item) -> target.set(item))
                    .in(this.out());
        }

        public CubeItemBuilder<ParentBuilder> _Rollup(){
            return new CubeItemBuilder<ParentBuilder>
                    ((item) -> target.set(item))
                    .in(this.out());
        }
    }


//
//
//    /**
//     * Same as GroupByExpressionBuilder
//     * @param <ParentBuilder>
//     */
//    public static class BaseGroupingSetItemBuilder<ParentBuilder>
//            extends CodeTreeLazyConfigBuilder<BaseGroupingSetItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.GroupByExpression, GroupBy.GroupingSet.Item> {
//
//        public BaseGroupingSetItemBuilder(GroupBy.GroupByExpression item) {
//            super(item);
//        }
//
//        public BaseGroupingSetItemBuilder(Setter<GroupBy.GroupingSet.Item> setter) {
//            super(new GroupBy.GroupByExpression(),setter);
//        }
//
//        public BaseGroupingSetItemBuilder<ParentBuilder> withColumnExpression(Expression... columnExpression) {
//            initAdd(Arrays.asList(columnExpression),
//                    target::getColumnExpressionList,
//                    target::setColumnExpressionList);
//            return this;
//        }
//    }
//
//    /**
//     * Same as RollupItemBuilder
//     * @param <ParentBuilder>
//     */
//    public static class RollupGroupingSetItemBuilder<ParentBuilder>
//            extends CodeTreeLazyConfigBuilder<RollupGroupingSetItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.RollupItem, GroupBy.GroupingSet.Item> {
//
//        public RollupGroupingSetItemBuilder(GroupBy.RollupItem item) {
//            super(item);
//        }
//
//        public RollupGroupingSetItemBuilder(Setter<GroupBy.GroupingSet.Item> setter) {
//            super(new GroupBy.RollupItem(),setter);
//        }
//
//
//        public GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>> withItem() {
//            return new GroupByExpressionBuilder<RollupItemBuilder<ParentBuilder>>
//                    (initNew(GroupByExpression::new,
//                            target::getGroupByExpressionList,
//                            target::setGroupByExpressionList))
//                    .in(this);
//        }
//
//    }
//
//    /**
//     * Same as CubeItemBuilder
//     * @param <ParentBuilder>
//     */
//    public static class CubeGroupingSetItemBuilder<ParentBuilder>
//            extends CodeTreeLazyConfigBuilder<CubeGroupingSetItemBuilder<ParentBuilder>, ParentBuilder, GroupBy.CubeItem, GroupBy.GroupingSet.Item> {
//
//        public CubeGroupingSetItemBuilder(GroupBy.CubeItem item) {
//            super(item);
//        }
//
//        public CubeGroupingSetItemBuilder(Setter<GroupBy.GroupingSet.Item> setter) {
//            super(new GroupBy.CubeItem(),setter);
//        }
//
//        public GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>> withItem() {
//            return new GroupByExpressionBuilder<CubeItemBuilder<ParentBuilder>>
//                    (initNew(GroupByExpression::new,
//                            target::getGroupByExpressionList,
//                            target::setGroupByExpressionList))
//                    .in(this);
//        }
//
//    }

}
