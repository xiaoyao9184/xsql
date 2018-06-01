package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.TimeFromParts;
import com.xy.xsql.tsql.model.functions.datetime.ToDatetimeOffset;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ToDatetimeOffsetConverter
        implements ModelMetaBlockConverter<ToDatetimeOffset> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ToDatetimeOffset>()
                    .overall("TODATETIMEOFFSET")
                    .sub_keyword(Function.Keywords.TODATETIMEOFFSET)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
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
