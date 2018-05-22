package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.Keymarks;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class StringConstantConverter
            implements ModelMetaBlockConverter<StringConstant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,StringConstant>()
                    .name("string")
                    .description("string constants")
                    .sub()
                        .optional(c -> !c.isUseNQuote())
                        .keyword(Keymarks.N_identifier)
                        .and()
                    .sub_keyword(Keymarks.quotation)
                    .sub()
                        .scope(c -> c.getString())
                        .and()
                    .sub_keyword(Keymarks.quotation)
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
