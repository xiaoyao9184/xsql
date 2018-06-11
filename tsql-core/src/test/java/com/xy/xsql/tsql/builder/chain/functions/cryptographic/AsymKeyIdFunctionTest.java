package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkey_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AsymKeyIdFunctionTest {


    /**
     * ASYMKEY_ID('ABerglundKey11')
     */
    public AsymKey_Id example1 = f_asymkey_id(c_string("ABerglundKey11"));

    @Test
    public void testExample1(){
        assertEquals(example1.getAsymKeyName().getString(), "ABerglundKey11");
    }
}