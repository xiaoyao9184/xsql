package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Top;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TopConverter
        implements MetaContextBlockConverter<Top> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Top> builder =
            new BlockMetaBuilder<Void,Top>()
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

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(Top context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
