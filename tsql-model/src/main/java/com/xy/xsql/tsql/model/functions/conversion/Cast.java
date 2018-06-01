package com.xy.xsql.tsql.model.functions.conversion;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Cast
        implements ConversionFunction, Function.InternalFunction {

    private Expression expression;
    private DataType dataType;
    private Number length;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Number getLength() {
        return length;
    }

    public void setLength(Number length) {
        this.length = length;
    }
}
