package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.predicate.Like;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class LikePredicateConverter
        implements ModelMetaBlockConverter<Like> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Like>()
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
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
