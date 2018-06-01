package com.xy.xsql.tsql.model.functions.system;

import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * TODO repalce by
 * @see com.xy.xsql.tsql.model.queries.predicates.IsNull
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class IsNull
        implements SystemFunction, Function.InternalFunction {

    private Expression checkExpression;
    private Expression replacementValue;

    public Expression getCheckExpression() {
        return checkExpression;
    }

    public void setCheckExpression(Expression checkExpression) {
        this.checkExpression = checkExpression;
    }

    public Expression getReplacementValue() {
        return replacementValue;
    }

    public void setReplacementValue(Expression replacementValue) {
        this.replacementValue = replacementValue;
    }
}
