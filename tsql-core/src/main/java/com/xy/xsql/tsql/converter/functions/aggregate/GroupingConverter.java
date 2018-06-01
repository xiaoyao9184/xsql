package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.aggregate.Count_Big;
import com.xy.xsql.tsql.model.functions.aggregate.Grouping;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class GroupingConverter
        implements ModelMetaBlockConverter<Grouping> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Grouping>()
                    .overall("GROUPING")
                    .sub_keyword(Function.Keywords.GROUPING)
                    .sub_keyword(Other.GROUP_START)
                    .sub("column_expression")
                        .scope(d -> d.getColumnExpression())
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
