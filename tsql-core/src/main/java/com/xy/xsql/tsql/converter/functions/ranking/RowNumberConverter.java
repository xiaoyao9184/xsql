package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.ranking.Rank;
import com.xy.xsql.tsql.model.functions.ranking.Row_Number;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RowNumberConverter
        implements ModelMetaBlockConverter<Row_Number> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Row_Number>()
                    .overall("ROW_NUMBER")
                    .sub_keyword(Function.Keywords.ROW_NUMBER)
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
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
