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
                        .format_indentation_right()
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
                            .description("ASC | DESC")
                            .optional(d -> !(
                                    d.isUseAsc() ||
                                    d.isUseDesc()
                            ))
                            .czse(OrderBy.Item::isUseAsc)
                                .keyword(Keywords.ASC)
                                .and()
                            .czse(OrderBy.Item::isUseDesc)
                                .keyword(Keywords.DESC)
                                .and()
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
                            .description("{ integer_constant | offset_row_count_expression }")
                            .style_required()
                            .czse(d -> d.getIntegerConstant() != null,"integer_constant")
                                .scope(OrderBy.OffsetFetch::getIntegerConstant)
                                .and()
                            .czse(d -> d.getOffsetRowCountExpression() != null, "offset_row_count_expression")
                                .scope(OrderBy.OffsetFetch::getOffsetRowCountExpression)
                                .and()
                            .and()
                        .sub()
                            .description("{ ROW | ROWS }")
                            .style_required()
                            .czse(d -> !d.isUseRows())
                                .keyword(Keywords.Key.ROW)
                                .and()
                            .czse(OrderBy.OffsetFetch::isUseRows)
                                .keyword(Keywords.Key.ROWS)
                                .and()
                            .and()
                        .sub()
                            .description("fetch")
                            .optional(d -> !d.isUseFetch())
                            .sub_keyword(Keywords.FETCH)
                            .sub()
                                .description("{ FIRST | NEXT }")
                                .style_required()
                                .czse(OrderBy.OffsetFetch::isUseFetchFirst)
                                    .keyword(Keywords.Key.FIRST)
                                    .and()
                                .czse(d -> !d.isUseFetchFirst())
                                    .keyword(Keywords.Key.NEXT)
                                    .and()
                                .and()
                            .sub()
                                .description("{integer_constant | fetch_row_count_expression }")
                                .style_required()
                                .czse(d -> d.getFetchIntegerConstant() != null, "integer_constant")
                                    .scope(OrderBy.OffsetFetch::getFetchIntegerConstant)
                                    .and()
                                .czse(d -> d.getFetchOffsetRowCountExpression() != null, "fetch_row_count_expression")
                                    .scope(OrderBy.OffsetFetch::getFetchOffsetRowCountExpression)
                                    .and()
                                .and()
                            .sub()
                                .description("{ ROW | ROWS }")
                                .style_required()
                                .czse(d -> !d.isUseFetchRows())
                                    .keyword(Keywords.Key.ROW)
                                    .and()
                                .czse(OrderBy.OffsetFetch::isUseFetchRows)
                                    .keyword(Keywords.Key.ROWS)
                                    .and()
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
