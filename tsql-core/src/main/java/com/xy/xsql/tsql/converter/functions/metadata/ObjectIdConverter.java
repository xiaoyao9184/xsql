package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.MultipartNamesConverter;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Object_Definition;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ObjectIdConverter
        implements ModelMetaBlockConverter<Object_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Object_Id>()
                    .overall("OBJECT_ID")
                    .sub_keyword(Function.Keywords.OBJECT_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("[ database_name . [ schema_name ] . | schema_name . ] object_name")
                        .scope(d -> d.getObjectName())
                        .and()
                    .sub()
                        .optional(d -> d.getObjectType() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("'object_type'")
                            .scope(d -> d.getObjectType())
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
