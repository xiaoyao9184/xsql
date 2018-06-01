package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Concat;
import com.xy.xsql.tsql.model.functions.string.Concat_Ws;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ConcatWsConverter
        implements ModelMetaBlockConverter<Concat_Ws> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Concat_Ws>()
                    .overall("CONCAT_WS")
                    .sub_keyword(Function.Keywords.CONCAT_WS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("separator")
                        .scope(d -> d.getSeparator())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("argument")
                        .list()
                        .scope(d -> d.getArgumentList())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
