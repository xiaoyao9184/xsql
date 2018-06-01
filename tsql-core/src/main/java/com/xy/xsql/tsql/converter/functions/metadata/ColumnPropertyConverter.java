package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.metadata.Col_Name;
import com.xy.xsql.tsql.model.functions.metadata.ColumnProperty;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ColumnPropertyConverter
        implements ModelMetaBlockConverter<ColumnProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,ColumnProperty>()
                    .overall("COLUMNPROPERTY")
                    .sub_keyword(Function.Keywords.COLUMNPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("id")
                        .scope(d -> d.getId())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("column")
                        .scope(d -> d.getColumn())
                        .and()
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
