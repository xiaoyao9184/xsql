package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.block.tsql.core.clause.SearchConditionConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Having;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class HavingConverter
        implements MetaContextBlockConverter<Having> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Having>()
                    .overall("HAVING")
                    .sub_keyword(Keywords.HAVING)
                    .sub("search condition")
                        .ref(SearchConditionConverter.class)
                        .data(Having::getSearchCondition)
                        .and()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(Having context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }

}
