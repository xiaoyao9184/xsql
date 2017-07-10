package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.clause.With;
import com.xy.xsql.tsql.model.element.Other;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WithConverter
        implements MetaContextBlockConverter<With> {

    // @formatter:off
    private static BlockMetaBuilder<Void,With> builder =
            new BlockMetaBuilder<Void,With>()
                    .overall("WITH common_table_expression")
                    .sub_keyword(Keywords.WITH)
                    .sub_list("common_table_expression")
                        .description("common_table_expression list")
                        .ref(CommonTableExpressionConverter.class)
                        .data(With::getCommonTableExpressionList)
                        .and();
    // @formatter:on

    public static BlockMeta meta() {
        return builder.build();
    }

    @Override
    public MetaContextBlock convert(With context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class CommonTableExpressionConverter
            implements MetaContextBlockConverter<With.CommonTableExpression> {

        // @formatter:off
        private static BlockMetaBuilder<Void,With.CommonTableExpression> builder =
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

        public static BlockMeta meta() {
            return builder.build();
        }

        @Override
        public MetaContextBlock convert(With.CommonTableExpression context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }
    }

}
