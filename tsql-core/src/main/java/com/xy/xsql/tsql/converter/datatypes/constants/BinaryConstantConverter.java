package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BinaryConstantConverter
            implements ModelMetaBlockConverter<BinaryConstant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,BinaryConstant>()
                    .name("binary")
                    .description("binary constants")
                    .sub()
                        .scope(c -> "0x")
                        .and()
                    .sub()
                        .optional(c -> c.getData() == null || c.getData().length == 0)
                        .scope(c -> javax.xml.bind.DatatypeConverter.printHexBinary(c.getData()))
                        .and()
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
