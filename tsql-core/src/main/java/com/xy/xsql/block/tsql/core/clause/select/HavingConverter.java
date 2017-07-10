package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.tsql.core.clause.SearchConditionConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.select.Having;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class HavingConverter
        implements ReferenceBlockConverter<Having> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Having> builder =
            new ReferenceBlockBuilder<Void,Having>()
                    .overall("HAVING")
                    .sub_keyword(Keywords.HAVING)
                    .sub("search condition")
                        .ref(SearchConditionConverter.class)
                        .data(Having::getSearchCondition)
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public BlockMeta convert(Having having) {
        return builder
                .data(having)
                .build();
    }

}
