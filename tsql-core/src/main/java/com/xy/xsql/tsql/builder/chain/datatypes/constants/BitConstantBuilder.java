package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.simple.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.BitConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BitConstantBuilder extends CodeBuilder<BitConstant> {

    public BitConstantBuilder() {
        super(new BitConstant());
    }

    public BitConstantBuilder(BitConstant target) {
        super(target);
    }

    public BitConstantBuilder withByte(boolean flag) {
        target.setaByte(flag ? (byte)1 : 0);
        return this;
    }

}
