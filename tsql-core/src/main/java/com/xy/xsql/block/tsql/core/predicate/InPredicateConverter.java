package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.In;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class InPredicateConverter
        implements ModelMetaBlockConverter<In> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,In>()
                    .overall("IN")
                    .sub("expression")
                        .scope(In::getExpression)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Keywords.NOT)
                        .and()
                    .sub_keyword(Keywords.IN)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .czse(d -> d.getSubquery() != null,"subquery")
                            .scope(In::getSubquery)
                            .format_indentation_right()
                            .and()
                        .czse(d -> d.getExpressionList() != null,"expression")
                            .list()
                            .syntax_required()
                            .scope(In::getExpressionList)
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
