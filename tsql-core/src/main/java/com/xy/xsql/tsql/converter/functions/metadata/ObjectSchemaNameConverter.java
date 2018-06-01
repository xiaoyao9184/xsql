package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Object_Name;
import com.xy.xsql.tsql.model.functions.metadata.Object_Schema_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ObjectSchemaNameConverter
        implements ModelMetaBlockConverter<Object_Schema_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Object_Schema_Name>()
                    .overall("OBJECT_SCHEMA_NAME")
                    .sub_keyword(Function.Keywords.OBJECT_SCHEMA_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("object_id")
                        .scope(d -> d.getObjectId())
                        .and()
                    .sub()
                        .optional(d -> d.getDatabaseId() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("database_id")
                            .scope(d -> d.getDatabaseId())
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
