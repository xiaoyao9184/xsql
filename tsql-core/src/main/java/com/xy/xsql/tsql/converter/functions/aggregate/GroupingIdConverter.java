package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.aggregate.Grouping_Id;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GroupingIdConverter
        implements ModelMetaBlockConverter<Grouping_Id> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Grouping_Id>()
                    .overall("GROUPING_ID")
                    .sub_keyword(Function.Keywords.GROUPING_ID)
                    .sub_keyword(Other.GROUP_START)
                    .sub_list("column_expression")
                        .scope(d -> d.getColumnExpressionList())
                        .syntax_reference()
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
