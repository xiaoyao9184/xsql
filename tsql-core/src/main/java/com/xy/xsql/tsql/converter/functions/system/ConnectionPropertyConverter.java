package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Compress;
import com.xy.xsql.tsql.model.functions.system.ConnectionProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ConnectionPropertyConverter
        implements ModelMetaBlockConverter<ConnectionProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ConnectionProperty>()
                    .overall("CONNECTIONPROPERTY")
                    .sub_keyword(Function.Keywords.CONNECTIONPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("property")
                        .scope(d -> d.getProperty())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
