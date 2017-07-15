package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.OrderBy;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OrderByConverter
        implements ModelMetaBlockConverter<OrderBy> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,OrderBy>()
                    .overall("ORDER BY Clause")
                    .sub_keyword(Keywords.ORDER)
                    .sub_keyword(Keywords.BY)
                    .sub_list(ItemConverter.meta)
                        .description("order by's item list")
                        .scope(OrderBy::getItems)
                        .style_start_new_line()
                        .format_indentation_right()
                        .and()
                    .sub("offset_fetch")
                        .optional(d -> d.getOffsetFetch() == null)
                        .ref(OffsetFetchConverter.class)
                        .scope(OrderBy::getOffsetFetch)
                        .style_start_new_line()
                        .format_line()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class ItemConverter
            implements ModelMetaBlockConverter<OrderBy.Item> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,OrderBy.Item>()
                        .description("order by's item")
                        .sub("order_by_expression")
                            .scope(OrderBy.Item::getOrderByExpression)
                            .and()
                        //TODO support COLLATE
    //                    .sub_meta("COLLATE collation_name")
                        .sub()
                            .description("ase/desc")
                            .optional(d -> !(
                                    d.isUseAsc() ||
                                    d.isUseDesc()
                            ))
                            .czse_keyword(OrderBy.Item::isUseAsc,Keywords.ASC)
                            .czse_keyword(OrderBy.Item::isUseDesc,Keywords.DESC)
                            .and()
                        .format_line()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class OffsetFetchConverter
            implements ModelMetaBlockConverter<OrderBy.OffsetFetch> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,OrderBy.OffsetFetch>()
                        .overall("offset_fetch")
                        .sub_keyword(Keywords.Key.OFFSET)
                        .sub()
                            .description("integer_constant/offset_row_count_expression")
                            .czse(d -> d.getIntegerConstant() != null,"integer_constant")
                                .scope(OrderBy.OffsetFetch::getIntegerConstant)
                                .and()
                            .czse(d -> d.getOffsetRowCountExpression() != null, "offset_row_count_expression")
                                .scope(OrderBy.OffsetFetch::getOffsetRowCountExpression)
                                .and()
                            .style_required()
                            .and()
                        .sub()
                            .description("row/rows")
                            .style_required()
                            .czse_keyword(d -> !d.isUseRows(), Keywords.Key.ROW)
                            .czse_keyword(OrderBy.OffsetFetch::isUseRows, Keywords.Key.ROWS)
                            .and()
                        .sub()
                            .description("fetch")
                            .optional(d -> !d.isUseFetch())
                            .sub_keyword(Keywords.FETCH)
                            .sub()
                                .description("first/next")
                                .style_required()
                                .czse_keyword(OrderBy.OffsetFetch::isUseFetchFirst, Keywords.Key.FIRST)
                                .czse_keyword(d -> !d.isUseFetchFirst(), Keywords.Key.NEXT)
                                .and()
                            .sub()
                                .description("integer_constant/fetch_row_count_expression")
                                .style_required()
                                .czse(d -> d.getFetchIntegerConstant() != null, "integer_constant")
                                    .scope(OrderBy.OffsetFetch::getFetchIntegerConstant)
                                    .and()
                                .czse(d -> d.getFetchOffsetRowCountExpression() != null, "fetch_row_count_expression")
                                    .scope(OrderBy.OffsetFetch::getFetchOffsetRowCountExpression)
                                    .and()
                                .and()
                            .sub()
                                .description("row/rows")
                                .style_required()
                                .czse_keyword(d -> !d.isUseFetchRows(), Keywords.Key.ROW)
                                .czse_keyword(OrderBy.OffsetFetch::isUseFetchRows, Keywords.Key.ROWS)
                                .and()
                            .sub_keyword(Keywords.Key.ONLY)
                            .style_start_new_line()
                            .style_convention_line_delimiter()
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
