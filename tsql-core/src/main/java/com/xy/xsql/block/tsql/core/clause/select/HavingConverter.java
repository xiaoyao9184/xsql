package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.BlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.SearchCondition;
import com.xy.xsql.tsql.model.clause.select.Having;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class HavingConverter
        implements BlockConverter<Having> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Having> builder =
            new ReferenceBlockBuilder<Void,Having>()
                    .overall("HAVING")
                    .sub_keyword(Keywords.HAVING)
                    .sub("search condition")
                        .ref(SearchCondition.class)
                        .data(Having::getSearchCondition)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public Block convert(Having having) {
        return builder
                .data(having)
                .build();
    }

}
