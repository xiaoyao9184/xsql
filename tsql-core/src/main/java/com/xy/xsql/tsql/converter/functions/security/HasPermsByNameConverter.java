package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Has_Perms_By_Name;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class HasPermsByNameConverter
        implements ModelMetaBlockConverter<Has_Perms_By_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Has_Perms_By_Name>()
                    .overall("HAS_PERMS_BY_NAME")
                    .sub_keyword(Function.Keywords.HAS_PERMS_BY_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("securable")
                        .scope(d -> d.getSecurable())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("securable_class")
                        .scope(d -> d.getSecurableClass())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("permission")
                        .scope(d -> d.getPermission())
                        .and()
                    .sub()
                        .optional(d -> d.getSubSecurable() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("sub-securable")
                            .scope(d -> d.getSubSecurable())
                            .and()
                        .and()
                    .sub()
                        .optional(d -> d.getSubSecurableClass() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("sub-securable_class")
                            .scope(d -> d.getSubSecurableClass())
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
