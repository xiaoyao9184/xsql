package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.NullIf;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class NullIfConverter
        implements MetaContextBlockConverter<NullIf> {

    // @formatter:off
    private static BlockMetaBuilder<Void,NullIf> builder =
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
                        .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(NullIf context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
