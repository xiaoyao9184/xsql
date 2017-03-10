package com.xy.xsql.orm.data.sql.expression;

import com.xy.xsql.orm.core.element.ListElementBuilder;
import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.data.sql.Expression;
import com.xy.xsql.orm.data.sql.element.GrammarEnum;
import com.xy.xsql.orm.data.sql.element.OtherEnum;

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
    public List<Element> toElementList() {
        ListElementBuilder b = new ListElementBuilder()
                .withDelimiter(OtherEnum.SPACE)
                .append(GrammarEnum.COALESCE)
                .append(OtherEnum.GROUP_START);
        int i = 0;
        for (Expression expression: expressionList) {
            b.append(i==0 ? null : OtherEnum.DELIMITER)
                    .append(expression);
            i++;
        }
        b.append(OtherEnum.GROUP_END);
        return b.build();
    }
}
