package com.xy.xsql.tsql.converter.datatypes.constants;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.datatypes.constants.BitConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BitConstantConverter
            implements ModelMetaBlockConverter<BitConstant> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,BitConstant>()
                    .name("bit")
                    .description("bit constants")
                    .scope(c -> c.getaByte().toString())
                    .sub_format_empty_delimiter()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }
}
