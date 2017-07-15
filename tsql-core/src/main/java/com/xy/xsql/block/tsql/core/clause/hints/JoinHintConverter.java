package com.xy.xsql.block.tsql.core.clause.hints;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.clause.hints.JoinHint;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class JoinHintConverter
        implements ModelMetaBlockConverter<JoinHint> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,JoinHint>()
                    .overall("join_hint")
                    .style_required()
                    .czse_keyword(d -> d.equals(JoinHint.LOOP), JoinHint.LOOP)
                    .czse_keyword(d -> d.equals(JoinHint.HASH), JoinHint.HASH)
                    .czse_keyword(d -> d.equals(JoinHint.MERGE), JoinHint.MERGE)
                    .czse_keyword(d -> d.equals(JoinHint.REMOTE), JoinHint.REMOTE)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
