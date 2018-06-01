package com.xy.xsql.tsql.model.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.datatypes.numeric.NumericDataType;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Crypt_Gen_Random
        implements CryptographicFunction, Function.InternalFunction {

    private Integer length;
    private BinaryConstant seed;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BinaryConstant getSeed() {
        return seed;
    }

    public void setSeed(BinaryConstant seed) {
        this.seed = seed;
    }
}
