package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Coalesce;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CoalesceConverter
        implements ModelMetaBlockConverter<Coalesce> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Coalesce>()
                    .overall("COALESCE")
                    .sub_keyword(Keywords.COALESCE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .list()
                        .data(Coalesce::getExpressionList)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
