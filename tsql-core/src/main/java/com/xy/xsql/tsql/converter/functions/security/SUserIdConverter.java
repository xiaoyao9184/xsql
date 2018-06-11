package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SUser_Id;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SUserIdConverter
        implements ModelMetaBlockConverter<SUser_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SUser_Id>()
                    .overall("SUSER_ID")
                    .sub_keyword(Function.Keywords.SUSER_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'login'")
                        .optional(d -> d.getLogin() == null)
                        .scope(d -> d.getLogin())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
