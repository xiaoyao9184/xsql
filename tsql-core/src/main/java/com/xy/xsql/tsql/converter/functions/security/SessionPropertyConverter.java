package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SessionProperty;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SessionPropertyConverter
        implements ModelMetaBlockConverter<SessionProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SessionProperty>()
                    .overall("SESSIONPROPERTY")
                    .sub_keyword(Function.Keywords.SESSIONPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("option")
                        .scope(d -> d.getOption())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
