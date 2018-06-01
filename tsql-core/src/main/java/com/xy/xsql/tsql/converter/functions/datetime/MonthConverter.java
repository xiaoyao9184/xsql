package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.IsDate;
import com.xy.xsql.tsql.model.functions.datetime.Month;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class MonthConverter
        implements ModelMetaBlockConverter<Month> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Month>()
                    .overall("MONTH")
                    .sub_keyword(Function.Keywords.MONTH)
                    .sub_keyword(Other.GROUP_START)
                    .sub("date")
                        .scope(d -> d.getDate())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
