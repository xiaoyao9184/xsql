package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;
import com.xy.xsql.tsql.model.functions.cursor.CursorFunction;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class DataLength
        implements DataTypeFunction, Function.InternalFunction {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
