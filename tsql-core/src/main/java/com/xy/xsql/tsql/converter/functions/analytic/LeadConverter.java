package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.analytic.Lag;
import com.xy.xsql.tsql.model.functions.analytic.Lead;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LeadConverter
        implements ModelMetaBlockConverter<Lead> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Lead>()
                    .overall("LEAD")
                    .sub_keyword(Function.Keywords.LEAD)
                    .sub_keyword(Other.GROUP_START)
                    .sub("scalar_expression")
                        .optional(d -> d.getScalarExpression() == null)
                        .scope(d -> d.getScalarExpression())
                        .and()
                    .sub()
                        .optional(d -> d.getOffset() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("offset")
                            .scope(d -> d.getOffset())
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getDefaultValue() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("default")
                            .scope(d -> d.getDefaultValue())
                            .and()
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
