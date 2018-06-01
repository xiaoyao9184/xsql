package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.DateAdd;
import com.xy.xsql.tsql.model.functions.datetime.IsDate;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsDateConverter
        implements ModelMetaBlockConverter<IsDate> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IsDate>()
                    .overall("ISDATE")
                    .sub_keyword(Function.Keywords.ISDATE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
