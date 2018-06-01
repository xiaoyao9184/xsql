package com.xy.xsql.tsql.converter.functions.analytic;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.aggregate.Avg;
import com.xy.xsql.tsql.model.functions.analytic.Cume_Dist;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CumeDistConverter
        implements ModelMetaBlockConverter<Cume_Dist> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Cume_Dist>()
                    .overall("CUME_DIST")
                    .sub_keyword(Function.Keywords.CUME_DIST)
                    .sub_keyword(Other.GROUP_START)
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .sub_keyword(Keywords.OVER)
                        .sub_keyword(Other.GROUP_START)
                        .sub("partition_by_clause")
                            .optional(d -> d.getPartitionBy() == null)
                            .scope(d -> d.getPartitionBy())
                            .and()
                        .sub("order_by_clause")
                            .scope(d -> d.getOrderBy())
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
