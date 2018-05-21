package com.xy.xsql.tsql.converter.elements.expressions;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.elements.expressions.NullIf;

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
                            .scope(NullIf::getExpressionLeft)
                            .and()
                        .sub("expression")
                            .scope(NullIf::getExpressionRight)
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
