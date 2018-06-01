package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.String_Split;
import com.xy.xsql.tsql.model.functions.string.Stuff;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class StuffConverter
        implements ModelMetaBlockConverter<Stuff> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Stuff>()
                    .overall("STUFF")
                    .sub_keyword(Function.Keywords.STUFF)
                    .sub_keyword(Other.GROUP_START)
                    .sub("character_expression")
                        .scope(d -> d.getCharacterExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("start")
                        .scope(d -> d.getStart())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("length")
                        .scope(d -> d.getLength())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("replaceWith_expression")
                        .scope(d -> d.getReplaceWithExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
