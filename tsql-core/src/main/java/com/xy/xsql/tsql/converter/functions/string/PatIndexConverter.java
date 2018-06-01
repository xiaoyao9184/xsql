package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.Difference;
import com.xy.xsql.tsql.model.functions.string.PatIndex;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class PatIndexConverter
        implements ModelMetaBlockConverter<PatIndex> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,PatIndex>()
                    .overall("PATINDEX")
                    .sub_keyword(Function.Keywords.PATINDEX)
                    .sub_keyword(Other.GROUP_START)
                    .sub("'%pattern%'")
                        .scope(d -> d.getPattern())
                        .and()
                    .sub_keyword(Other.DELIMITER)
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
