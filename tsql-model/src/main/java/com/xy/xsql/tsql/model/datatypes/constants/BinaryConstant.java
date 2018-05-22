package com.xy.xsql.tsql.model.datatypes.constants;

import com.xy.xsql.tsql.model.elements.expressions.Expression;

/**
 * Binary constants
 * uniqueidentifier constants(output like Binary string)
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class BinaryConstant
        implements Constant, Expression {

    private byte[] data;

    public BinaryConstant(){}

    public BinaryConstant(byte[] data){
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
