package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
import com.xy.xsql.tsql.model.expression.Expression;
import com.xy.xsql.tsql.model.expression.RowValueExpression;
import com.xy.xsql.tsql.util.ListBlockBuilder;

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


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.VALUES);

        for (List<Expression> rowValueExpressionList: getRowValueExpressionListGroup()) {
            b.append(Other.GROUP_START)
                    .appendExpression(rowValueExpressionList,Other.DELIMITER)
                    .append(Other.GROUP_END);
        }

        return b.build();
    }
}
