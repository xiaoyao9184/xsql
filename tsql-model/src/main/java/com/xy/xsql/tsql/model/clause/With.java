package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.element.Unknown;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.ArrayList;
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

    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder();
        b.append(Keywords.WITH)
                .append(commonTableExpressionList);

        return b.build();
    }


    /**
     * <common_table_expression>
     */
    public static class CommonTableExpression implements Expression {

        private String expressionName;
        private List<String> columnName;
        //TODO
//        private Sentence cteQueryDefinition;


        public String getExpressionName() {
            return expressionName;
        }

        public void setExpressionName(String expressionName) {
            this.expressionName = expressionName;
        }

        public List<String> getColumnName() {
            return columnName;
        }

        public void setColumnName(List<String> columnName) {
            this.columnName = columnName;
        }

//        public Sentence getCteQueryDefinition() {
//            return cteQueryDefinition;
//        }
//
//        public void setCteQueryDefinition(Sentence cteQueryDefinition) {
//            this.cteQueryDefinition = cteQueryDefinition;
//        }


        @Override
        public List<Block> toBlockList() {
            ListBlockBuilder b = new ListBlockBuilder()
                    .append(new Unknown(expressionName));
            if(columnName.size() > 0){
                List<Block> columnNameBlock = new ArrayList<>();
                for (String name: columnName) {
                    columnNameBlock.add(new Unknown(name));
                }
                b.append(Other.GROUP_START)
                        .append(columnNameBlock,null)
                        .append(Other.GROUP_START);
            }

//            b.append(Keywords.AS)
//                    .append(Other.GROUP_START)
//                    .append(cteQueryDefinition)
//                    .append(Other.GROUP_START);

            return b.build();
        }

    }
}
