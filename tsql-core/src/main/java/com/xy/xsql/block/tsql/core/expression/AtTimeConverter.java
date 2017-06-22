package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.AtTimeZone;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AtTimeConverter
        implements BlockConverter<AtTimeZone> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,AtTimeZone> builder =
            new ReferenceBlockBuilder<Void,AtTimeZone>()
                    .overall("AT TIME ZONE")
                    .sub("inputdate")
                        .data(AtTimeZone::getInputExpression)
                        .and()
                    .sub_keyword(Keywords.Key.AT)
                    .sub_keyword(Keywords.Key.TIME)
                    .sub_keyword(Keywords.Key.ZONE)
                    .sub("timezone")
                        .data(AtTimeZone::getTimezone)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(AtTimeZone atTimeZone) {
        return builder
                .data(atTimeZone)
                .build();
    }

}
