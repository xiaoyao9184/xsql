package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.core.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.Where;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WhereConverter
        implements ModelMetaBlockConverter<Where> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Where>()
                    .overall("WHERE")
                    .sub_keyword(Keywords.WHERE)
                    .sub("search_condition")
                        .ref(SearchConditionConverter.class)
                        .data(Where::getSearchCondition)
                        .format_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
