package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockConverter;
import com.xy.xsql.block.core.ReferenceBlockBuilder;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.block.tsql.core.clause.select.ForConverter;
import com.xy.xsql.block.tsql.core.clause.select.GroupByConverter;
import com.xy.xsql.block.tsql.core.clause.select.IntoConverter;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.operator.Set;
import com.xy.xsql.tsql.model.statement.dml.Select;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class SelectConverter
        implements ReferenceBlockConverter<Select> {

    // @formatter:off
    private static ReferenceBlockBuilder<Void,Select> builder =
            new ReferenceBlockBuilder<Void,Select>()
                    .overall("SELECT statement")
                    .sub("[ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ]")
                        .optional(d -> d.getWith() == null)
                        .data(Select::getWith)
                        .and()
                    .sub("query_specification")
                        .ref(QuerySpecificationConverter.class)
                        .data(Select::getQueryExpression)
                        .and()
                    .sub("ORDER BY { order_by_expression | column_position [ ASC | DESC ] } [ ,...n ]")
                        .optional(d -> d.getOrderBy() == null)
                        .data(Select::getOrderBy)
                        .and()
                    .sub("FOR Clause")
                        .optional(d -> d.getForClause() == null)
                        .ref(ForConverter.class)
                        .data(Select::getForClause)
                        .and()
                    .sub("OPTION ( <query_hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Select::getOption)
                        .and()
                    .subTakeLine();
    // @formatter:on

    public static ReferenceBlock meta() {
        return builder.build();
    }

    @Override
    public ReferenceBlock convert(Select select) {
        return builder
                .data(select)
                .build();
    }


    public static class QueryExpressionConverter
            implements ReferenceBlockConverter<Select.QueryExpression> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Select.QueryExpression> builder =
                new ReferenceBlockBuilder<Void,Select.QueryExpression>()
                        .overall("query_expression")
                        .sub()
                            .required()
                            .czse(d -> d.getQueryExpression() != null,"query_specification")
                                .ref(QuerySpecificationConverter.class)
                                .data(Select.QueryExpression::getQuerySpecification)
                                .and()
                            .czse(d -> d.getQuerySpecification() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("query_expression")
                                    .ref(QueryExpressionConverter.class)
                                    .data(Select.QueryExpression::getQueryExpression)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .sub()
                            .optional(d -> d.getUnitItem() == null)
                            .list(UnionItemConverter.meta())
                            .data(Select.QueryExpression::getUnitItem)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Select.QueryExpression queryExpression) {
            return builder
                    .data(queryExpression)
                    .build();
        }

    }


    public static class UnionItemConverter
            implements ReferenceBlockConverter<Select.UnionItem> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Select.UnionItem> builder =
                new ReferenceBlockBuilder<Void,Select.UnionItem>()
                        .sub()
                            .required()
                            .czse(d ->
                                    Set.UNION_ALL.equals(d.getOperatorSet()) ||
                                    Set.UNION.equals(d.getOperatorSet())
                            )
                                .sub_keyword(Keywords.UNION)
                                .sub()
                                    .optional(d -> Set.UNION.equals(d.getOperatorSet()))
                                    .keyword(Keywords.ALL)
                                    .and()
                                .and()
                            .czse(d -> Set.EXCEPT.equals(d.getOperatorSet()))
                                .keyword(Keywords.EXCEPT)
                                .and()
                            .czse(d -> Set.INTERSECT.equals(d.getOperatorSet()))
                                .keyword(Keywords.INTERSECT)
                                .and()
                            .and()
                        .sub()
                            .czse(d -> d.getQueryExpression() != null,"query_specification")
                                .ref(QuerySpecificationConverter.class)
                                .data(Select.UnionItem::getQueryExpression)
                                .and()
                            .czse(d -> d.getQuerySpecification() != null)
                                .sub_keyword(Other.GROUP_START)
                                .sub("query_expression")
                                    .ref(QueryExpressionConverter.class)
                                    .data(Select.UnionItem::getQuerySpecification)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Select.UnionItem unionItem) {
            return builder
                    .data(unionItem)
                    .build();
        }

    }


    public static class QuerySpecificationConverter
            implements ReferenceBlockConverter<Select.QuerySpecification> {

        // @formatter:off
        private static ReferenceBlockBuilder<Void,Select.QuerySpecification> builder =
                new ReferenceBlockBuilder<Void,Select.QuerySpecification>()
                        .overall("query_specification")
                        .sub_keyword(Keywords.SELECT)
                        .sub()
                            .description("all or distinct")
                            .optional(d -> !d.isUseAll() && !d.isUseDistinct())
                            .czse_keyword(Select.QuerySpecification::isUseAll, Keywords.ALL)
                            .czse_keyword(Select.QuerySpecification::isUseDistinct, Keywords.DISTINCT)
                            .and()
                        .sub("TOP ( expression ) [ PERCENT ]")
                            .optional(d -> d.getTop() == null)
                            .data(Select.QuerySpecification::getTop)
                            .and()
                        .sub("select_list")
                            .ref(com.xy.xsql.block.tsql.core.clause.select.SelectConverter.SelectListConverter.class)
                            .data(Select.QuerySpecification::getSelectList)
                            .and()
                        //TODO can be set data
//                        .sub_meta(IntoConverter.meta())
                        .sub("FROM { <table_source> } [ ,...n ]")
                            .optional(d -> d.getFrom() == null)
                            .data(Select.QuerySpecification::getFrom)
                            .and()
                        .sub("WHERE <search_condition>")
                            .optional(d -> d.getWhere() == null)
                            .data(Select.QuerySpecification::getWhere)
                            .and()
                        .sub("GROUP BY")
                            .optional(d -> d.getGroupBy() == null)
                            .ref(GroupByConverter.class)
                            .data(Select.QuerySpecification::getGroupBy)
                            .and()
                        .sub("HAVING < search_condition >")
                            .optional(d -> d.getHaving() == null)
                            .data(Select.QuerySpecification::getHaving)
                            .and()
                        .subTakeLine();
        // @formatter:on

        public static ReferenceBlock meta() {
            return builder.build();
        }

        @Override
        public ReferenceBlock convert(Select.QuerySpecification querySpecification) {
            return builder
                    .data(querySpecification)
                    .build();
        }

    }
}
