package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.builder.chain.queries.Queries;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_WITH;

import java.util.Arrays;

import static com.xy.xsql.tsql.builder.chain.queries.Queries.$SubQuery;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface with {

    @Deprecated
    static b_WITH WITH(b_WITH.b$common_table_expression common_table_expression){
        return new b_WITH(){
            {
                withItem(common_table_expression.build());
            }
        };
    }

    static b_WITH WITH(String expression_name,
                       b_WITH.b$column_name_list column_name_list,
                       b_WITH.b_AS as){
        return new b_WITH(){
            {
                withItem()
                        .withExpressionName(expression_name)
                        .withColumnName(column_name_list.build())
                        .withCteQueryDefinition(as.build());
            }
        };
    }

    static b_WITH WITH(String expression_name,
                       b_WITH.b_AS as){
        return new b_WITH(){
            {
                withItem()
                    .withExpressionName(expression_name)
                    .withCteQueryDefinition(as.build());
            }
        };
    }


    static b_WITH.b$column_name_list $(ColumnName... columnNames){
        return new b_WITH.b$column_name_list(){
            {
                this.target.addAll(Arrays.asList(columnNames));
            }
        };
    }

    static b_WITH.b_AS AS(Select cte_query_definition){
        return new b_WITH.b_AS(cte_query_definition);
    }

    static b_WITH.b_AS AS(Select.QueryExpression cte_query_definition){
        return new b_WITH.b_AS(Queries.$SubQuery(cte_query_definition));
    }

    static b_WITH.b_AS AS(Select.QuerySpecification cte_query_definition){
        return new b_WITH.b_AS($SubQuery(cte_query_definition));
    }


    @Deprecated
    static b_WITH.b$common_table_expression $(
            String expression_name,
            b_WITH.b_AS as){
        return new b_WITH.b$common_table_expression(){
            {
                withExpressionName(expression_name);
                withCteQueryDefinition(as.build());
            }
        };
    }

    @Deprecated
    static b_WITH.b$common_table_expression $(
            String expression_name){
        return new b_WITH.b$common_table_expression(){
            {
                withExpressionName(expression_name);
            }
        };
    }
}
