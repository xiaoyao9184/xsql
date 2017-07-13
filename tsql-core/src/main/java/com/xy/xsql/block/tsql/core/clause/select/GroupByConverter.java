package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class GroupByConverter
        implements ModelMetaBlockConverter<GroupBy> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,GroupBy>()
                    .overall("GROUP BY")
                    .sub_keyword(Keywords.GROUP)
                    .sub_keyword(Keywords.BY)
                    .sub_list(ItemConverter.meta)
                        .description("--GroupBy_Item_List")
                        .required()
                        .data(GroupBy::getItems)
                        .headFootTakeLine()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ItemConverter
            implements ModelMetaBlockConverter<GroupBy.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.Item>()
                        .czse_ref(d -> d instanceof GroupBy.BaseItem, BaseItemConverter.meta)
                        .czse_ref(d -> d instanceof GroupBy.RollupItem, RollupItemConverter.meta)
                        .czse_ref(d -> d instanceof GroupBy.CubeItem, CubeItemConverter.meta)
                        .czse_ref(d -> d instanceof GroupBy.GroupingSetsItem, GroupingSetsItemConverter.meta)
                        .czse(d -> d instanceof GroupBy.TotalItem, "()")
                            .description("calculates the grand total")
                            .keyword("()")
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class BaseItemConverter
            implements ModelMetaBlockConverter<GroupBy.BaseItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.BaseItem>()
                        .sub("column-expression")
                            .data(GroupBy.BaseItem::getExpression)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class RollupItemConverter
            implements ModelMetaBlockConverter<GroupBy.RollupItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.RollupItem>()
                        .sub_keyword(Keywords.Key.ROLLUP)
                        .sub_keyword(Other.GROUP_START)
                        .sub("group_by_expression")
                            .description("{ <group_by_expression> } [,...n]")
                            .required()
                            .list()
                            .ref(GroupByExpressionConverter.class)
                            .data(GroupBy.RollupItem::getGroupByExpressionList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class CubeItemConverter
            implements ModelMetaBlockConverter<GroupBy.CubeItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.CubeItem>()
                        .sub_keyword(Keywords.Key.CUBE)
                        .sub_keyword(Other.GROUP_START)
                        .sub("group_by_expression")
                            .description("{ <group_by_expression> } [,...n]")
                            .required()
                            .list()
                            .ref(GroupByExpressionConverter.class)
                            .data(GroupBy.CubeItem::getGroupByExpressionList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class GroupingSetsItemConverter
            implements ModelMetaBlockConverter<GroupBy.GroupingSetsItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.GroupingSetsItem>()
                        .sub_keyword(Keywords.Key.GROUPING)
                        .sub_keyword(Keywords.Key.SETS)
                        .sub_keyword(Other.GROUP_START)
                        .sub("grouping_set")
                            .description("{ <grouping_set> } [,...n]")
                            .required()
                            .list()
                            .ref(GroupingSetConverter.class)
                            .data(GroupBy.GroupingSetsItem::getGroupingSetItemList)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class TotalItemConverter
            implements ModelMetaBlockConverter<GroupBy.TotalItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.TotalItem>()
                        .keyword("()")
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class ColumnNameItemConverter
            implements ModelMetaBlockConverter<GroupBy.ColumnNameItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.ColumnNameItem>()
                        .sub("column-name")
                            .data(GroupBy.ColumnNameItem::getColumnName)
                            .and()
                        .sub()
                            .description("with DISTRIBUTED_AGG")
                            .optional(d -> !d.isUseWithDISTRIBUTED_AGG())
                            .sub_keyword(Keywords.WITH)
                            .sub_keyword(Other.GROUP_START)
                            .sub_keyword(Keywords.Key.DISTRIBUTED_AGG)
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class GroupByExpressionConverter
            implements ModelMetaBlockConverter<GroupBy.GroupByExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.GroupByExpression>()
                        .overall("group_by_expression")
                        .czse(d -> d.getColumnExpressionList().size() == 1,"column-expression")
                            .data(d -> d.getColumnExpressionList().get(0))
                            .and()
                        .czse(d -> d.getColumnExpressionList().size() > 1)
                            .description("( column-expression [ ,...n ] )")
                            .sub_keyword(Other.GROUP_START)
                            .sub("column-expression")
                                .list()
                                .data(GroupBy.GroupByExpression::getColumnExpressionList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class GroupingSetConverter
            implements ModelMetaBlockConverter<GroupBy.GroupingSet> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.GroupingSet>()
                        .overall("grouping_set")
                        .czse(GroupBy.GroupingSet::isUseTotal, "()")
                            .description("() --calculates the grand total")
                            .keyword("()")
                            .and()
                        .czse(d -> d.getGroupByExpressionList().size() == 1, "grouping_set_item")
                            .description("grouping_set_item")
                            .ref(GroupingSetItemConverter.class)
                            .data(d -> d.getGroupByExpressionList().get(0))
                            .and()
                        .czse(d -> d.getGroupByExpressionList().size() > 1)
                            .description("( <grouping_set_item> [ ,...n ] )")
                            .sub_keyword(Other.GROUP_START)
                            .sub("grouping_set_item")
                                .list()
                                .ref(GroupingSetItemConverter.class)
                                .data(GroupBy.GroupingSet::getGroupByExpressionList)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

    public static class GroupingSetItemConverter
            implements ModelMetaBlockConverter<GroupBy.GroupingSet.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,GroupBy.GroupingSet.Item>()
                        .overall("grouping_set_item")
                        .czse(d -> d instanceof GroupBy.GroupByExpression,"group_by_expression")
                            .and()
                        .czse_ref(d -> d instanceof GroupBy.RollupItem, RollupItemConverter.meta)
                        .czse_ref(d -> d instanceof GroupBy.CubeItem, CubeItemConverter.meta)
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
