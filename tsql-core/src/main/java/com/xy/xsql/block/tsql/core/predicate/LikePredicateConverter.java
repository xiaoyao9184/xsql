package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.predicate.Like;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LikePredicateConverter
        implements ReferenceBlockConverter<Like> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Like> builder =
            new ReferenceBlockBuilder<Void,Like>()
                    .overall("LIKE")
                    .sub("string_expression")
                        .data(Like::getExpression)
                        .and()
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Keywords.NOT)
                        .and()
                    .sub_keyword(Keywords.LIKE)
                    .sub("string_expression")
                        .data(Like::getLikeExpression)
                        .and()
                    .sub()
                        .description("[ ESCAPE 'escape_character' ]")
                        .optional(l -> l.getEscapeCharacter() == null)
                        .sub_keyword(Keywords.ESCAPE)
                        .sub("'escape_character'")
                            .data(Like::getEscapeCharacter)
                            .and()
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Like like) {
        return builder
                .data(like)
                .build();
    }
}
