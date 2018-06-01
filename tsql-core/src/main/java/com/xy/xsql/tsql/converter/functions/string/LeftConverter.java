package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Difference;
import com.xy.xsql.tsql.model.functions.string.Left;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LeftConverter
        implements ModelMetaBlockConverter<Left> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Left>()
                    .overall("LEFT")
                    .sub_keyword(Function.Keywords.LEFT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("character_expression")
                        .scope(d -> d.getCharacterExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("integer_expression")
                        .scope(d -> d.getIntegerExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
