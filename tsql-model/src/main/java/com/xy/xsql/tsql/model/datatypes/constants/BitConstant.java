package com.xy.xsql.tsql.model.datatypes.constants;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BitConstant implements Constant {

    private Byte aByte;

    public BitConstant(){
        this.aByte = 0;
    }

    public BitConstant(Byte aByte) {
        this.aByte = aByte;
    }

    public BitConstant(boolean flag) {
        this.aByte = flag ? (byte)1 : 0;
    }

    public Byte getaByte() {
        return aByte;
    }

    public void setaByte(Byte aByte) {
        this.aByte = aByte;
    }
}
