package com.xy.xsql.tsql.model.clause;

import com.xy.xsql.tsql.model.Block;
import com.xy.xsql.tsql.model.Keywords;
import com.xy.xsql.tsql.model.element.Other;
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

    //( <row value expression list> ) [ ,...n ]
    private List<List<RowValueExpression>> rowValueExpressionListGroup;

    public List<List<RowValueExpression>> getRowValueExpressionListGroup() {
        return rowValueExpressionListGroup;
    }

    public void setRowValueExpressionListGroup(List<List<RowValueExpression>> rowValueExpressionListGroup) {
        this.rowValueExpressionListGroup = rowValueExpressionListGroup;
    }


    @Override
    public List<Block> toBlockList() {
        ListBlockBuilder b = new ListBlockBuilder()
                .append(Keywords.VALUES);

        for (List<RowValueExpression> rowValueExpressionList: getRowValueExpressionListGroup()) {
            b.append(Other.GROUP_START)
                    .appendExpression(rowValueExpressionList,Other.DELIMITER)
                    .append(Other.GROUP_END);
        }

        return b.build();
    }
}
