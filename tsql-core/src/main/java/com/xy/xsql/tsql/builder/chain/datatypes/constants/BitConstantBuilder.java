package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.BitConstant;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BitConstantBuilder extends CodeBuilder<BitConstant> {

    public BitConstantBuilder() {
        super(new BitConstant());
    }

    public BitConstantBuilder(BitConstant tar) {
        super(tar);
    }

    public BitConstantBuilder withByte(boolean flag) {
        target.setaByte(flag ? (byte)1 : 0);
        return this;
    }

}
