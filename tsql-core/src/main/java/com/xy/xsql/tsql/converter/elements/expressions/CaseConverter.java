package com.xy.xsql.tsql.converter.elements.expressions;

import com.xy.xsql.block.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.model.elements.Keywords;
import com.xy.xsql.tsql.model.elements.expressions.Case;

/**
 * Created by xiaoyao9184 on 2017/6/15.
 */
public class CaseConverter
        implements ModelMetaBlockConverter<Case> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Case>()
                    .overall("CASE")
                    .czse(d -> d.getInputExpression() != null)
                        .description("simple case")
                        .ref(SimpleCaseConverter.meta)
                        .scope(d -> d)
                        .and()
                    .czse(d -> d.getInputExpression() == null)
                        .description("searched case")
                        .ref(SearchedCaseConverter.meta)
                        .scope(d -> d)
                        .and()
                    .syntax_sub_line()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class SimpleCaseConverter {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Case>()
                        .overall("Simple CASE expression")
                        .sub_keyword(Keywords.CASE)
                        .sub("input_expression")
                            .scope(Case::getInputExpression)
                            .format()
                                .line(false)
                                .and()
                            .and()
                        .sub()
                            .description("when then list")
                            .repeat()
                            .ref(CaseWhenThenExpressionConverter.meta)
                            .scope(Case::getWhenThenExpressionList)
                            .sub_format_line(true)
                            .and()
                        .sub()
                            .description("else")
                            .optional(c -> c.getElseResultExpression() == null)
                            .sub_keyword(Keywords.ELSE)
                            .sub("else_result_expression")
                                .scope(Case::getElseResultExpression)
                                .and()
                            .and()
                        .sub_keyword(Keywords.END)
                        .sub_format_line(true)
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
                            .description("when then list")
                            .repeat()
                            .ref(CaseWhenThenExpressionConverter.meta)
                            .scope(Case::getWhenThenExpressionList)
                            .and()
                        .sub()
                            .description("else")
                            .optional(c -> c.getElseResultExpression() == null)
                            .sub_keyword(Keywords.ELSE)
                            .sub("else_result_expression")
                                .scope(Case::getElseResultExpression)
                                .and()
                            .and()
                        .sub_keyword(Keywords.END)
                        .sub_format_line(true)
                        .build();
        // @formatter:on

        public BlockMeta meta() {
            return meta;
        }

    }


    public static class CaseWhenThenExpressionConverter
            implements ModelMetaBlockConverter<Case.WhenThenExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Case.WhenThenExpression>()
                        .sub_keyword(Keywords.WHEN)
                        .sub("when_expression")
                            .scope(Case.WhenThenExpression::getWhenExpression)
                            .and()
                        .sub_keyword(Keywords.THEN)
                        .sub("result_expression")
                            .scope(Case.WhenThenExpression::getResultExpression)
                            .and()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }

}
