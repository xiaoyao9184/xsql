package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.ObjectProperty;
import com.xy.xsql.tsql.model.functions.metadata.ObjectPropertyEX;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ObjectPropertyEXConverter
        implements ModelMetaBlockConverter<ObjectPropertyEX> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ObjectPropertyEX>()
                    .overall("OBJECTPROPERTYEX")
                    .sub_keyword(Function.Keywords.OBJECTPROPERTYEX)
                    .sub_keyword(Other.GROUP_START)
                    .sub("id")
                        .scope(d -> d.getId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
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
