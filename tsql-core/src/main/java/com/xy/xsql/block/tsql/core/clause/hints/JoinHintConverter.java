package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class JoinHintConverter
        implements MetaContextBlockConverter<JoinHint> {

    // @formatter:off
    private static BlockMetaBuilder<Void,JoinHint> builder =
            new BlockMetaBuilder<Void,JoinHint>()
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

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(JoinHint context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
