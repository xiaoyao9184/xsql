package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.predicate.IsNull;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsNullPredicateConverter
        implements ModelMetaBlockConverter<IsNull> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IsNull>()
                    .overall("IS NULL")
                    .sub("expression")
                        .scope(IsNull::getExpression)
                        .and()
                    .sub_keyword(Keywords.IS)
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Keywords.NOT)
                        .and()
                    .sub_keyword(Keywords.NULL)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
