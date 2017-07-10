package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.predicate.Exists;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class ExistsPredicateConverter
        implements MetaContextBlockConverter<Exists> {

    // @formatter:off
    private static BlockMetaBuilder<Void,Exists> builder =
            new BlockMetaBuilder<Void,Exists>()
                    .overall("EXISTS")
                    .sub_keyword(Keywords.EXISTS)
                    .sub_keyword(Other.GROUP_START)
                    .sub("subquery")
                        .data(Exists::getSubquery)
                        .and()
                    .sub_keyword(Other.GROUP_END);
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(Exists context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
