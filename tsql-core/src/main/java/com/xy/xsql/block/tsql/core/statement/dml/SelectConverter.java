package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.meta.BlockMetaBuilder;
import com.xy.xsql.block.core.converter.ModelMetaBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
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
        implements ModelMetaBlockConverter<Select> {

    // @formatter:off
    public static BlockMeta meta =
            new BlockMetaBuilder<Void,Select>()
                    .overall("SELECT statement")
                    .sub("[ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ]")
                        .optional(d -> d.getWith() == null)
                        .data(Select::getWith)
                        .format_line()
                        .and()
                    .sub("query_expression")
                        .ref(QueryExpressionConverter.class)
                        .data(Select::getQueryExpression)
                        .format_line()
                        .and()
                    .sub("ORDER BY { order_by_expression | column_position [ ASC | DESC ] } [ ,...n ]")
                        .optional(d -> d.getOrderBy() == null)
                        .data(Select::getOrderBy)
                        .format_line()
                        .and()
                    .sub("FOR Clause")
                        .optional(d -> d.getForClause() == null)
                        .ref(ForConverter.class)
                        .data(Select::getForClause)
                        .format_line()
                        .and()
                    .sub("OPTION ( <query_hint> [ ,...n ] )")
                        .optional(d -> d.getOption() == null)
                        .data(Select::getOption)
                        .format_line()
                        .and()
                    .subTakeLine()
                    .build();
    // @formatter:on

    @Override
    public BlockMeta meta() {
        return meta;
    }


    public static class QueryExpressionConverter
            implements ModelMetaBlockConverter<Select.QueryExpression> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Select.QueryExpression>()
                        .overall("query_expression")
                        .sub()
                            .description("first query")
                            .required()
                            .czse(d -> d.getQuerySpecification() != null,"query_specification")
                                .ref(QuerySpecificationConverter.class)
                                .data(Select.QueryExpression::getQuerySpecification)
                                .and()
                            .czse(d -> d.getQueryExpression() != null)
                                .description("( <query_expression> )")
                                .sub_keyword(Other.GROUP_START)
                                .sub("query_expression")
                                    .ref(QueryExpressionConverter.class)
                                    .data(Select.QueryExpression::getQueryExpression)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .and()
                        .sub()
                            .description("union list")
                            .optional(d -> d.getUnionItems() == null)
                            .repeat()
                            .ref(UnionItemConverter.meta)
                            .data(Select.QueryExpression::getUnionItems)
                            .format_line()
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class UnionItemConverter
            implements ModelMetaBlockConverter<Select.UnionItem> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Select.UnionItem>()
                        .description("union item")
                        .sub()
                            .description("union item's keyword")
                            .required()
                            .czse(d ->
                                    Set.UNION_ALL.equals(d.getOperatorSet()) ||
                                    Set.UNION.equals(d.getOperatorSet())
                            )
                                .description("union keyword")
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
                            .description("union's query")
                            .czse(d -> d.getQuerySpecification() != null,"query_specification")
                                .ref(QuerySpecificationConverter.class)
                                .data(Select.UnionItem::getQuerySpecification)
                                .and()
                            .czse(d -> d.getQueryExpression() != null)
                                .description("( <query_expression> )")
                                .sub_keyword(Other.GROUP_START)
                                .sub("query_expression")
                                    .ref(QueryExpressionConverter.class)
                                    .data(Select.UnionItem::getQueryExpression)
                                    .and()
                                .sub_keyword(Other.GROUP_END)
                                .and()
                            .format_line()
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }


    public static class QuerySpecificationConverter
            implements ModelMetaBlockConverter<Select.QuerySpecification> {

        // @formatter:off
        public static BlockMeta meta =
                new BlockMetaBuilder<Void,Select.QuerySpecification>()
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
                            .format_line()
                            .and()
                        .sub("select_list")
                            .ref(com.xy.xsql.block.tsql.core.clause.select.SelectConverter.SelectListConverter.class)
                            .data(Select.QuerySpecification::getSelectList)
                            .format_indentation_right()
                            .and()
                        .sub("INTO new_table")
                            .optional(d -> d.getInto() == null)
                            .ref(IntoConverter.meta)
                            .data(Select.QuerySpecification::getInto)
                            .format_line()
                            .and()
                        .sub("FROM { <table_source> } [ ,...n ]")
                            .optional(d -> d.getFrom() == null)
                            .data(Select.QuerySpecification::getFrom)
                            .format_line()
                            .and()
                        .sub("WHERE <search_condition>")
                            .optional(d -> d.getWhere() == null)
                            .data(Select.QuerySpecification::getWhere)
                            .format_line()
                            .and()
                        .sub("GROUP BY")
                            .optional(d -> d.getGroupBy() == null)
                            .ref(GroupByConverter.class)
                            .data(Select.QuerySpecification::getGroupBy)
                            .format_line()
                            .and()
                        .sub("HAVING < search_condition >")
                            .optional(d -> d.getHaving() == null)
                            .data(Select.QuerySpecification::getHaving)
                            .format_line()
                            .and()
                        .subTakeLine()
                        .build();
        // @formatter:on

        @Override
        public BlockMeta meta() {
            return meta;
        }

    }
}
