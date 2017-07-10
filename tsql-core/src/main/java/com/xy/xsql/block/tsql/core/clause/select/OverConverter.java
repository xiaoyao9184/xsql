package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Over;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OverConverter
        implements ReferenceBlockConverter<Over> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Over> builder =
            new BlockMetaBuilder<Void,Over>()
                    .overall("OVER")
                    .sub_keyword(Keywords.OVER)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .description("over param")
                        .sub("PARTITION BY clause")
                            .optional(d -> d.getPartitionBy() == null)
                            .ref(PartitionByConverter.class)
                            .data(Over::getPartitionBy)
                            .and()
                        .sub("ORDER BY clause")
                            .optional(d -> d.getOrderBy() == null)
                            .ref(OrderByConverter.class)
                            .data(Over::getOrderBy)
                            .and()
                        .sub("ROW or RANGE clause")
                            .optional(d -> d.getRowRange() == null)
                            .ref(RowRangeConverter.class)
                            .data(Over::getRowRange)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Over over) {
        return builder
                .data(over)
                .build();
    }



    public static class PartitionByConverter
            implements ReferenceBlockConverter<Over.PartitionBy> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.PartitionBy> builder =
                new BlockMetaBuilder<Void,Over.PartitionBy>()
                        .overall("PARTITION BY clause")
                        .sub_keyword(Keywords.Key.PARTITION)
                        .sub_keyword(Keywords.BY)
                        .sub("value_expression")
                            .description("value_expression , ... [ n ]")
                            .list()
                            .data(Over.PartitionBy::getValueExpressionList)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.PartitionBy partitionBy) {
            return builder
                    .data(partitionBy)
                    .build();
        }
    }

    public static class OrderByConverter
            implements ReferenceBlockConverter<Over.OrderBy> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.OrderBy> builder =
                new BlockMetaBuilder<Void,Over.OrderBy>()
                        .overall("ORDER BY clause")
                        .sub_keyword(Keywords.ORDER)
                        .sub_keyword(Keywords.BY)
                        .sub()
                            .description("order by list")
                            .list()
                            .ref(com.xy.xsql.block.tsql.core.clause.select.OrderByConverter.ItemConverter.meta())
