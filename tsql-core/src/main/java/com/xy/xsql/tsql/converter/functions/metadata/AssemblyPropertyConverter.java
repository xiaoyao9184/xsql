package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.AppLock_Test;
import com.xy.xsql.tsql.model.functions.metadata.AssemblyProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AssemblyPropertyConverter
        implements ModelMetaBlockConverter<AssemblyProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AssemblyProperty>()
                    .overall("ASSEMBLYPROPERTY")
                    .sub_keyword(Function.Keywords.ASSEMBLYPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'assembly_name'")
                        .scope(d -> d.getAssemblyName())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("'property_name'")
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
