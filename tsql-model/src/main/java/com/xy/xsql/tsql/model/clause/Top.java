package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * https://msdn.microsoft.com/en-us/library/ms189463.aspx
 *
 -- Syntax for SQL Server and Azure SQL Database

 [
 TOP (expression) [PERCENT]
 [ WITH TIES ]
 ]

 *
 -- Syntax for Azure SQL Data Warehouse and Parallel Data Warehouse

 [
 TOP ( expression )
 [ WITH TIES ]
 ]
 * Created by xiaoyao9184 on 2016/11/13.
 */
public class Top implements Clause {

    private Expression expression;
    private boolean usePercent;
    private boolean useTies;


    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public boolean isUsePercent() {
        return usePercent;
    }

    public void setUsePercent(boolean usePercent) {
        this.usePercent = usePercent;
    }

    public boolean isUseTies() {
        return useTies;
    }

    public void setUseTies(boolean useTies) {
        this.useTies = useTies;
    }

}
