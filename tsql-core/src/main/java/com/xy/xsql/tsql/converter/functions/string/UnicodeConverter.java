package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Trim;
import com.xy.xsql.tsql.model.functions.string.Unicode;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class UnicodeConverter
        implements ModelMetaBlockConverter<Unicode> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Unicode>()
                    .overall("UNICODE")
                    .sub_keyword(Function.Keywords.UNICODE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'ncharacter_expression'")
                        .scope(d -> d.getNcharacterExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
