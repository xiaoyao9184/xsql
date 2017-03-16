package com.xy.xsql.tsql.model.expression;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.util.ListBlockBuilder;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/ms177562.aspx
 *

 -- Syntax for SQL Server, Azure SQL Database, Azure SQL Data Warehouse, Parallel Data Warehouse

 NULLIF ( expression , expression )

 *
 * Created by xiaoyao9184 on 2016/12/23.
 */
public class NullIf implements Expression {

    //
    private Expression expressionLeft;
    private Expression expressionRight;


    public Expression getExpressionLeft() {
        return expressionLeft;
    }

    public void setExpressionLeft(Expression expressionLeft) {
        this.expressionLeft = expressionLeft;
    }

    public Expression getExpressionRight() {
        return expressionRight;
    }

    public void setExpressionRight(Expression expressionRight) {
        this.expressionRight = expressionRight;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.NULLIF)
                .append(Other.GROUP_START)
                .append(expressionLeft)
                .append(Other.DELIMITER)
                .append(expressionRight)
                .append(Other.GROUP_END);
        return b.build();
    }
}
