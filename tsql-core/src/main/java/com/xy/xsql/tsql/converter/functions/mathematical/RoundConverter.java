package com.xy.xsql.tsql.converter.functions.mathematical;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.mathematical.Rand;
import com.xy.xsql.tsql.model.functions.mathematical.Round;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class RoundConverter
        implements ModelMetaBlockConverter<Round> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Round>()
                    .overall("ROUND")
                    .sub_keyword(Function.Keywords.ROUND)
                    .sub_keyword(Other.GROUP_START)
                    .sub("numeric_expression")
                        .scope(d -> d.getNumericExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("length")
                        .scope(d -> d.getLength())
                        .and()
                    .sub()
                        .optional(d -> d.getFunction() == null)
                        .sub_keyword(Other.DELIMITER)
                        .sub("function")
                            .scope(d -> d.getFunction())
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
