package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoAsymKey;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_null;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkey_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_decryptbykeyautoasymkey;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DecryptByKeyAutoAsymKeyFunctionTest {


    /**
     * DecryptByKeyAutoAsymKey ( AsymKey_ID('SSN_AKey') , NULL ,EncryptedNationalIDNumber2)
     */
    public DecryptByKeyAutoAsymKey example1 = f_decryptbykeyautoasymkey(
            f_asymkey_id(c_string("SSN_AKey")),
            e_null(),
            e_variable("EncryptedNationalIDNumber2")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getAkeyId().getClass(), AsymKey_Id.class);
        assertEquals(example1.getAkeyPassword().getClass(), Null.class);
        assertEquals(example1.getCiphertextVariable().getName(),"EncryptedNationalIDNumber2");
    }
}