package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.ATn2;
import com.xy.xsql.tsql.model.functions.mathematical.Power;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PowerConverter
        implements ModelMetaBlockConverter<Power> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Power>()
                    .overall("POWER")
                    .sub_keyword(Function.Keywords.POWER)
                    .sub_keyword(Other.GROUP_START)
                    .sub("float_expression")
                        .scope(d -> d.getFloatExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("y")
                        .scope(d -> d.getY())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
