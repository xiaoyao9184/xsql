package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.system.IsNull;
import com.xy.xsql.tsql.model.functions.system.IsNumeric;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsNumericConverter
        implements ModelMetaBlockConverter<IsNumeric> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IsNumeric>()
                    .overall("ISNUMERIC")
                    .sub_keyword(Function.Keywords.ISNUMERIC)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
