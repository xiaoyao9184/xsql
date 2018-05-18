package com.xy.xsql.tsql.model.queries;

import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.Expression;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms175972.aspx
 *
 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 [ WITH <common_table_expression> [ ,...n ] ]

 <common_table_expression>::=
 expression_name [ ( column_name [ ,...n ] ) ]
 AS
 ( CTE_query_definition )

 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class With implements Clause {

    //<common_table_expression> [ ,...n ] ]
    private List<CommonTableExpression> commonTableExpressionList;


    public List<CommonTableExpression> getCommonTableExpressionList() {
        return commonTableExpressionList;
    }

    public void setCommonTableExpressionList(List<CommonTableExpression> commonTableExpressionList) {
        this.commonTableExpressionList = commonTableExpressionList;
    }


    /**
     * <common_table_expression>
     */
    public static class CommonTableExpression implements Expression {

        private String expressionName;
        private List<ColumnName> columnName;
        //maybe diff?
        private Select cteQueryDefinition;


        public String getExpressionName() {
            return expressionName;
        }

        public void setExpressionName(String expressionName) {
            this.expressionName = expressionName;
        }

        public List<ColumnName> getColumnName() {
            return columnName;
        }

        public void setColumnName(List<ColumnName> columnName) {
            this.columnName = columnName;
        }

        public Select getCteQueryDefinition() {
            return cteQueryDefinition;
        }

        public void setCteQueryDefinition(Select cteQueryDefinition) {
            this.cteQueryDefinition = cteQueryDefinition;
        }

    }
}
