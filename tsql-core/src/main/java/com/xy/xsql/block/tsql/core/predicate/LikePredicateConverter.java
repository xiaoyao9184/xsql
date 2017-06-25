package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.predicate.Like;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LikePredicateConverter
        implements BlockConverter<Like> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Like> builder =
            new ReferenceBlockBuilder<Void,Like>()
                    .overall("LIKE")
                .sub("string_expression")
                    .data(Like::getExpression)
                    .and()
                .sub()
                    .keyword(Keywords.NOT)
                    .optional(Like::isUseNotOperator)
                    .and()
                .sub_keyword(Keywords.LIKE)
                .sub("string_expression")
                    .data(Like::getLikeExpression)
                    .and()
                .sub()
                    .description("[ ESCAPE 'escape_character' ]")
                    .optional(l -> l.getEscapeCharacter() != null)
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
    public Block convert(Like like) {
        return builder
                .data(like)
                .build();
    }
}
