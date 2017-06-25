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
                    .sub_list(ItemConverter.meta())
                        .description("--GroupBy_Item_List")
                        .required()
                        .data(GroupBy::getItems)
                        .headFootTakeLine()
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
                        .czse(d -> d instanceof GroupBy.BaseItem, BaseItemConverter.meta())
                        .czse(d -> d instanceof GroupBy.RollupItem, RollupItemConverter.meta())
                        .czse(d -> d instanceof GroupBy.CubeItem, CubeItemConverter.meta())
                        .czse(d -> d instanceof GroupBy.GroupingSetsItem, GroupingSetsItemConverter.meta())
                        .czse(d -> d instanceof GroupBy.TotalItem, "()")
                            .description("calculates the grand total")
                            .data("()")
                            .and()
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
                        .sub("group_by_expression")
                            .description("{ <group_by_expression> } [,...n]")
                            .required()
                            .list()
                            .more()
                            .ref(GroupByExpressionConverter.class)
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
                        .sub("group_by_expression")
                            .description("{ <group_by_expression> } [,...n]")
                            .required()
                            .list()
                            .more()
                            .ref(GroupByExpressionConverter.class)
                            .data(GroupBy.CubeItem::getGroupByExpressionList)
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
                        .sub("grouping_set")
                            .description("{ <grouping_set> } [,...n]")
                            .required()
                            .list()
                            .more()
                            .ref(GroupingSetConverter.class)
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
                        .czse(d -> d.getColumnExpressionList().size() == 1,"column-expression")
                            .data(d -> d.getColumnExpressionList().get(0))
                            .and()
                        .czse(d -> d.getColumnExpressionList().size() > 1)
                            .description("( column-expression [ ,...n ] )")
                            .sub_keyword(Other.GROUP_START)
                            .sub("column-expression")
                                .list()
                                .more()
                                .data(GroupBy.GroupByExpression::getColumnExpressionList)
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
                        .czse(d -> d.isUseTotal(), "()")
                            .description("() --calculates the grand total")
                            .filter(d -> !d.isUseTotal())
                            .data("()")
                            .and()
                        .czse(d -> d.getGroupByExpressionList().size() == 1, "grouping_set_item")
                            .description("grouping_set_item")
                            .ref(GroupBy.GroupingSet.Item.class)
                            .data(d -> d.getGroupByExpressionList().get(0))
                            .and()
                        .czse(d -> d.getGroupByExpressionList().size() > 1)
                            .description("( <grouping_set_item> [ ,...n ] )")
                            .sub_keyword(Other.GROUP_START)
                            .sub("grouping_set_item")
                                .list()
                                .more()
                                .ref(GroupBy.GroupingSet.Item.class)
                                .data(GroupBy.GroupingSet::getGroupByExpressionList)
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
                        .czse(d -> d instanceof GroupBy.GroupByExpression,"group_by_expression")
                            .and()
                        .czse(d -> d instanceof GroupBy.RollupItem, RollupItemConverter.meta())
                        .czse(d -> d instanceof GroupBy.CubeItem, CubeItemConverter.meta())
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
