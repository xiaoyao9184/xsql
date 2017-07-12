package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.NullIf;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class NullIfConverter
        implements ModelMetaBlockConverter<NullIf> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,NullIf>()
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
                        .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
