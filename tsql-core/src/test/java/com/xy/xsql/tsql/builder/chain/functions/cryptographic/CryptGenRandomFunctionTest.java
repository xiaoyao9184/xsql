package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.Crypt_Gen_Random;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_bin;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_crypt_gen_random;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CryptGenRandomFunctionTest {


    /**
     * CRYPT_GEN_RANDOM(50)
     */
    public Crypt_Gen_Random example1 = f_crypt_gen_random(50);

    /**
     * CRYPT_GEN_RANDOM(4, 0x25F18060)
     */
    public Crypt_Gen_Random example2 = f_crypt_gen_random(4,
            e_bin(new byte[]{ 0x25, (byte) 0xF1, (byte) 0x80, 0x60}));

    @Test
    public void testExample1(){
        assertEquals(example1.getLength(),new Integer(50));
        assertEquals(example2.getLength(),new Integer(4));
        assertArrayEquals(example2.getSeed().getData(),new byte[]{ 0x25, (byte) 0xF1, (byte) 0x80, 0x60});
    }
}