package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.AtTimeZone;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class AtTimeConverter
        implements MetaContextBlockConverter<AtTimeZone> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,AtTimeZone>()
                    .overall("AT TIME ZONE")
                    .sub("inputdate")
                        .data(AtTimeZone::getInputExpression)
                        .and()
                    .sub_keyword(Keywords.Key.AT)
                    .sub_keyword(Keywords.Key.TIME)
                    .sub_keyword(Keywords.Key.ZONE)
                    .sub("timezone")
                        .data(AtTimeZone::getTimezone)
                        .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(AtTimeZone context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
