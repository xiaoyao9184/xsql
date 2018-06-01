package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.Current_Timestamp;
import com.xy.xsql.tsql.model.functions.datetime.DateAdd;
import com.xy.xsql.tsql.model.functions.logical.Choose;

import static com.xy.xsql.tsql.converter.functions.FunctionConverters.onlyName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DateAddConverter
        implements ModelMetaBlockConverter<DateAdd> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,DateAdd>()
                    .overall("DATEADD")
                    .sub_keyword(Function.Keywords.DATEADD)
                    .sub_keyword(Other.GROUP_START)
                    .sub("datepart")
                        .scope(d -> d.getDatepart())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("number")
                        .scope(d -> d.getNumber())
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
