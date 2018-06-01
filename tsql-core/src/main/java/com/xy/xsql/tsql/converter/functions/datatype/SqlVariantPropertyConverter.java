package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.datatype.Identity;
import com.xy.xsql.tsql.model.functions.datatype.Sql_Variant_Property;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class SqlVariantPropertyConverter
        implements ModelMetaBlockConverter<Sql_Variant_Property> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Sql_Variant_Property>()
                    .overall("SQL_VARIANT_PROPERTY")
                    .sub_keyword(Function.Keywords.SQL_VARIANT_PROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
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
