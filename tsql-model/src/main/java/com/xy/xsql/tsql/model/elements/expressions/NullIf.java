package com.xy.xsql.tsql.model.elements.expressions;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

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

}
