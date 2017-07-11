package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.In;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class InPredicateConverter
        implements MetaContextBlockConverter<In> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,In>()
                    .overall("IN")
                    .sub("expression")
                        .data(In::getExpression)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Keywords.NOT)
                        .and()
                    .sub_keyword(Keywords.IN)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getSubquery() != null,"subquery")
                            .data(In::getSubquery)
                            .and()
                        .czse(d -> d.getExpressionList() != null,"expression")
                            .list()
                            .required()
                            .data(In::getExpressionList)
                            .and()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(In context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
