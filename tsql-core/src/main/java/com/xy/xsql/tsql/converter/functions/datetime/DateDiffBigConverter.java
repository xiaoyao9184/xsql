package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.DateDiff;
import com.xy.xsql.tsql.model.functions.datetime.DateDiff_Big;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DateDiffBigConverter
        implements ModelMetaBlockConverter<DateDiff_Big> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DateDiff_Big>()
                    .overall("DATEDIFF_BIG")
                    .sub_keyword(Function.Keywords.DATEDIFF_BIG)
                    .sub_keyword(Other.GROUP_START)
                    .sub("datepart")
                        .scope(d -> d.getDatepart())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("startdate")
                        .scope(d -> d.getStartDate())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("startdate")
                        .scope(d -> d.getEndDate())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
