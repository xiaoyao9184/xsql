package com.xy.xsql.tsql.model.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Identity
        implements DataTypeFunction, Function.InternalFunction {

    private StringConstant dataType;
    private StringConstant seed;
    private StringConstant increment;
    private StringConstant column_name;

    public StringConstant getDataType() {
        return dataType;
    }

    public void setDataType(StringConstant dataType) {
        this.dataType = dataType;
    }

    public StringConstant getSeed() {
        return seed;
    }

    public void setSeed(StringConstant seed) {
        this.seed = seed;
    }

    public StringConstant getIncrement() {
        return increment;
    }

    public void setIncrement(StringConstant increment) {
        this.increment = increment;
    }

    public StringConstant getColumn_name() {
        return column_name;
    }

    public void setColumn_name(StringConstant column_name) {
        this.column_name = column_name;
    }
}
