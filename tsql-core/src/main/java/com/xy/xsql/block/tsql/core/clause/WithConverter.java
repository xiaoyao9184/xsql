package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WithConverter
        implements ModelMetaBlockConverter<With> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,With>()
                    .overall("WITH common_table_expression")
                    .sub_keyword(Keywords.WITH)
                    .sub_list("common_table_expression")
                        .description("common_table_expression list")
                        .ref(CommonTableExpressionConverter.class)
                        .data(With::getCommonTableExpressionList)
                        .and()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class CommonTableExpressionConverter
            implements ModelMetaBlockConverter<With.CommonTableExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,With.CommonTableExpression>()
                        .overall("common_table_expression")
                        .sub("expression_name")
                            .data(With.CommonTableExpression::getExpressionName)
                            .and()
                        .sub()
                            .description("column_name list")
                            .optional(data -> data.getColumnName() == null ||
                                    data.getColumnName().isEmpty())
                            .sub_keyword(Other.GROUP_START)
                            .sub_list("column_name")
                                .data(With.CommonTableExpression::getColumnName)
                                .and()
                            .sub_keyword(Other.GROUP_END)
                            .format_indentation_right()
                            .and()
                        .sub()
                            .keyword(Keywords.AS)
                            .startNewline()
                            .and()
                        .sub()
                            .keyword(Other.GROUP_START)
                            .startNewline()
                            .format_indentation_right()
                            .and()
                        .sub("CTE_query_definition")
                            .data(With.CommonTableExpression::getCteQueryDefinition)
                            .and()
                        .sub_keyword(Other.GROUP_END)
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
