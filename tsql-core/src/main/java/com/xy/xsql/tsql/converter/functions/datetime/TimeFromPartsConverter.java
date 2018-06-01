package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.SmallDatetimeFromParts;
import com.xy.xsql.tsql.model.functions.datetime.TimeFromParts;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TimeFromPartsConverter
        implements ModelMetaBlockConverter<TimeFromParts> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,TimeFromParts>()
                    .overall("TIMEFROMPARTS")
                    .sub_keyword(Function.Keywords.TIMEFROMPARTS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("hour")
                        .scope(d -> d.getHour())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("minute")
                        .scope(d -> d.getMinute())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("seconds")
                        .scope(d -> d.getSeconds())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("fractions")
                        .scope(d -> d.getFractions())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("precision")
                        .scope(d -> d.getPrecision())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
