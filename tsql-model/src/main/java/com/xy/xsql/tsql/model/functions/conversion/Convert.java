package com.xy.xsql.tsql.model.functions.conversion;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Convert
        implements ConversionFunction, Function.InternalFunction {

    //data_type [ ( length ) ]
    private DataType dataType;
    private Number length;
    // expression [ , style ]
    private Expression expression;
    private Expression style;

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

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getStyle() {
        return style;
    }

    public void setStyle(Expression style) {
        this.style = style;
    }
}
