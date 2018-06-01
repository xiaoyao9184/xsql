package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ColumnProperty;
import com.xy.xsql.tsql.model.functions.metadata.Database_Principal_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DatabasePrincipalIdConverter
        implements ModelMetaBlockConverter<Database_Principal_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Database_Principal_Id>()
                    .overall("DATABASE_PRINCIPAL_ID")
                    .sub_keyword(Function.Keywords.DATABASE_PRINCIPAL_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'principal_name'")
                        .scope(d -> d.getPrincipalName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
