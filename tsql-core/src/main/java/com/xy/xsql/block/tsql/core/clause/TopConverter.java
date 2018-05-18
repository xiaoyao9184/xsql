package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.queries.Top;
import com.xy.xsql.tsql.model.elements.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TopConverter
        implements ModelMetaBlockConverter<Top> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Top>()
                    .overall("TOP")
                    .sub_keyword(Keywords.TOP)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(Top::getExpression)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .optional(data -> !data.isUsePercent())
                        .keyword(Keywords.PERCENT)
                        .and()
                    .sub()
                        .optional(data -> !data.isUseTies())
                        .sub_keyword(Keywords.WITH)
                        .sub_keyword(Keywords.Key.TIES)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
