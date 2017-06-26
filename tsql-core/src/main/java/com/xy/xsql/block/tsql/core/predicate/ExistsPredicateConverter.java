package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.Exists;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExistsPredicateConverter
        implements ReferenceBlockConverter<Exists> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Exists> builder =
            new ReferenceBlockBuilder<Void,Exists>()
                    .overall("EXISTS")
                    .sub_keyword(Keywords.EXISTS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("subquery")
                        .data(Exists::getSubquery)
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Exists exists) {
        return builder
                .data(exists)
                .build();
    }
}
