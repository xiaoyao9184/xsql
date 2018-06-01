package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.analytic.Cume_Dist;
import com.xy.xsql.tsql.model.functions.analytic.First_Value;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class FirstValueConverter
        implements ModelMetaBlockConverter<First_Value> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,First_Value>()
                    .overall("FIRST_VALUE")
                    .sub_keyword(Function.Keywords.FIRST_VALUE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("scalar_expression")
                        .optional(d -> d.getScalarExpression() == null)
                        .scope(d -> d.getScalarExpression())
                        .and()
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
                        .sub("rows_range_clause")
                            .optional(d -> d.getOver().getRowRange() == null)
                            .scope(d -> d.getOver().getRowRange())
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
