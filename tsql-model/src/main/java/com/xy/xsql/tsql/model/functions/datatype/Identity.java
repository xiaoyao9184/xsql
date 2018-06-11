package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.DataType;
import com.xy.xsql.tsql.model.elements.expressions.Expression;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Identity
        implements DataTypeFunction, Function.InternalFunction {

    private DataType dataType;
    private Expression seed;
    private Expression increment;
    private Expression column_name;

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Expression getSeed() {
        return seed;
    }

    public void setSeed(Expression seed) {
        this.seed = seed;
    }

    public Expression getIncrement() {
        return increment;
    }

    public void setIncrement(Expression increment) {
        this.increment = increment;
    }

    public Expression getColumn_name() {
        return column_name;
    }

    public void setColumn_name(Expression column_name) {
        this.column_name = column_name;
    }
}
