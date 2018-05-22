package com.xy.xsql.tsql.builder.chain.datatypes.constants;

import com.xy.xsql.core.builder.CodeBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

/**
 * Created by xiaoyao9184 on 2018/5/22.
 */
public class BinaryConstantBuilder extends CodeBuilder<BinaryConstant> {

    public BinaryConstantBuilder() {
        super(new BinaryConstant());
    }

    public BinaryConstantBuilder(BinaryConstant tar) {
        super(tar);
    }

    public BinaryConstantBuilder withData(byte[] data) {
        target.setData(data);
        return this;
    }

    public BinaryConstantBuilder withData(UUID uuid) {
        byte[] data = new byte[16];
        ByteBuffer.wrap(data,8,8)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(uuid.getLeastSignificantBits());

        long l = uuid.getMostSignificantBits();
        byte[] most = ByteBuffer
                .allocate(Long.BYTES)
                .putLong(l)
                .array();

        ByteBuffer.wrap(data,0,4)
                .put(most,3,1)
                .put(most,2,1)
                .put(most,1,1)
                .put(most,0,1);
        ByteBuffer.wrap(data,4,2)
                .put(most,5,1)
                .put(most,4,1);
        ByteBuffer.wrap(data,6,2)
                .put(most,7,1)
                .put(most,6,1);

        target.setData(data);
        return this;
    }

}
