package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.expression.Expression;

import java.util.List;

/**
 * https://msdn.microsoft.com/en-us/library/dd776382.aspx
 *
 VALUES ( <row value expression list> ) [ ,...n ]

 <row value expression list> ::=
 {<row value expression> } [ ,...n ]

 <row value expression> ::=
 { DEFAULT | NULL | expression }

 *
 * Created by xiaoyao9184 on 2016/12/22.
 */
public class TableValueConstructor implements Clause {

    //TODO maybe can replace with Expression
    //( <row value expression list> ) [ ,...n ]
    private List<List<Expression>> rowValueExpressionListGroup;

    public List<List<Expression>> getRowValueExpressionListGroup() {
        return rowValueExpressionListGroup;
    }

    public void setRowValueExpressionListGroup(List<List<Expression>> rowValueExpressionListGroup) {
        this.rowValueExpressionListGroup = rowValueExpressionListGroup;
    }

}
