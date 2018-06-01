package com.xy.xsql.tsql.model.functions.json;

import com.xy.xsql.tsql.model.datatypes.Bit;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class IsJson
        implements JsonFunction, Function.InternalFunction {

    private Expression expression;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

}
