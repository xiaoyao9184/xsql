package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;
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
                    .sub()
                        .list(ItemConverter.meta())
                        .data(OrderBy::getItems)
                        .more()
                        .startNewline()
                        .and()
                    .sub("offset_fetch")
                        .optional(d -> d.getOffsetFetch() == null)
                        .ref(OrderBy.OffsetFetch.class)
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
                        //TODO
    //                    .sub("COLLATE collation_name")
                        .sub()
                            .description("ASC | DESC")
                            .optional(d -> !(
                                    d.isUseAsc() ||
                                    d.isUseDesc()
                            ))
                            .oneOf()
                                .keyword(Keywords.ASC)
                                .and()
                            .oneOf()
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
                            .oneOf("integer_constant")
                                .filter(d -> d.getIntegerConstant() == null)
                                .data(OrderBy.OffsetFetch::getIntegerConstant)
                                .and()
                            .oneOf("offset_row_count_expression")
                                .filter(d -> d.getOffsetRowCountExpression() == null)
                                .data(OrderBy.OffsetFetch::getOffsetRowCountExpression)
                                .and()
                            .and()
                        .sub()
                            .description("{ ROW | ROWS }")
                            .required()
                            .oneOf()
                                .filter(OrderBy.OffsetFetch::isUseRows)
                                .keyword(Keywords.Key.ROW)
                                .and()
                            .oneOf()
                                .filter(d -> !d.isUseRows())
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
                                .oneOf()
                                    .filter(d -> !d.isUseFetchFirst())
                                    .keyword(Keywords.Key.FIRST)
                                    .and()
                                .oneOf()
                                    .filter(OrderBy.OffsetFetch::isUseFetchFirst)
                                    .keyword(Keywords.Key.NEXT)
                                    .and()
                                .and()
                            .sub()
                                .description("{integer_constant | fetch_row_count_expression }")
                                .required()
                                .oneOf("integer_constant")
                                    .filter(d -> d.getFetchIntegerConstant() == null)
                                    .data(OrderBy.OffsetFetch::getFetchIntegerConstant)
                                    .and()
                                .oneOf("fetch_row_count_expression")
                                    .filter(d -> d.getFetchOffsetRowCountExpression() == null)
                                    .data(OrderBy.OffsetFetch::getFetchOffsetRowCountExpression)
                                    .and()
                                .and()
                            .sub()
                                .description("{ ROW | ROWS }")
                                .required()
                                .oneOf()
                                    .filter(OrderBy.OffsetFetch::isUseFetchRows)
                                    .keyword(Keywords.Key.ROW)
                                    .and()
                                .oneOf()
                                    .filter(d -> !d.isUseFetchRows())
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
