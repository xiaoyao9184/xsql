package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Compress;
import com.xy.xsql.tsql.model.functions.system.Session_Context;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SessionContextConverter
        implements ModelMetaBlockConverter<Session_Context> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Session_Context>()
                    .overall("SESSION_CONTEXT")
                    .sub_keyword(Function.Keywords.SESSION_CONTEXT)
                    .sub_keyword(Other.GROUP_START)
                    .sub("N'key'")
                        .scope(d -> d.getKey())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
