package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Space;
import com.xy.xsql.tsql.model.functions.string.Str;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class StrConverter
        implements ModelMetaBlockConverter<Str> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Str>()
                    .overall("STR")
                    .sub_keyword(Function.Keywords.STR)
                    .sub_keyword(Other.GROUP_START)
                    .sub("float_expression")
                        .scope(d -> d.getFloatExpression())
                        .and()
                    .sub()
                        .optional(d -> d.getLength() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("length")
                            .scope(d -> d.getLength())
                            .and()
                        .sub()
                            .optional(d -> d.getDecimal() == null)
                            .sub_keyword(Other.DELIMITER)
                            .sub("decimal")
                                .scope(d -> d.getDecimal())
                                .and()
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
