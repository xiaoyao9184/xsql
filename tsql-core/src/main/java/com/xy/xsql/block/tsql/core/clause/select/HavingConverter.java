package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.SearchConditionConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Having;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class HavingConverter
        implements ModelMetaBlockConverter<Having> {

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

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
