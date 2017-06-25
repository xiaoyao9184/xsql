package com.xy.xsql.tsql.model.expression;

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

}
