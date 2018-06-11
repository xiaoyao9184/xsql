package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.User_Name;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class UserNameConverter
        implements ModelMetaBlockConverter<User_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,User_Name>()
                    .overall("USER_NAME")
                    .sub_keyword(Function.Keywords.USER_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("id")
                        .optional(d -> d.getId() == null)
                        .scope(d -> d.getId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
