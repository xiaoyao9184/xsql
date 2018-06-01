package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Left;
import com.xy.xsql.tsql.model.functions.string.Replicate;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ReplicateConverter
        implements ModelMetaBlockConverter<Replicate> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Replicate>()
                    .overall("REPLICATE")
                    .sub_keyword(Function.Keywords.REPLICATE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("string_expression")
                        .scope(d -> d.getStringExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("integer_expression")
                        .scope(d -> d.getIntegerExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
