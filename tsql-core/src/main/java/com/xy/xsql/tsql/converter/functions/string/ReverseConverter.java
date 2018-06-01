package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Replicate;
import com.xy.xsql.tsql.model.functions.string.Reverse;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ReverseConverter
        implements ModelMetaBlockConverter<Reverse> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Reverse>()
                    .overall("REVERSE")
                    .sub_keyword(Function.Keywords.REVERSE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("string_expression")
                        .scope(d -> d.getStringExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
