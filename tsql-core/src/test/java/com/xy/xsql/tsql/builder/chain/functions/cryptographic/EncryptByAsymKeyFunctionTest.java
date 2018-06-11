package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByAsymKey;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkey_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_encryptbyasymkey;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class EncryptByAsymKeyFunctionTest {


    /**
     * EncryptByAsymKey(AsymKey_ID('JanainaAsymKey02'), @cleartext)
     */
    public EncryptByAsymKey example1 = f_encryptbyasymkey(
            f_asymkey_id(c_string("JanainaAsymKey02")),
            e_variable("cleartext")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getAsymKeyId().getClass(), AsymKey_Id.class);
        assertEquals(example1.getPlaintextVariable().getName(), "cleartext");
    }

}