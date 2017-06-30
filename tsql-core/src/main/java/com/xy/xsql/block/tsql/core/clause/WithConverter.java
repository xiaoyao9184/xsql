package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WithConverter
        implements ReferenceBlockConverter<With> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,With> builder =
            new ReferenceBlockBuilder<Void,With>()
                    .overall("WITH common_table_expression")
                    .sub_keyword(Keywords.WITH)
                    .sub_list("common_table_expression")
                        .description("common_table_expression list")
                        .ref(CommonTableExpressionConverter.class)
                        .data(With::getCommonTableExpressionList)
                        .and();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(With with) {
        return builder
                .data(with)
                .build();
    }


    public static class CommonTableExpressionConverter
            implements ReferenceBlockConverter<With.CommonTableExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,With.CommonTableExpression> builder =
                new ReferenceBlockBuilder<Void,With.CommonTableExpression>()
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
                            .and()
                        .sub()
                            .keyword(Keywords.AS)
                            .startNewline()
                            .and()
                        .sub()
                            .keyword(Other.GROUP_START)
                            .startNewline()
                            .and()
                        .sub("CTE_query_definition")
                            .data(With.CommonTableExpression::getCteQueryDefinition)
                            .and()
                        .sub_keyword(Other.GROUP_END);
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(With.CommonTableExpression commonTableExpression) {
            return builder
                    .data(commonTableExpression)
                    .build();
        }
    }

}
