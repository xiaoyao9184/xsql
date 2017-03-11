package com.xy.xsql.orm.data.sql.clause;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.ElementList;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.Sentence;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;
import com.xy.xsql.orm.data.sql.element.UnknownString;

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
public class With implements ElementList {

    //<common_table_expression> [ ,...n ] ]
    private List<CommonTableExpression> commonTableExpressionList;


    public List<CommonTableExpression> getCommonTableExpressionList() {
        return commonTableExpressionList;
    }

    public void setCommonTableExpressionList(List<CommonTableExpression> commonTableExpressionList) {
        this.commonTableExpressionList = commonTableExpressionList;
    }

    @Override
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder();
        b.append(GrammarEnum.WITH)
                .append(commonTableExpressionList);

        return b.build();
    }


    /**
     * <common_table_expression>
     */
    public static class CommonTableExpression implements Expression {

        private String expressionName;
        private List<String> columnName;
        private Sentence cteQueryDefinition;


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

        public Sentence getCteQueryDefinition() {
            return cteQueryDefinition;
        }

        public void setCteQueryDefinition(Sentence cteQueryDefinition) {
            this.cteQueryDefinition = cteQueryDefinition;
        }


        @Override
        public List<Element> toElementList() {
            ListElementBuilder b = new ListElementBuilder()
                    .append(new UnknownString(expressionName));
            if(columnName.size() > 0){
                List<Element> columnNameElement = new ArrayList<>();
                for (String name: columnName) {
                    columnNameElement.add(new UnknownString(name));
                }
                b.append(OtherEnum.GROUP_START)
                        .append(columnNameElement,null)
                        .append(OtherEnum.GROUP_START);
            }

            b.append(GrammarEnum.AS)
                    .append(OtherEnum.GROUP_START)
                    .append(cteQueryDefinition)
                    .append(OtherEnum.GROUP_START);

            return b.build();
        }

    }
}
