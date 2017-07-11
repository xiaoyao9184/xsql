package com.xy.xsql.block.tsql.core.predicate;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.predicate.IsNull;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class IsNullPredicateConverter
        implements MetaContextBlockConverter<IsNull> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,IsNull>()
                    .overall("IS NULL")
                    .sub("expression")
                        .data(IsNull::getExpression)
                        .and()
                    .sub_keyword(Keywords.IS)
                    .sub()
                        .optional(d -> !d.isUseNotOperator())
                        .keyword(Keywords.NOT)
                        .and()
                    .sub_keyword(Keywords.NULL)
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(IsNull context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
