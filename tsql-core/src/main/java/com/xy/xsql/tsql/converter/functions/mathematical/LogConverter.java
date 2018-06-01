package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.ATn2;
import com.xy.xsql.tsql.model.functions.mathematical.Log;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LogConverter
        implements ModelMetaBlockConverter<Log> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Log>()
                    .overall("LOG")
                    .sub_keyword(Function.Keywords.LOG)
                    .sub_keyword(Other.GROUP_START)
                    .sub("float_expression")
                        .scope(d -> d.getFloatExpression())
                        .and()
                    .sub()
                        .optional(d -> d.getBase() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("base")
                            .scope(d -> d.getBase())
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
