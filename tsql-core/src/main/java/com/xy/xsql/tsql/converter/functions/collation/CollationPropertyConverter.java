package com.xy.xsql.tsql.converter.functions.collation;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.analytic.Cume_Dist;
import com.xy.xsql.tsql.model.functions.collation.CollationProperty;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CollationPropertyConverter
        implements ModelMetaBlockConverter<CollationProperty> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,CollationProperty>()
                    .overall("COLLATIONPROPERTY")
                    .sub_keyword(Function.Keywords.COLLATIONPROPERTY)
                    .sub_keyword(Other.GROUP_START)
                    .sub("collation_name")
                        .scope(d -> c_string(d.getCollationName()))
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("property")
                        .scope(d -> c_string(d.getProperty()))
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
