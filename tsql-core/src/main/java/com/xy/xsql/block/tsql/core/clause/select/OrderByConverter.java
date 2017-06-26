package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.OrderBy;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OrderByConverter
        implements BlockConverter<OrderBy> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,OrderBy> builder =
            new ReferenceBlockBuilder<Void,OrderBy>()
                    .sub_keyword(Keywords.ORDER)
                    .sub_keyword(Keywords.BY)
                    .sub_list(ItemConverter.meta())
                        .data(OrderBy::getItems)
                        .startNewline()
                        .and()
                    .sub("offset_fetch")
                        .optional(d -> d.getOffsetFetch() == null)
                        .ref(OffsetFetchConverter.class)
                        .data(OrderBy::getOffsetFetch)
                        .startNewline()
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(OrderBy orderBy) {
        return builder
                .data(orderBy)
                .build();
    }


    public static class ItemConverter
            implements BlockConverter<OrderBy.Item> {

        // @formatter:off
    private static ReferenceBlockBuilder<Void,OrderBy.Item> builder =
                new ReferenceBlockBuilder<Void,OrderBy.Item>()
                        .sub("order_by_expression")
                            .data(OrderBy.Item::getOrderByExpression)
                            .and()
                        //TODO support COLLATE
    //                    .sub_meta("COLLATE collation_name")
                        .sub()
                            .description("ASC | DESC")
                            .optional(d -> !(
                                    d.isUseAsc() ||
                                    d.isUseDesc()
                            ))
                            .czse(d -> d.isUseAsc())
                                .keyword(Keywords.ASC)
                                .and()
                            .czse(d -> d.isUseDesc())
                                .keyword(Keywords.DESC)
                                .and()
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(OrderBy.Item item) {
            return builder
                    .data(item)
                    .build();
        }
    }


    public static class OffsetFetchConverter
            implements BlockConverter<OrderBy.OffsetFetch> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,OrderBy.OffsetFetch> builder =
                new ReferenceBlockBuilder<Void,OrderBy.OffsetFetch>()
                        .overall("offset_fetch")
                        .sub_keyword(Keywords.Key.OFFSET)
                        .sub()
                            .description("{ integer_constant | offset_row_count_expression }")
                            .required()
                            .czse(d -> d.getIntegerConstant() != null,"integer_constant")
                                .data(OrderBy.OffsetFetch::getIntegerConstant)
                                .and()
                            .czse(d -> d.getOffsetRowCountExpression() != null, "offset_row_count_expression")
                                .data(OrderBy.OffsetFetch::getOffsetRowCountExpression)
                                .and()
                            .and()
                        .sub()
                            .description("{ ROW | ROWS }")
                            .required()
                            .czse(OrderBy.OffsetFetch::isUseRows)
                                .keyword(Keywords.Key.ROW)
                                .and()
                            .czse(OrderBy.OffsetFetch::isUseRows)
                                .keyword(Keywords.Key.ROWS)
                                .and()
                            .and()
                        .sub()
                            .description("FETCH")
                            .optional(OrderBy.OffsetFetch::isUseFetch)
                            .sub_keyword(Keywords.FETCH)
                            .sub()
                                .description("{ FIRST | NEXT }")
                                .required()
                                .czse(OrderBy.OffsetFetch::isUseFetchFirst)
                                    .keyword(Keywords.Key.FIRST)
                                    .and()
                                .czse(d -> !d.isUseFetchFirst())
                                    .keyword(Keywords.Key.NEXT)
                                    .and()
                                .and()
                            .sub()
                                .description("{integer_constant | fetch_row_count_expression }")
                                .required()
                                .czse(d -> d.getFetchIntegerConstant() != null, "integer_constant")
                                    .data(OrderBy.OffsetFetch::getFetchIntegerConstant)
                                    .and()
                                .czse(d -> d.getFetchOffsetRowCountExpression() != null, "fetch_row_count_expression")
                                    .data(OrderBy.OffsetFetch::getFetchOffsetRowCountExpression)
                                    .and()
                                .and()
                            .sub()
                                .description("{ ROW | ROWS }")
                                .required()
                                .czse(OrderBy.OffsetFetch::isUseFetchRows)
                                    .keyword(Keywords.Key.ROW)
                                    .and()
                                .czse(d -> !d.isUseFetchRows())
                                    .keyword(Keywords.Key.ROWS)
                                    .and()
                                .and()
                            .sub_keyword(Keywords.Key.ONLY)
                            .startNewline()
                            .headFootTakeLine()
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public Block convert(OrderBy.OffsetFetch item) {
            return builder
                    .data(item)
                    .build();
        }
    }

}
