package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Id;
import com.xy.xsql.tsql.model.functions.metadata.ServerProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ServerPropertyConverter
        implements ModelMetaBlockConverter<ServerProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ServerProperty>()
                    .overall("SERVERPROPERTY")
                    .sub_keyword(Function.Keywords.SERVERPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'propertyname'")
                        .scope(d -> d.getPropertyName())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


}
