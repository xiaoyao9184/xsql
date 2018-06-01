package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ParseName;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SchemaIdConverter
        implements ModelMetaBlockConverter<Schema_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Schema_Id>()
                    .overall("SCHEMA_ID")
                    .sub_keyword(Function.Keywords.SCHEMA_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("schema_name")
                        .optional(d -> d.getSchemaName() == null)
                        .scope(d -> d.getSchemaName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
