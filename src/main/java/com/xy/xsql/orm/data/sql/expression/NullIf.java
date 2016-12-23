package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.NULLIF)
                .append(OtherEnum.GROUP_START)
                .append(getExpression())
                .append(getExpression2())
                .append(OtherEnum.GROUP_END);
        return b.build();
    }
}
