package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Char;
import com.xy.xsql.tsql.model.functions.string.Difference;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DifferenceConverter
        implements ModelMetaBlockConverter<Difference> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Difference>()
                    .overall("DIFFERENCE")
                    .sub_keyword(Function.Keywords.DIFFERENCE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("character_expression")
                        .scope(d -> d.getCharacterExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("character_expression")
                        .scope(d -> d.getCharacterExpression2())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
