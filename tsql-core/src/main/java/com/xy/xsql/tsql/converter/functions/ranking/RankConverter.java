package com.xy.xsql.tsql.converter.functions.ranking;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.ranking.Ntile;
import com.xy.xsql.tsql.model.functions.ranking.Rank;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RankConverter
        implements ModelMetaBlockConverter<Rank> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Rank>()
                    .overall("RANK")
                    .sub_keyword(Function.Keywords.RANK)
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
