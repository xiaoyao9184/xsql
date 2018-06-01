package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import com.xy.xsql.tsql.model.functions.conversion.Try_Cast;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TryCastConverter
        implements ModelMetaBlockConverter<Try_Cast> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Try_Cast>()
                    .overall("TRY_CAST")
                    .sub_keyword(Function.Keywords.TRY_CAST)
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
