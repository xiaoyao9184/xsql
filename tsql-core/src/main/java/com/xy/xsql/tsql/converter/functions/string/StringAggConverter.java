package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.converter.queries.select.OrderByConverter;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.Other;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.string.String_Agg;
import com.xy.xsql.tsql.model.queries.select.OrderBy;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class StringAggConverter
        implements ModelMetaBlockConverter<String_Agg> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,String_Agg>()
                    .overall("STRING_AGG")
                    .sub_keyword(Function.Keywords.STRING_AGG)
                    .sub_keyword(Other.GROUP_START)
                    .sub("expression")
                        .scope(d -> d.getExpression())
                        .and()
                    .sub_keyword(Other.DELIMITER)
                    .sub("separator")
                        .scope(d -> d.getSeparator())
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .sub("order_clause")
                        .optional(d -> d.getOrderBy() == null)
                        .ref(StringAggConverter.metaOrder)
                        .scope(d -> d.getOrderBy())
                        .syntax_reference()
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    // @formatter:off
    public static BlockMeta metaOrder =
            new BlockMetaBuilder<Void,OrderBy.Item>()
                    .overall("order_clause")
                    .sub_keyword(Keywords.Key.WITHIN)
                    .sub_keyword(Keywords.GROUP)
                    .sub_keyword(Other.GROUP_START)
                    .sub_keyword(Keywords.ORDER)
                    .sub_keyword(Keywords.BY)
                    .sub("order_by_expression_list")
                        .ref(OrderByConverter.ItemConverter.class)
                        .scope(d -> d.getOrderByExpression())
                        .and()
                    .sub()
                        .description("ase/desc")
                        .optional(d -> !(
                                d.isUseAsc() ||
                                d.isUseDesc()
                        ))
                        .czse_keyword(OrderBy.Item::isUseAsc,Keywords.ASC)
                        .czse_keyword(OrderBy.Item::isUseDesc,Keywords.DESC)
                        .and()
                    .sub_keyword(Other.GROUP_END)
                    .build();
    // @formatter:on

}
