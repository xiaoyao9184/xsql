package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Coalesce;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CoalesceConverter
        implements MetaContextBlockConverter<Coalesce> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Coalesce> builder =
            new BlockMetaBuilder<Void,Coalesce>()
                    .overall("COALESCE")
                    .sub_keyword(Keywords.COALESCE)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .list()
                        .data(Coalesce::getExpressionList)
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(Coalesce context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
