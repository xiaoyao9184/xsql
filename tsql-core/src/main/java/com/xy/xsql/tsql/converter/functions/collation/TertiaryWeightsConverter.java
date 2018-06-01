package com.xy.xsql.tsql.converter.functions.collation;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.collation.CollationProperty;
import com.xy.xsql.tsql.model.functions.collation.Tertiary_Weights;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TertiaryWeightsConverter
        implements ModelMetaBlockConverter<Tertiary_Weights> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Tertiary_Weights>()
                    .overall("TERTIARY_WEIGHTS")
                    .sub_keyword(Function.Keywords.TERTIARY_WEIGHTS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("non_Unicode_character_string_expression")
                        .scope(d -> d.getNonUnicodeCharacterStringExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
