package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Is_RoleMember;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class IsRoleMemberConverter
        implements ModelMetaBlockConverter<Is_RoleMember> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Is_RoleMember>()
                    .overall("IS_ROLEMEMBER")
                    .sub_keyword(Function.Keywords.IS_ROLEMEMBER)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'role'")
                        .scope(d-> d.getRole())
                        .and()
                    .sub()
                        .optional(d -> d.getDatabase_principal() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("'database_principal'")
                            .scope(d -> d.getDatabase_principal())
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
