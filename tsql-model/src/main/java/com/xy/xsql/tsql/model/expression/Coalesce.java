package com.xy.xsql.tsql.model.expression;


import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms190349.aspx
 *

 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 COALESCE ( expression [ ,...n ] )

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class Coalesce implements Expression {

    //
    private List<Expression> expressionList;


    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.COALESCE)
                .append(Other.GROUP_START);
        int i = 0;
        for (Expression expression: expressionList) {
            b.append(i==0 ? null : Other.DELIMITER)
                    .append(expression);
            i++;
        }
        b.append(Other.GROUP_END);
        return b.build();
    }
}
