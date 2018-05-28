package com.xy.xsql.tsql.style.constructor.builder.queries;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.builder.chain.queries.WithBuilder;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.style.constructor.builder.elements.variables.b_SET;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_CONTAINS;
import com.xy.xsql.tsql.style.constructor.builder.queries.predicates.b_FREETEXT;
import com.xy.xsql.tsql.style.constructor.builder.statements.b_INSERT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xiaoyao9184 on 2015/5/25.
 */
public class b_WITH extends WithBuilder<b_WITH> {

    public b_WITH() {
        this.in(this);
    }


    /*
    Item
     */

    @Deprecated
    public b_WITH $$(b$common_table_expression common_table_expression){
        return withItem(common_table_expression.build())
                .and();
    }

    public b_WITH $$(String expression_name,
                     b$column_name_list column_name_list,
                     b_AS as){
        return withItem()
                    .withExpressionName(expression_name)
                    .withColumnName(column_name_list.build())
                    .withCteQueryDefinition(as.build())
                    .and()
                .and();
    }

    public b_WITH $$(String expression_name,
                     b_AS as){
        return withItem()
                .withExpressionName(expression_name)
                .withCteQueryDefinition(as.build())
                .and()
                .and();
    }


    @Deprecated
    public static class b$common_table_expression extends CommonTableExpressionBuilder<b$common_table_expression> {

        public b$common_table_expression() {
            this.in(this);
        }

        /*
        Item
         */

        public b$common_table_expression $$(ColumnName... columnName){
            return withColumnName(columnName)
                    .and();
        }

        public b$common_table_expression AS(Select cteQueryDefinition){
            return withCteQueryDefinition(cteQueryDefinition)
                    .and();
        }

    }

    /**
     * same as
     * @see b_SET.b_OF
     * @see b_WITH.b$column_name_list
     * @see b_FREETEXT.b$column_list
     * @see b_CONTAINS.b$column_list
     * @see b_INSERT.b$column_list
     */
    public static class b$column_name_list extends CodeBuilder<List<ColumnName>> {

        public b$column_name_list() {
            super(new ArrayList<>());
        }

        /*
        Item
         */

        public b$column_name_list $$(ColumnName... columnNames){
            this.target.addAll(Arrays.asList(columnNames));
            return this;
        }
    }

    public static class b_AS extends CodeBuilder<Select> {
        public b_AS(Select cteQueryDefinition) {
            super(cteQueryDefinition);
        }
    }

}
