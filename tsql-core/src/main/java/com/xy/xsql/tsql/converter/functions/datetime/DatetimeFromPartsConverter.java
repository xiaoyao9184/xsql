package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.Datetime2FromParts;
import com.xy.xsql.tsql.model.functions.datetime.DatetimeFromParts;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DatetimeFromPartsConverter
        implements ModelMetaBlockConverter<DatetimeFromParts> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DatetimeFromParts>()
                    .overall("DATETIMEFROMPARTS")
                    .sub_keyword(Function.Keywords.DATETIMEFROMPARTS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("year")
                        .scope(d -> d.getYear())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("month")
                        .scope(d -> d.getMonth())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("day")
                        .scope(d -> d.getDay())
                        .and()
                    .sub_keyword(Other.DELIMITER)
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
                    .sub("milliseconds")
                        .scope(d -> d.getMilliseconds())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