//                            .sub_meta(com.xy.xsql.block.tsql.core.clause.select.OrderByConverter.ItemConverter.meta())
                            .data(Over.OrderBy::getItems)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.OrderBy orderBy) {
            return builder
                    .data(orderBy)
                    .build();
        }
    }



    public static class RowRangeConverter
            implements ReferenceBlockConverter<Over.RowRange> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.RowRange> builder =
                new BlockMetaBuilder<Void,Over.RowRange>()
                        .overall("ROW or RANGE clause")
                        .sub()
                            .description("row/range")
                            .required()
                            .czse_keyword(Over.RowRange::isUseRows, Keywords.Key.ROWS)
                            .czse_keyword(d -> !d.isUseRows(), Keywords.Key.RANGE)
                            .and()
                        .sub("window frame extent")
                            .ref(WindowFrameExtentConverter.class)
                            .data(Over.RowRange::getWindowFrameExtent)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.RowRange rowRange) {
            return builder
                    .data(rowRange)
                    .build();
        }
    }

    public static class WindowFrameExtentConverter
            implements ReferenceBlockConverter<Over.WindowFrameExtent> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.WindowFrameExtent> builder =
                new BlockMetaBuilder<Void,Over.WindowFrameExtent>()
                        .overall("window frame extent")
                        .required()
                        .czse(d -> d instanceof Over.WindowFramePreceding,"window frame preceding")
                            .ref(WindowFramePrecedingConverter.class)
                            .data(d -> d)
                            .and()
                        .czse(d -> d instanceof Over.WindowFrameBetween,"window frame between")
                            .ref(WindowFrameBetweenConverter.class)
                            .data(d -> d)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.WindowFrameExtent windowFrameExtent) {
            return builder
                    .data(windowFrameExtent)
                    .build();
        }
    }

    public static class WindowFrameBetweenConverter
            implements ReferenceBlockConverter<Over.WindowFrameBetween> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.WindowFrameBetween> builder =
                new BlockMetaBuilder<Void,Over.WindowFrameBetween>()
                        .overall("window frame between")
                        .sub_keyword(Keywords.BETWEEN)
                        .sub("window frame bound")
                            .ref(WindowFrameBoundConverter.class)
                            .data(Over.WindowFrameBetween::getBetweenBound)
                            .and()
                        .sub_keyword(Keywords.AND)
                        .sub("window frame bound")
                            .ref(WindowFrameBoundConverter.class)
                            .data(Over.WindowFrameBetween::getAndBound)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.WindowFrameBetween windowFrameBetween) {
            return builder
                    .data(windowFrameBetween)
                    .build();
        }
    }

    public static class WindowFrameBoundConverter
            implements ReferenceBlockConverter<Over.WindowFrameBound> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.WindowFrameBound> builder =
                new BlockMetaBuilder<Void,Over.WindowFrameBound>()
                        .overall("window frame bound")
                        .required()
                        .czse(d -> d instanceof Over.WindowFramePreceding,"window frame preceding")
                            .ref(WindowFramePrecedingConverter.class)
                            .data(d -> d)
                            .and()
                        .czse(d -> d instanceof Over.WindowFrameFollowing,"window frame following")
                            .ref(WindowFrameFollowingConverter.class)
                            .data(d -> d)
                            .and()
                        .subTakeLine()
                        .headFootTakeLine();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.WindowFrameBound windowFrameBound) {
            return builder
                    .data(windowFrameBound)
                    .build();
        }
    }

    public static class WindowFramePrecedingConverter
            implements ReferenceBlockConverter<Over.WindowFramePreceding> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.WindowFramePreceding> builder =
                new BlockMetaBuilder<Void,Over.WindowFramePreceding>()
                        .overall("window frame preceding")
                        .czse(Over.WindowFramePreceding::isUseUnbounded)
                            .description("unbounded")
                            .sub_keyword(Keywords.Key.UNBOUNDED)
                            .sub_keyword(Keywords.Key.PRECEDING)
                            .and()
                        .czse(d -> d.getUnsignedvaluespecification() != null)
                            .description("unsigned_value_specification PRECEDING")
                            .sub("unsigned_value_specification")
                                .ref(UnsignedValueSpecificationConverter.class)
                                .data(Over.WindowFramePreceding::getUnsignedvaluespecification)
                                .and()
                            .sub_keyword(Keywords.Key.PRECEDING)
                            .and()
                        .czse(Over.WindowFramePreceding::isUseCurrent)
                            .description("current row")
                            .sub_keyword(Keywords.CURRENT)
                            .sub_keyword(Keywords.Key.ROW)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.WindowFramePreceding windowFramePreceding) {
            return builder
                    .data(windowFramePreceding)
                    .build();
        }
    }

    public static class WindowFrameFollowingConverter
            implements ReferenceBlockConverter<Over.WindowFrameFollowing> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.WindowFrameFollowing> builder =
                new BlockMetaBuilder<Void,Over.WindowFrameFollowing>()
                        .overall("window frame following")
                        .czse(Over.WindowFrameFollowing::isUseUnbounded)
                            .description("unbounded")
                            .sub_keyword(Keywords.Key.UNBOUNDED)
                            .sub_keyword(Keywords.Key.FOLLOWING)
                            .and()
                        .czse(d -> d.getUnsignedvaluespecification() != null)
                            .description("unsigned_value_specification FOLLOWING")
                            .sub("unsigned_value_specification")
                                .ref(UnsignedValueSpecificationConverter.class)
                                .data(Over.WindowFrameFollowing::getUnsignedvaluespecification)
                                .and()
                            .sub_keyword(Keywords.Key.FOLLOWING)
                            .and()
                        .czse(Over.WindowFrameFollowing::isUseCurrent)
                            .description("current row")
                            .sub_keyword(Keywords.CURRENT)
                            .sub_keyword(Keywords.Key.ROW)
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.WindowFrameFollowing windowFrameFollowing) {
            return builder
                    .data(windowFrameFollowing)
                    .build();
        }
    }

    public static class UnsignedValueSpecificationConverter
            implements ReferenceBlockConverter<Over.UnsignedValueSpecification> {

        // @formatter:off
        private static BlockMetaBuilder<Void,Over.UnsignedValueSpecification> builder =
                new BlockMetaBuilder<Void,Over.UnsignedValueSpecification>()
                        .overall("unsigned value specification")
                        .required()
                        .sub("<unsigned integer literal>")
                            .data(d -> d.toNumberConstant().toString())
                            .and();
        // @formatter:on

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public BlockMeta convert(Over.UnsignedValueSpecification unsignedValueSpecification) {
            return builder
                    .data(unsignedValueSpecification)
                    .build();
        }
    }

}
