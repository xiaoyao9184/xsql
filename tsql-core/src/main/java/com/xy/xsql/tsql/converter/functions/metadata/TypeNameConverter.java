package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Type_Id;
import com.xy.xsql.tsql.model.functions.metadata.Type_Name;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TypeNameConverter
        implements ModelMetaBlockConverter<Type_Name> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Type_Name>()
                    .overall("TYPE_NAME")
                    .sub_keyword(Function.Keywords.TYPE_NAME)
                    .sub_keyword(Other.GROUP_START)
                    .sub("type_id")
                        .scope(d -> d.getTypeId())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
