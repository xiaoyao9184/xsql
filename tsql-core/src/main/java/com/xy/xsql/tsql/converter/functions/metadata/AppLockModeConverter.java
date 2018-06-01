package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datetime.DateAdd;
import com.xy.xsql.tsql.model.functions.metadata.AppLock_Mode;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AppLockModeConverter
        implements ModelMetaBlockConverter<AppLock_Mode> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AppLock_Mode>()
                    .overall("APPLOCK_MODE")
                    .sub_keyword(Function.Keywords.APPLOCK_MODE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'database_principal'")
                        .scope(d -> d.getDatabasePrincipal())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'resource_name'")
                        .scope(d -> d.getResourceName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'lock_owner'")
                        .scope(d -> d.getLockOwner())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
