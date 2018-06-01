package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.Month;
import com.xy.xsql.tsql.model.functions.datetime.Year;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class YearConverter
        implements ModelMetaBlockConverter<Year> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Year>()
                    .overall("YEAR")
                    .sub_keyword(Function.Keywords.YEAR)
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
