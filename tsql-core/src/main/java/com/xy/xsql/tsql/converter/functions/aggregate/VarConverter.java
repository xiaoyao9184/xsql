package com.xy.xsql.tsql.converter.functions.aggregate;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.aggregate.Sum;
import com.xy.xsql.tsql.model.functions.aggregate.Var;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class VarConverter
        implements ModelMetaBlockConverter<Var> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Var>()
                    .overall("VAR")
                    .sub_keyword(Function.Keywords.VAR)
                    .sub_keyword(Other.GROUP_START)
                    .sub()
                        .optional(d -> !d.isUseAll() && !d.isUseDistinct())
                        .czse_keyword(d -> d.isUseAll(), Keywords.ALL)
                        .czse_keyword(d -> d.isUseDistinct(), Keywords.DISTINCT)
                        .and()
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub()
                        .sub_keyword(Keywords.OVER)
                        .sub_keyword(Other.GROUP_START)
                        .sub("partition_by_clause")
                            .optional(d -> d.getPartitionBy() == null)
                            .scope(d -> d.getPartitionBy())
                            .and()
                        .sub("order_by_clause")
                            .scope(d -> d.getOrderBy())
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .syntax_indentation_right()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }

}
