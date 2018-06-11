package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByAsymKey;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkey_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_decryptbyasymkey;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DecryptByAsymKeyFunctionTest {


    /**
     * DecryptByAsymKey( AsymKey_Id('JanainaAsymKey02'),
     ProtectedData, N'pGFD4bb925DGvbd2439587y' )
     */
    public DecryptByAsymKey example1 = f_decryptbyasymkey(
            f_asymkey_id(c_string("JanainaAsymKey02")),
            e_variable("ProtectedData"),
            c_n_string("pGFD4bb925DGvbd2439587y")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getAsymKeyId().getClass(), AsymKey_Id.class);
        assertEquals(example1.getCiphertextVariable().getName(),"ProtectedData");
        assertEquals(example1.getAsymKeyPassword().getString(),"pGFD4bb925DGvbd2439587y");
    }
}