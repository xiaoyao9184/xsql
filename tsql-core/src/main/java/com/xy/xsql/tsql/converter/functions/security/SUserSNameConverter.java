package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.SUser_SName;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class SUserSNameConverter
        implements ModelMetaBlockConverter<SUser_SName> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,SUser_SName>()
                    .overall("SUSER_SNAME")
                    .sub_keyword(Function.Keywords.SUSER_SNAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("server_user_sid")
                        .optional(d -> d.getServerUserSId() == null)
                        .scope(d -> d.getServerUserSId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
