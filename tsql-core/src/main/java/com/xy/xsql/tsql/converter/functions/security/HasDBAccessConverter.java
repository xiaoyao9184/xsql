package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.security.Certencoded;
import com.xy.xsql.tsql.model.functions.security.Has_DBAccess;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class HasDBAccessConverter
        implements ModelMetaBlockConverter<Has_DBAccess> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Has_DBAccess>()
                    .overall("HAS_DBACCESS")
                    .sub_keyword(Function.Keywords.HAS_DBACCESS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'database_name'")
                        .scope(d -> d.getDatabaseName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
