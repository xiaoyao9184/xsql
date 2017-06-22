package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Case;
import com.xy.xsql.tsql.model.expression.Coalesce;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CoalesceConverter
        implements BlockConverter<Coalesce> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Coalesce> builder =
            new ReferenceBlockBuilder<Void,Coalesce>()
                    .overall("COALESCE")
                    .sub_keyword(Keywords.COALESCE)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list("expression")
                        .data(Coalesce::getExpressionList)
                        .more()
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on


    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Coalesce coalesce) {
        return builder
                .data(coalesce)
                .build();
    }
}
