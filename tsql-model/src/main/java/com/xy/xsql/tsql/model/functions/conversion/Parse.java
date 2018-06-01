package com.xy.xsql.tsql.model.functions.conversion;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Parse
        implements ConversionFunction, Function.InternalFunction {

    //string_value AS data_type
    private Expression stringValue;
    private DataType dataType;
    //[ USING culture ]
    private StringConstant culture;

    public Expression getStringValue() {
        return stringValue;
    }

    public void setStringValue(Expression stringValue) {
        this.stringValue = stringValue;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public StringConstant getCulture() {
        return culture;
    }

    public void setCulture(StringConstant culture) {
        this.culture = culture;
    }
}
