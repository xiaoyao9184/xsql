package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.core.SubBuilder;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.tsql.model.clause.select.GroupBy.GroupByExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class GroupByBuilder<Done>
        extends SubBuilder<GroupByBuilder<Done>,Void,Done> {

    private GroupBy groupBy;

    public GroupByBuilder(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public GroupByBuilder() {
        this.groupBy = new GroupBy();
    }

    public GroupBy build(){
        return groupBy;
    }


    public GroupListByBuilder<GroupByBuilder<Done>> withGroupList(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new GroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    @Deprecated
    public BaseGroupListByBuilder<GroupByBuilder<Done>> withBaseGroupList(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new BaseGroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    @Deprecated
    public CubeGroupListByBuilder<GroupByBuilder<Done>> withCubeGroupList(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new CubeGroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    @Deprecated
    public RollupGroupListByBuilder<GroupByBuilder<Done>> withRollupGroupList(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new RollupGroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    @Deprecated
    public GroupingSetsGroupByListBuilder<GroupByBuilder<Done>> withGroupingSetsGroupByList(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new GroupingSetsGroupByListBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }


    /**
     * @see #withBaseGroupList()
     * @return
     */
    @Deprecated
    public BaseGroupListByBuilder<GroupByBuilder<Done>> base(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new BaseGroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    /**
     * @see #withCubeGroupList()
     * @return
     */
    @Deprecated
    public CubeGroupListByBuilder<GroupByBuilder<Done>> cube(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new CubeGroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    /**
     * @see #withRollupGroupList()
     * @return
     */
    @Deprecated
    public RollupGroupListByBuilder<GroupByBuilder<Done>> rollup(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new RollupGroupListByBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }

    /**
     * @see #withGroupingSetsGroupByList()
     * @return
     */
    @Deprecated
    public GroupingSetsGroupByListBuilder<GroupByBuilder<Done>> groupingSet(){
        List<GroupBy.GroupByItem> items = new ArrayList<>();
        this.groupBy.setItems(items);
        return new GroupingSetsGroupByListBuilder<GroupByBuilder<Done>>(items)
                .in(this);
    }


    public class GroupListByBuilder<Done>
            extends SubBuilder<GroupListByBuilder<Done>,Void,Done> {
        private List<GroupBy.GroupByItem> items;

        public GroupListByBuilder(List<GroupBy.GroupByItem> items) {
            this.items = items;
        }

        public BaseGroupByItemBuilder<GroupListByBuilder<Done>> withBaseItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new BaseGroupByItemBuilder<GroupListByBuilder<Done>>(item)
                    .in(this);
        }

        public RollupGroupByItemBuilder<GroupListByBuilder<Done>> withRollupItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new RollupGroupByItemBuilder<GroupListByBuilder<Done>>(item)
                    .in(this);
        }

        public CubeGroupByItemBuilder<GroupListByBuilder<Done>> withCubeItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new CubeGroupByItemBuilder<GroupListByBuilder<Done>>(item)
                    .in(this);
        }

        public GroupingSetsGroupByItemBuilder<GroupListByBuilder<Done>> withGroupingSetsItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new GroupingSetsGroupByItemBuilder<GroupListByBuilder<Done>>(item)
                    .in(this);
        }
    }


    /**
     *
     * @param <Done>
     */
    public class BaseGroupListByBuilder<Done>
            extends SubBuilder<BaseGroupListByBuilder<Done>,Void,Done> {
        private List<GroupBy.GroupByItem> items;

        public BaseGroupListByBuilder(List<GroupBy.GroupByItem> items) {
            this.items = items;
        }

        public BaseGroupByItemBuilder<BaseGroupListByBuilder<Done>> withItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new BaseGroupByItemBuilder<BaseGroupListByBuilder<Done>>(item)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public class CubeGroupListByBuilder<Done>
            extends SubBuilder<CubeGroupListByBuilder<Done>,Void,Done> {
        private List<GroupBy.GroupByItem> items;

        public CubeGroupListByBuilder(List<GroupBy.GroupByItem> items) {
            this.items = items;
        }

        public CubeGroupByItemBuilder<CubeGroupListByBuilder<Done>> withItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new CubeGroupByItemBuilder<CubeGroupListByBuilder<Done>>(item)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public class RollupGroupListByBuilder<Done>
            extends SubBuilder<RollupGroupListByBuilder<Done>,Void,Done> {
        private List<GroupBy.GroupByItem> items;

        public RollupGroupListByBuilder(List<GroupBy.GroupByItem> items) {
            this.items = items;
        }

        public RollupGroupByItemBuilder<RollupGroupListByBuilder<Done>> withItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new RollupGroupByItemBuilder<RollupGroupListByBuilder<Done>>(item)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class GroupingSetsGroupByListBuilder<Done>
            extends SubBuilder<GroupingSetsGroupByListBuilder<Done>,Void,Done> {
        private List<GroupBy.GroupByItem> items;

        public GroupingSetsGroupByListBuilder(List<GroupBy.GroupByItem> items) {
            this.items = items;
        }

        public GroupingSetsGroupByItemBuilder<GroupingSetsGroupByListBuilder<Done>> withItem(){
            GroupBy.GroupByItem item = new GroupBy.GroupByItem();
            if(this.items == null){
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return new GroupingSetsGroupByItemBuilder<GroupingSetsGroupByListBuilder<Done>>(item)
                    .in(this);
        }
    }




    /**
     *
     * @param <Done>
     */
    public static class BaseGroupByItemBuilder<Done>
            extends SubBuilder<BaseGroupByItemBuilder<Done>,Void,Done> {

        private GroupBy.GroupByItem groupByItem;

        public BaseGroupByItemBuilder(GroupBy.GroupByItem groupByItem) {
            this.groupByItem = groupByItem;
            this.groupByItem.setType(GroupBy.ItemType.Base);
        }


        public BaseGroupByItemBuilder<Done> withColumnExpression(Expression columnExpression) {
            this.groupByItem.setColumnExpression(columnExpression);
            return this;
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class RollupGroupByItemBuilder<Done>
            extends SubBuilder<RollupGroupByItemBuilder<Done>,Void,Done> {

        private GroupBy.GroupByItem groupByItem;

        public RollupGroupByItemBuilder(GroupBy.GroupByItem groupByItem) {
            this.groupByItem = groupByItem;
            this.groupByItem.setType(GroupBy.ItemType.Rollup);
        }


        public GroupByExpressionListBuilder<RollupGroupByItemBuilder<Done>> withGroupByExpressionList() {
            List<GroupByExpression> groupByExpressionList = new ArrayList<>();
            this.groupByItem.setGroupByExpressionList(groupByExpressionList);
            return new GroupByExpressionListBuilder<RollupGroupByItemBuilder<Done>>(groupByExpressionList)
                    .in(this);
        }

        @Deprecated
        public RollupGroupByItemBuilder<Done> withColumnExpression(Expression columnExpression) {
            this.groupByItem.setColumnExpression(columnExpression);
            return this;
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class CubeGroupByItemBuilder<Done>
            extends SubBuilder<CubeGroupByItemBuilder<Done>,Void,Done> {

        private GroupBy.GroupByItem groupByItem;

        public CubeGroupByItemBuilder(GroupBy.GroupByItem groupByItem) {
            this.groupByItem = groupByItem;
            this.groupByItem.setType(GroupBy.ItemType.Cube);
        }

        public GroupByExpressionListBuilder<CubeGroupByItemBuilder<Done>> withGroupByExpressionList() {
            List<GroupByExpression> groupByExpressionList = new ArrayList<>();
            this.groupByItem.setGroupByExpressionList(groupByExpressionList);
            return new GroupByExpressionListBuilder<CubeGroupByItemBuilder<Done>>(groupByExpressionList)
                    .in(this);
        }

        @Deprecated
        public CubeGroupByItemBuilder<Done> withColumnExpression(Expression columnExpression) {
            this.groupByItem.setColumnExpression(columnExpression);
            return this;
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class GroupingSetsGroupByItemBuilder<Done>
            extends SubBuilder<GroupingSetsGroupByItemBuilder<Done>,Void,Done> {

        private GroupBy.GroupByItem groupByItem;

        public GroupingSetsGroupByItemBuilder(GroupBy.GroupByItem groupByItem) {
            this.groupByItem = groupByItem;
            this.groupByItem.setType(GroupBy.ItemType.GroupingSet);
        }


        public GroupingSetListBuilder<GroupingSetsGroupByItemBuilder<Done>> withGroupingSetList() {
            List<GroupBy.GroupingSet> groupingSetList = new ArrayList<>();
            this.groupByItem.setGroupingSetList(groupingSetList);
            return new GroupingSetListBuilder<GroupingSetsGroupByItemBuilder<Done>>(groupingSetList)
                    .in(this);
        }
    }


    /**
     *
     * @param <Done>
     */
    public static class GroupByExpressionListBuilder<Done>
            extends SubBuilder<GroupByExpressionListBuilder<Done>,Void,Done> {

        private List<GroupByExpression> groupByExpressionList;

        public GroupByExpressionListBuilder(List<GroupByExpression> groupByExpressionList) {
            this.groupByExpressionList = groupByExpressionList;
        }


        public GroupByExpressionBuilder<GroupByExpressionListBuilder<Done>> withGroupByExpression() {
            GroupByExpression groupByExpression = new GroupByExpression();
            this.groupByExpressionList.add(groupByExpression);
            return new GroupByExpressionBuilder<GroupByExpressionListBuilder<Done>>(groupByExpression)
                    .in(this);
        }
    }

    /**
     * 
     * @param <Done>
     */
    public static class GroupByExpressionBuilder<Done>
            extends SubBuilder<GroupByExpressionBuilder<Done>,Void,Done> {

        private GroupBy.GroupByExpression groupByExpression;

        public GroupByExpressionBuilder(GroupBy.GroupByExpression groupByExpression) {
            this.groupByExpression = groupByExpression;
        }


        public GroupByExpressionBuilder<Done> withColumnExpression(Expression columnExpression) {
            if(this.groupByExpression.getColumnExpressionList() == null){
                this.groupByExpression.setColumnExpressionList(new ArrayList<Expression>());
            }
            this.groupByExpression.getColumnExpressionList().add(columnExpression);
            return this;
        }
    }


    /**
     *
     * @param <Done>
     */
    public static class GroupingSetListBuilder<Done>
            extends SubBuilder<GroupingSetListBuilder<Done>,Void,Done> {

        private List<GroupBy.GroupingSet> groupingSetList;

        public GroupingSetListBuilder(List<GroupBy.GroupingSet> groupingSetList) {
            this.groupingSetList = groupingSetList;
        }

        public GroupingSetBuilder<GroupingSetListBuilder<Done>> withGroupingSet(){
            GroupBy.GroupingSet item = new GroupBy.GroupingSet();
            if(this.groupingSetList == null){
                this.groupingSetList = new ArrayList<>();
            }
            this.groupingSetList.add(item);
            return new GroupingSetBuilder<GroupingSetListBuilder<Done>>(item)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class GroupingSetBuilder<Done>
            extends SubBuilder<GroupingSetBuilder<Done>,Void,Done> {

        private GroupBy.GroupingSet groupingSet;

        public GroupingSetBuilder(GroupBy.GroupingSet groupingSet) {
            this.groupingSet = groupingSet;
        }


        public GroupingSetItemListBuilder<GroupingSetBuilder<Done>> withGroupingSetItemList(){
            List<GroupBy.GroupingSetItem> groupingSetItemList = new ArrayList<>();
            this.groupingSet.setGroupingSetItemList(groupingSetItemList);
            return new GroupingSetItemListBuilder<GroupingSetBuilder<Done>>(groupingSetItemList)
                    .in(this);
        }
    }

    /**
     *
     * @param <Done>
     */
    public static class GroupingSetItemListBuilder<Done>
            extends SubBuilder<GroupingSetItemListBuilder<Done>,Void,Done> {

        private List<GroupBy.GroupingSetItem> groupingSetItemList;

        public GroupingSetItemListBuilder(List<GroupBy.GroupingSetItem> groupingSetItemList) {
            this.groupingSetItemList = groupingSetItemList;
        }

        public GroupingSetItemBuilder<GroupingSetItemListBuilder<Done>> withGroupingSetItem(){
            GroupBy.GroupingSetItem groupingSetItem = new GroupBy.GroupingSetItem();
            if(this.groupingSetItemList == null){
                this.groupingSetItemList = new ArrayList<>();
            }
            this.groupingSetItemList.add(groupingSetItem);
            return new GroupingSetItemBuilder<GroupingSetItemListBuilder<Done>>(groupingSetItem)
                    .in(this);
        }
    }

    public static class GroupingSetItemBuilder<Done>
            extends SubBuilder<GroupingSetItemBuilder<Done>,Void,Done> {

        private GroupBy.GroupingSetItem groupingSetItem;

        public GroupingSetItemBuilder(GroupBy.GroupingSetItem groupingSetItem) {
            this.groupingSetItem = groupingSetItem;
        }

        public GroupByExpressionBuilder<GroupingSetItemBuilder<Done>> withGroupByExpression(){
            GroupByExpression groupByExpression = new GroupByExpression();
            List<GroupByExpression> list = new ArrayList<>();
            list.add(groupByExpression);
            this.groupingSetItem.setGroupByExpressionList(list);
            return new GroupByExpressionBuilder<GroupingSetItemBuilder<Done>>(groupByExpression)
                    .in(this);
        }


        public GroupByExpressionListBuilder<GroupingSetItemBuilder<Done>> withRollupGroupByExpressionList(){
            List<GroupByExpression> groupByExpressionList = new ArrayList<>();
            this.groupingSetItem.setUseRollup(true);
            this.groupingSetItem.setGroupByExpressionList(groupByExpressionList);
            return new GroupByExpressionListBuilder<GroupingSetItemBuilder<Done>>(groupByExpressionList)
                    .in(this);
        }


        public GroupByExpressionListBuilder<GroupingSetItemBuilder<Done>> withCubeGroupByExpressionList(){
            List<GroupByExpression> groupByExpressionList = new ArrayList<>();
            this.groupingSetItem.setUseCube(true);
            this.groupingSetItem.setGroupByExpressionList(groupByExpressionList);
            return new GroupByExpressionListBuilder<GroupingSetItemBuilder<Done>>(groupByExpressionList)
                    .in(this);
        }
    }
}
