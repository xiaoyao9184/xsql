package com.xy.xsql.tsql.model.datatypes.numeric;

import com.xy.xsql.tsql.model.datatypes.DataType.*;

/**
 * Created by xiaoyao9184 on 2018/5/18.
 */
public class Decimal
        extends PrecisionScaleDataType
        implements NumericDataType, InternalDataType {

    public Decimal(){
        super(18,0);
    }

    @Override
    public Keywords keyword() {
        return Keywords.decimal;
    }

}
