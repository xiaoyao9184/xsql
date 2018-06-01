package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import com.xy.xsql.tsql.model.functions.conversion.Try_Convert;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class TryConvertConverter
        implements ModelMetaBlockConverter<Try_Convert> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Try_Convert>()
                    .overall("TRY_CONVERT")
                    .sub_keyword(Function.Keywords.TRY_CONVERT)
                    .sub_keyword(Other.GROUP_START)
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
                    .sub_keyword(Other.DELIMITER)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub()
                        .optional(d -> d.getStyle() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("style")
                            .scope(d -> d.getStyle())
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
