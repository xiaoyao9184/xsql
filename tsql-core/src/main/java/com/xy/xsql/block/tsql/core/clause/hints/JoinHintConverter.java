package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class JoinHintConverter
        implements BlockConverter<JoinHint> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,JoinHint> builder =
            new ReferenceBlockBuilder<Void,JoinHint>()
                    .overall("join_hint")
                    .required()
                    .oneOf()
                        .filter(d -> d.equals(JoinHint.LOOP))
                        .keyword(JoinHint.LOOP)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(JoinHint.HASH))
                        .keyword(JoinHint.HASH)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(JoinHint.MERGE))
                        .keyword(JoinHint.MERGE)
                        .and()
                    .oneOf()
                        .filter(d -> d.equals(JoinHint.REMOTE))
                        .keyword(JoinHint.REMOTE)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(JoinHint joinHint) {
        return builder
                .data(joinHint)
                .build();
    }

}
