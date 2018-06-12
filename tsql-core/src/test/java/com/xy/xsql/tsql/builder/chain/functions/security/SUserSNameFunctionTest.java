package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.BinaryConstant;
import com.xy.xsql.tsql.model.functions.security.SUser_SName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_bin;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_suser_sname;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SUserSNameFunctionTest {


    /**
     * SUSER_SNAME()
     */
    public SUser_SName exampleA = f_suser_sname();

    @Test
    public void testExampleA() {
        assertNull(exampleA.getServerUserSId());
    }

    /**
     * SUSER_SNAME(0x010500000000000515000000a065cf7e784b9b5fe77c87705a2e0000)
     */
    public SUser_SName exampleB = f_suser_sname(
            c_bin(
                    javax.xml.bind.DatatypeConverter.parseHexBinary("010500000000000515000000a065cf7e784b9b5fe77c87705a2e0000")
            )
    );

    @Test
    public void testExampleB() {
        assertEquals(exampleB.getServerUserSId().getClass(), BinaryConstant.class);
    }

    /**
     * SUSER_SNAME(0x01)
     */
    public SUser_SName exampleE = f_suser_sname(
            c_bin(new byte[]{ 0x01 })
    );

    @Test
    public void testExampleE() {
        assertEquals(exampleE.getServerUserSId().getClass(), BinaryConstant.class);
    }

}