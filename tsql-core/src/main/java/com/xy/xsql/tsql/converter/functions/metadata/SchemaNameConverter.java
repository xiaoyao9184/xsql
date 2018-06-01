package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Id;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SchemaNameConverter
        implements ModelMetaBlockConverter<Schema_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Schema_Name>()
                    .overall("SCHEMA_NAME")
                    .sub_keyword(Function.Keywords.SCHEMA_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("schema_id")
                        .optional(d -> d.getSchemaId() == null)
                        .scope(d -> d.getSchemaId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
