package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.Key_GUID;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_key_guid;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class KeyGUIDFunctionTest {


    /**
     * Key_GUID('ABerglundKey1')
     */
    public Key_GUID exampleA = f_key_guid(
            c_string("ABerglundKey1")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getKeyName().getString(), "ABerglundKey1");
    }

}