package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.DateName;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DatePartConverter
        implements ModelMetaBlockConverter<DatePart> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DatePart>()
                    .overall("DATEPART")
                    .sub_keyword(Function.Keywords.DATEPART)
                    .sub_keyword(Other.GROUP_START)
                    .sub("datepart")
                        .scope(d -> d.getDatepart())
                        .and()
                    .sub_keyword(Other.DELIMITER)
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
