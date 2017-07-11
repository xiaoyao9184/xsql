package com.xy.xsql.block.tsql.core.expression;

import com.xy.xsql.block.core.MetaContextBlockBuilder;
import com.xy.xsql.block.core.MetaContextBlockConverter;
import com.xy.xsql.block.core.BlockMetaBuilder;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.block.model.MetaContextBlock;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.expression.Case;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CaseConverter
        implements MetaContextBlockConverter<Case> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Case>()
                    .overall("CASE")
                    .czse(d -> d.getInputExpression() != null)
                        .description("simple case")
                        .ref(SimpleCaseConverter.meta)
                        .data(d -> d)
                        .and()
                    .czse(d -> d.getInputExpression() == null)
                        .description("searched case")
                        .ref(SearchedCaseConverter.meta)
                        .data(d -> d)
                        .and()
                    .subTakeLine()
                    .build();
    // @formatter:on

    public BlockMeta meta() {
        return meta;
    }

    @Override
    public MetaContextBlock convert(Case context) {
        return MetaContextBlockBuilder
                .meta(meta())
                .build(context);
    }


    public static class SimpleCaseConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Case>()
                        .overall("Simple CASE expression")
                        .sub_keyword(Keywords.CASE)
                        .sub("input_expression")
                            .data(Case::getInputExpression)
                            .and()
                        .sub()
                            .description("WHEN when_expression THEN result_expression [ ...n ]")
                            .repeat()
                            .ref(CaseWhenThenExpressionConverter.meta)
                            .data(Case::getWhenThenExpressionList)
                            .and()
                        .sub()
                            .description("[ ELSE else_result_expression ]")
                            .optional(c -> c.getElseResultExpression() == null)
                            .sub_keyword(Keywords.ELSE)
                            .sub("else_result_expression")
                                .data(Case::getElseResultExpression)
                                .and()
                            .and()
                        .sub_keyword(Keywords.END)
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }

    public static class SearchedCaseConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Case>()
                        .overall("Searched CASE expression")
                        .sub_keyword(Keywords.CASE)
                        .sub()
                            .description("WHEN Boolean_expression THEN result_expression [ ...n ]")
                            .repeat()
                            .ref(CaseWhenThenExpressionConverter.meta)
                            .data(Case::getWhenThenExpressionList)
                            .and()
                        .sub()
                            .description("[ ELSE else_result_expression ]")
                            .optional(c -> c.getElseResultExpression() == null)
                            .sub_keyword(Keywords.ELSE)
                            .sub("else_result_expression")
                                .data(Case::getElseResultExpression)
                                .and()
                            .and()
                        .sub_keyword(Keywords.END)
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }


    public static class CaseWhenThenExpressionConverter
            implements MetaContextBlockConverter<Case.WhenThenExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Case.WhenThenExpression>()
                        .sub_keyword(Keywords.WHEN)
                        .sub("when_expression")
                            .data(Case.WhenThenExpression::getWhenExpression)
                            .and()
                        .sub_keyword(Keywords.THEN)
                        .sub("result_expression")
                            .data(Case.WhenThenExpression::getResultExpression)
                            .and()
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

        @Override
        public MetaContextBlock convert(Case.WhenThenExpression context) {
            return MetaContextBlockBuilder
                    .meta(meta())
                    .build(context);
        }

    }

}
