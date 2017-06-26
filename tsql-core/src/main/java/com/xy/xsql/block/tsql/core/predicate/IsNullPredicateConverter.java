package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.predicate.IsNull;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsNullPredicateConverter
        implements ReferenceBlockConverter<IsNull> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,IsNull> builder =
            new ReferenceBlockBuilder<Void,IsNull>()
                    .overall("IS NULL")
                    .sub("expression")
                        .data(IsNull::getExpression)
                        .and()
                    .sub_keyword(Keywords.IS)
                    .sub()
                        .keyword(Keywords.NOT)
                        .optional(IsNull::isUseNotOperator)
                        .and()
                    .sub_keyword(Keywords.NULL);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(IsNull isNull) {
        return builder
                .data(isNull)
                .build();
    }
}
