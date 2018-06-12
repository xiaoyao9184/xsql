package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.Key_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_key_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class KeyIdFunctionTest {


    /**
     * KEY_ID('ABerglundKey1')
     */
    public Key_Id exampleA = f_key_id(
            c_string("ABerglundKey1")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getKeyName().getString(), "ABerglundKey1");
    }

    /**
     * KEY_ID('#ABerglundKey2')
     */
    public Key_Id exampleB = f_key_id(
            c_string("#ABerglundKey2")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getKeyName().getString(), "#ABerglundKey2");
    }

}