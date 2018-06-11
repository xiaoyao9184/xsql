package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Permissions;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class PermissionsConverter
        implements ModelMetaBlockConverter<Permissions> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Permissions>()
                    .overall("PERMISSIONS")
                    .sub_keyword(Function.Keywords.PERMISSIONS)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .optional(d -> d.getObjectId() == null)
                        .sub("objectid")
                            .scope(d-> d.getObjectId())
                            .and()
                        .sub("'column'")
                            .optional(d -> d.getColumn() == null)
                            .scope(d-> d.getColumn())
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
