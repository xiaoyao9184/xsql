package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.datatypes.MultipartNamesConverter;
import com.xy.xsql.tsql.converter.queries.select.OverConverter;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.NextValueFor;
import com.xy.xsql.tsql.model.functions.metadata.Object_Definition;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ObjectDefinitionConverter
        implements ModelMetaBlockConverter<Object_Definition> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Object_Definition>()
                    .overall("OBJECT_DEFINITION")
                    .sub_keyword(Function.Keywords.OBJECT_DEFINITION)
                    .sub_keyword(Other.GROUP_START)
                    .sub("object_id")
                        .scope(d -> d.getObjectId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
