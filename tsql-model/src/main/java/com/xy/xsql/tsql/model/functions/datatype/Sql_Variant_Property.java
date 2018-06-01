package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.Sql_Variant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Sql_Variant_Property
        implements DataTypeFunction, Function.InternalFunction {

    private Expression expression;
    private StringConstant property;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public StringConstant getProperty() {
        return property;
    }

    public void setProperty(StringConstant property) {
        this.property = property;
    }

    enum Property {
        BaseType,
        Precision,
        Scale,
        TotalBytes,
        Collation,
        MaxLength;
    }
}
