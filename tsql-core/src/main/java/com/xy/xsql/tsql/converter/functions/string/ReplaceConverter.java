package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Difference;
import com.xy.xsql.tsql.model.functions.string.Replace;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ReplaceConverter
        implements ModelMetaBlockConverter<Replace> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Replace>()
                    .overall("REPLACE")
                    .sub_keyword(Function.Keywords.REPLACE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("string_expression")
                        .scope(d -> d.getStringExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("string_pattern")
                        .scope(d -> d.getStringPattern())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("string_replacement")
                        .scope(d -> d.getStringReplacement())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
