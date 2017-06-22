package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TopConverter
        implements BlockConverter<Top> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Top> builder =
            new ReferenceBlockBuilder<Void,Top>()
                    .overall("TOP")
                    .sub_keyword(Keywords.TOP)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .data(Top::getExpression)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub("PERCENT")
                        .optional(data -> !data.isUsePercent())
                        .keyword(Keywords.PERCENT)
                        .and()
                    .sub("WITH TIES")
                        .optional(data -> !data.isUseTies())
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Keywords.Key.TIES)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Top top) {
        return builder
                .data(top)
                .build();
    }

}
