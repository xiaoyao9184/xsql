package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.ATn2;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ATan2Converter
        implements ModelMetaBlockConverter<ATn2> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ATn2>()
                    .overall("ATN2")
                    .sub_keyword(Function.Keywords.ATN2)
                    .sub_keyword(Other.GROUP_START)
                    .sub("float_expression")
                        .scope(d -> d.getFloatExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("float_expression2")
                        .scope(d -> d.getFloatExpression2())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
