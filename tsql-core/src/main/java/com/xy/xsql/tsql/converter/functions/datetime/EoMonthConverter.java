package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.Day;
import com.xy.xsql.tsql.model.functions.datetime.EoMonth;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class EoMonthConverter
        implements ModelMetaBlockConverter<EoMonth> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,EoMonth>()
                    .overall("EOMONTH")
                    .sub_keyword(Function.Keywords.EOMONTH)
                    .sub_keyword(Other.GROUP_START)
                    .sub("start_date")
                        .scope(d -> d.getStartDate())
                        .and()
                    .sub()
                        .optional(d -> d.getMonthToAdd() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("month_to_add")
                            .scope(d -> d.getMonthToAdd())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
