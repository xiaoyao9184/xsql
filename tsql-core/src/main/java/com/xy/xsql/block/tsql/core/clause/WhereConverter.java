package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.Where;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WhereConverter
        implements BlockConverter<Where> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Where> builder =
            new ReferenceBlockBuilder<Void,Where>()
                    .overall("WHERE")
                    .sub_keyword(Keywords.WHERE)
                    .sub("search_condition")
                        .ref(SearchConditionConverter.class)
                        .data(Where::getSearchCondition)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Where where) {
        return builder
                .data(where)
                .build();
    }

}
