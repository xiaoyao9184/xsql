package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Over;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OverConverter
        implements ReferenceBlockConverter<Over> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Over> builder =
            new ReferenceBlockBuilder<Void,Over>()
                    .overall("OVER")
                    .sub_keyword(Keywords.OVER)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .sub("PARTITION BY clause")
                            .ref(PartitionByConverter.class)
                            .data(Over::getPartitionBy)
                            .and()
                        .sub("ORDER BY clause")
                            .ref(OrderByConverter.class)
                            .data(Over::getOrderBy)
                            .and()
    //                    .sub_meta("<ROW or RANGE clause>")
    //                        .data(d -> d.getPartitionBy())
    //                        .and()
                        .subTakeLine()
                        .headFootTakeLine()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Over over) {
        return builder
                .data(over)
                .build();
    }



    public static class PartitionByConverter
            implements ReferenceBlockConverter<Over.PartitionBy> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Over.PartitionBy> builder =
                new ReferenceBlockBuilder<Void,Over.PartitionBy>()
                        .overall("PARTITION BY clause")
                        .sub_keyword(Keywords.Key.PARTITION)
                        .sub_keyword(Keywords.BY)
                        .sub()
                            .description("value_expression , ... [ n ]")
                            .list("value_expression")
                            .data(Over.PartitionBy::getValueExpressionList)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Over.PartitionBy partitionBy) {
            return builder
                    .data(partitionBy)
                    .build();
        }
    }

    public static class OrderByConverter
            implements ReferenceBlockConverter<Over.OrderBy> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Over.OrderBy> builder =
                new ReferenceBlockBuilder<Void,Over.OrderBy>()
                        .overall("ORDER BY clause")
                        .sub_keyword(Keywords.ORDER)
                        .sub_keyword(Keywords.BY)
                        .sub()
                            .list()
                            .sub_meta(com.xy.xsql.block.tsql.core.clause.select.OrderByConverter.ItemConverter.meta())
                            .data(Over.OrderBy::getItems)
                            .and();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Over.OrderBy orderBy) {
            return builder
                    .data(orderBy)
                    .build();
        }
    }

}
