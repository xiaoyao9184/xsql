package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class JoinHintConverter
        implements ReferenceBlockConverter<JoinHint> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,JoinHint> builder =
            new ReferenceBlockBuilder<Void,JoinHint>()
                    .overall("join_hint")
                    .required()
                    .czse(d -> d.equals(JoinHint.LOOP))
                        .keyword(JoinHint.LOOP)
                        .and()
                    .czse(d -> d.equals(JoinHint.HASH))
                        .keyword(JoinHint.HASH)
                        .and()
                    .czse(d -> d.equals(JoinHint.MERGE))
                        .keyword(JoinHint.MERGE)
                        .and()
                    .czse(d -> d.equals(JoinHint.REMOTE))
                        .keyword(JoinHint.REMOTE)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(JoinHint joinHint) {
        return builder
                .data(joinHint)
                .build();
    }

}
