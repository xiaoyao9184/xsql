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
    private Expression expression;
    private Expression expression2;


    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression2() {
        return expression2;
    }

    public void setExpression2(Expression expression2) {
        this.expression2 = expression2;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .withDelimiter(Other.SPACE)
                .append(Keywords.NULLIF)
                .append(Other.GROUP_START)
                .append(getExpression())
                .append(getExpression2())
                .append(Other.GROUP_END);
        return b.build();
    }
}
