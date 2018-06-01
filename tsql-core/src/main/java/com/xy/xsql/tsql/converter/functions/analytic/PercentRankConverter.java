package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.analytic.Percent_Rank;
import com.xy.xsql.tsql.model.functions.analytic.Percentile_Disc;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PercentRankConverter
        implements ModelMetaBlockConverter<Percent_Rank> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Percent_Rank>()
                    .overall("PERCENT_RANK")
                    .sub_keyword(Function.Keywords.PERCENT_RANK)
                    .sub_keyword(Other.GROUP_START)
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .sub_keyword(Keywords.OVER)
                        .sub_keyword(Other.GROUP_START)
                        .sub("partition_by_clause")
                            .optional(d -> d.getOver().getPartitionBy() == null)
                            .scope(d -> d.getOver().getPartitionBy())
                            .and()
                        .sub("order_by_clause")
                            .scope(d -> d.getOver().getOrderBy())
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
