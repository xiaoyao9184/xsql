package com.xy.xsql.tsql.model.functions.aggregate;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

import java.util.List;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Grouping_Id
        implements AggregateFunction, Function.InternalFunction {

    //<column_expression>[ ,...n ]
    private List<Expression> columnExpressionList;

    public List<Expression> getColumnExpressionList() {
        return columnExpressionList;
    }

    public void setColumnExpressionList(List<Expression> columnExpressionList) {
        this.columnExpressionList = columnExpressionList;
    }
}
