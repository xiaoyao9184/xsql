package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.analytic.Percentile_Cont;
import com.xy.xsql.tsql.model.functions.analytic.Percentile_Disc;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PercentileDiscConverter
        implements ModelMetaBlockConverter<Percentile_Disc> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Percentile_Disc>()
                    .overall("PERCENTILE_DISC")
                    .sub_keyword(Function.Keywords.PERCENTILE_DISC)
                    .sub_keyword(Other.GROUP_START)
                    .sub("numeric_literal")
                        .scope(d -> d.getNumericLiteral())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .sub_keyword(Keywords.Key.WITHIN)
                        .sub_keyword(Keywords.GROUP)
                        .sub_keyword(Other.GROUP_START)
                        .sub_keyword(Keywords.ORDER)
                        .sub_keyword(Keywords.BY)
                        .sub("order_by_expression")
                            .scope(d -> d.getOrderBy().getOrderByExpression())
                            .and()
                        .sub()
                            .optional(d -> !d.getOrderBy().isUseAsc() && !d.getOrderBy().isUseDesc())
                            .czse_keyword(d -> d.getOrderBy().isUseAsc(), Keywords.ASC)
                            .czse_keyword(d -> d.getOrderBy().isUseDesc(), Keywords.DESC)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_indentation_right()
                        .and()
                    .sub()
                        .sub_keyword(Keywords.OVER)
                        .sub_keyword(Other.GROUP_START)
                        .sub("partition_by_clause")
                            .optional(d -> d.getOver().getPartitionBy() == null)
                            .scope(d -> d.getOver().getPartitionBy())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
