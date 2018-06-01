package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.String_Escape;
import com.xy.xsql.tsql.model.functions.string.String_Split;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class StringSplitConverter
        implements ModelMetaBlockConverter<String_Split> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,String_Split>()
                    .overall("STRING_SPLIT")
                    .sub_keyword(Function.Keywords.STRING_SPLIT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("string")
                        .scope(d -> d.getString())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("separator")
                        .scope(d -> d.getSeparator())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
