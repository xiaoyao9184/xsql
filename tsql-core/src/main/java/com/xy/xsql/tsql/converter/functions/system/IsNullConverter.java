package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.Checksum;
import com.xy.xsql.tsql.model.functions.system.IsNull;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsNullConverter
        implements ModelMetaBlockConverter<IsNull> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IsNull>()
                    .overall("ISNULL")
                    .sub_keyword(Function.Keywords.ISNULL)
                    .sub_keyword(Other.GROUP_START)
                    .sub("check_expression")
                        .scope(d -> d.getCheckExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("replacement_value")
                        .scope(d -> d.getReplacementValue())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
