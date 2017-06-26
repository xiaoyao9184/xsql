package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.NullIf;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class NullIfConverter
        implements ReferenceBlockConverter<NullIf> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,NullIf> builder =
            new ReferenceBlockBuilder<Void,NullIf>()
                    .overall("NULLIF")
                    .sub_keyword(Keywords.NULLIF)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .list()
                        .sub("expression")
                            .data(NullIf::getExpressionLeft)
                            .and()
                        .sub("expression")
                            .data(NullIf::getExpressionRight)
                            .and()
                        .and()
                        .sub_keyword(Other.GROUP_END);
    // @formatter:on


    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(NullIf nullIf) {
        return builder
                .data(nullIf)
                .build();
    }

}
