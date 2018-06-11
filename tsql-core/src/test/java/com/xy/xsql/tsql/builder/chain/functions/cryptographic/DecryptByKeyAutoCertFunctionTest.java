package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKeyAutoCert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_null;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_decryptbykeyautocert;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DecryptByKeyAutoCertFunctionTest {


    /**
     * DecryptByKeyAutoCert ( cert_ID('HumanResources037') , NULL ,EncryptedNationalIDNumber)
     */
    public DecryptByKeyAutoCert example1 = f_decryptbykeyautocert(
            f_cert_id(c_string("HumanResources037")),
            e_null(),
            e_variable("EncryptedNationalIDNumber")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getCertId().getClass(), Cert_Id.class);
        assertEquals(example1.getCertPassword().getClass(), Null.class);
        assertEquals(example1.getCiphertextVariable().getName(),"EncryptedNationalIDNumber");
    }
}