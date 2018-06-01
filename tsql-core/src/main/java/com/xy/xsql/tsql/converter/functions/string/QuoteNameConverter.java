package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.PatIndex;
import com.xy.xsql.tsql.model.functions.string.QuoteName;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class QuoteNameConverter
        implements ModelMetaBlockConverter<QuoteName> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,QuoteName>()
                    .overall("QUOTENAME")
                    .sub_keyword(Function.Keywords.QUOTENAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'character_string'")
                        .scope(d -> d.getCharacterString())
                        .and()
                    .sub()
                        .optional(d -> d.getQuoteCharacter() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("'quote_character'")
                            .scope(d -> d.getQuoteCharacter())
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
