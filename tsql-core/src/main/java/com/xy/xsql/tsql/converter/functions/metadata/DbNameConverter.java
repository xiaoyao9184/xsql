package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Db_Id;
import com.xy.xsql.tsql.model.functions.metadata.Db_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class DbNameConverter
        implements ModelMetaBlockConverter<Db_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Db_Name>()
                    .overall("DB_NAME")
                    .sub_keyword(Function.Keywords.DB_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("database_id")
                        .optional(d -> d.getDatabaseId() == null)
                        .scope(d -> d.getDatabaseId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
