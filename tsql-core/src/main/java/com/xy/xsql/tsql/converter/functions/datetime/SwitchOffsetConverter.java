package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.Month;
import com.xy.xsql.tsql.model.functions.datetime.SwitchOffset;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SwitchOffsetConverter
        implements ModelMetaBlockConverter<SwitchOffset> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SwitchOffset>()
                    .overall("SWITCHOFFSET")
                    .sub_keyword(Function.Keywords.SWITCHOFFSET)
                    .sub_keyword(Other.GROUP_START)
                    .sub("DATETIMEOFFSET")
                        .scope(d -> d.getDatetimeOffset())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("time_zone")
                        .scope(d -> d.getTimeZone())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
