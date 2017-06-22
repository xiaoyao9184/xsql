package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class GroupByConverter
        implements BlockConverter<GroupBy> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,GroupBy> builder =
            new ReferenceBlockBuilder<Void,GroupBy>()
                    .sub_keyword(Keywords.GROUP)
                    .sub_keyword(Keywords.BY)
                    .sub()
                        .description("--GroupBy_Item_List")
                        .list(ItemConverter.meta())
                        .data(GroupBy::getItems)
                        .oneMore()
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(GroupBy groupBy) {
        return builder
                .data(groupBy)
                .build();
    }


    public static class ItemConverter
            implements BlockConverter<GroupBy.Item> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.Item> builder =
                new ReferenceBlockBuilder<Void,GroupBy.Item>()
                        .oneOf(BaseItemConverter.meta())
                        .oneOf(RollupItemConverter.meta())
                        .oneOf(CubeItemConverter.meta())
                        .oneOf(GroupingSetsItemConverter.meta())
                        .oneOf("()")
                            .description("calculates the grand total")
                            .data("()")
                            .and()
                        .headFootTakeLine()
                        .subTakeLine();

        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.Item item) {
            return builder
                    .data(item)
                    .build();
        }
    }

    public static class BaseItemConverter
            implements BlockConverter<GroupBy.BaseItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.BaseItem> builder =
                new ReferenceBlockBuilder<Void,GroupBy.BaseItem>()
                        .sub("column-expression")
                            .data(GroupBy.BaseItem::getExpression)
                            .and();

        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.BaseItem item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class RollupItemConverter
            implements BlockConverter<GroupBy.RollupItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.RollupItem> builder =
                new ReferenceBlockBuilder<Void,GroupBy.RollupItem>()
                        .sub_keyword(Keywords.Key.ROLLUP)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list()
                            .sub("group_by_expression")
                                .ref(GroupByExpressionConverter.class)
                                .and()
                            .oneMore()
                            .data(GroupBy.RollupItem::getGroupByExpressionList)
                            .and()
                        .sub_keyword(Other.GROUP_END);
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.RollupItem item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class CubeItemConverter
            implements BlockConverter<GroupBy.CubeItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.CubeItem> builder =
                new ReferenceBlockBuilder<Void,GroupBy.CubeItem>()
                        .sub_keyword(Keywords.Key.CUBE)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list()
                            .sub("group_by_expression")
                                .ref(GroupByExpressionConverter.class)
                                .and()
                            .data(GroupBy.CubeItem::getGroupByExpressionList)
                            .oneMore()
                            .and()
                        .sub_keyword(Other.GROUP_END);
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.CubeItem item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class GroupingSetsItemConverter
            implements BlockConverter<GroupBy.GroupingSetsItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.GroupingSetsItem> builder =
                new ReferenceBlockBuilder<Void,GroupBy.GroupingSetsItem>()
                        .sub_keyword(Keywords.Key.GROUPING)
                        .sub_keyword(Keywords.Key.SETS)
                        .sub_keyword(Other.GROUP_START)
                        .sub()
                            .list()
                            .sub("grouping_set")
                                .ref(GroupingSetConverter.class)
                                .and()
                            .data(GroupBy.GroupingSetsItem::getGroupingSetItemList)
                            .oneMore()
                            .and()
                        .sub_keyword(Other.GROUP_END);
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.GroupingSetsItem item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class TotalItemConverter
            implements BlockConverter<GroupBy.TotalItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.TotalItem> builder =
                new ReferenceBlockBuilder<Void,GroupBy.TotalItem>()
                        .data("()");
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.TotalItem item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class GroupByExpressionConverter
            implements BlockConverter<GroupBy.GroupByExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.GroupByExpression> builder =
                new ReferenceBlockBuilder<Void,GroupBy.GroupByExpression>()
                        .overall("group_by_expression")
                        .oneOf("column-expression")
                            .filter(d -> d.getColumnExpressionList().size() == 1)
                            .data(d -> d.getColumnExpressionList().get(0))
                            .and()
                        .oneOf()
                            .description("--column-expression list")
                            .filter(d -> d.getColumnExpressionList().size() > 1)
                            .sub_keyword(Other.GROUP_START)
                            .sub()
                                .list()
                                .sub("column-expression")
                                    .and()
                                .data(GroupBy.GroupByExpression::getColumnExpressionList)
                                .more()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.GroupByExpression item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class GroupingSetConverter
            implements BlockConverter<GroupBy.GroupingSet> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.GroupingSet> builder =
                new ReferenceBlockBuilder<Void,GroupBy.GroupingSet>()
                        .overall("grouping_set")
                        .oneOf("()")
                            .description("() --calculates the grand total")
                            .filter(d -> !d.isUseTotal())
                            .data("()")
                            .and()
                        .oneOf("grouping_set_item")
                            .description("grouping_set_item")
                            .filter(d -> d.getGroupByExpressionList().size() == 1)
                            .ref(GroupBy.GroupingSet.Item.class)
                            .data(d -> d.getGroupByExpressionList().get(0))
                            .and()
                        .oneOf()
                            .description("--<grouping_set_item> list")
                            .filter(d -> d.getGroupByExpressionList().size() > 1)
                            .sub_keyword(Other.GROUP_START)
                            .sub("grouping_set_item")
                                .list("grouping_set_item")
                                .ref(GroupBy.GroupingSet.Item.class)
                                .data(GroupBy.GroupingSet::getGroupByExpressionList)
                                .more()
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.GroupingSet item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class GroupingSetItemConverter
            implements BlockConverter<GroupBy.GroupingSet.Item> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,GroupBy.GroupingSet.Item> builder =
                new ReferenceBlockBuilder<Void,GroupBy.GroupingSet.Item>()
                        .overall("grouping_set_item")
                        .oneOf("group_by_expression")
                            .filter(d -> !(d instanceof GroupBy.GroupByExpression))
                            .data(d -> d)
                            .and()
                        .oneOf(RollupItemConverter.meta())
//                            .description("--ROLLUP")
//                            .filter(d -> !(d instanceof GroupBy.RollupItem))
//                            .data(d -> d)
//                            .and()
                        .oneOf(CubeItemConverter.meta())
//                            .description("--CUBE")
//                            .filter(d -> !(d instanceof GroupBy.CubeItem))
//                            .data(d -> d)
//                            .and();
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(GroupBy.GroupingSet.Item item) {
            return builder
                    .data(item)
                    .build();
        }
    }


}
