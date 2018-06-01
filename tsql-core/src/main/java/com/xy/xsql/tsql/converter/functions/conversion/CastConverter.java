package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.configuration.$$DbTs;
import com.xy.xsql.tsql.model.functions.conversion.Cast;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CastConverter
        implements ModelMetaBlockConverter<Cast> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Cast>()
                    .overall("CAST")
                    .sub_keyword(Function.Keywords.CAST)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Keywords.AS)
                    .sub("data_type")
                        .scope(d -> d.getDataType())
                        .and()
                    .sub()
                        .optional(d -> d.getLength() == null)
                        .sub_keyword(Other.GROUP_START)
                        .sub("length")
                            .scope(d -> d.getLength())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
