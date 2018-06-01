package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Stats_Date;
import com.xy.xsql.tsql.model.functions.metadata.Type_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TypeIdConverter
        implements ModelMetaBlockConverter<Type_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Type_Id>()
                    .overall("TYPE_ID")
                    .sub_keyword(Function.Keywords.TYPE_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub("schema_name")
                        .optional(d -> d.getSchemaName() == null)
                        .scope(d -> d.getSchemaName())
                        .and()
                    .sub("type_name")
                        .scope(d -> d.getTypeName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
