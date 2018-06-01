package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.DateDiff_Big;
import com.xy.xsql.tsql.model.functions.datetime.DateFromParts;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DateFromPartsConverter
        implements ModelMetaBlockConverter<DateFromParts> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DateFromParts>()
                    .overall("DATEFROMPARTS")
                    .sub_keyword(Function.Keywords.DATEFROMPARTS)
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
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
