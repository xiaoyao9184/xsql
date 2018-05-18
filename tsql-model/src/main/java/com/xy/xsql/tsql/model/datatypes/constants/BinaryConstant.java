package com.xy.xsql.tsql.model.datatypes.constants;

import com.xy.xsql.tsql.model.datatypes.constants.Constant;
import com.xy.xsql.tsql.model.expression.Expression;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;

/**
 * Binary constants
 * uniqueidentifier constants(output like Binary string)
 * Created by xiaoyao9184 on 2017/3/17.
 */
public class BinaryConstant implements Constant, Expression {

    private byte[] data;

    public BinaryConstant(byte[] data){
        this.data = data;
    }

    public BinaryConstant(UUID uuid){
        data = new byte[16];
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


    }


    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "0x" +
                (data.length == 0 ?
                        "" :
                        javax.xml.bind.DatatypeConverter.printHexBinary(data));
    }
}
