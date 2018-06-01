package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ServerProperty;
import com.xy.xsql.tsql.model.functions.metadata.Stats_Date;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class StatsDateConverter
        implements ModelMetaBlockConverter<Stats_Date> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Stats_Date>()
                    .overall("STATS_DATE")
                    .sub_keyword(Function.Keywords.STATS_DATE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("object_id")
                        .scope(d -> d.getObjectId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("stats_id")
                        .scope(d -> d.getStatsId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
