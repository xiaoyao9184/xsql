package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.datatypes.constants.BinaryConstantBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.Keymarks;
import com.xy.xsql.tsql.model.datatypes.constants.UniqueidentifierConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class UniqueidentifierConstantConverter
            implements ModelMetaBlockConverter<UniqueidentifierConstant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,UniqueidentifierConstant>()
                    .name("uniqueidentifier")
                    .description("uniqueidentifier constants")
                    .czse(UniqueidentifierConstant::isUseBinaryFormat)
                        .sub_keyword(Keymarks.quotation)
                        .sub()
                            .scope(constant -> constant.getUuid().toString().toUpperCase())
                            .and()
                        .sub_keyword(Keymarks.quotation)
                        .and()
                    .czse(c -> true)
                        .scope(constant ->
                                new BinaryConstantBuilder()
                                        .withData(constant.getUuid())
                                        .build()
                        )
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
