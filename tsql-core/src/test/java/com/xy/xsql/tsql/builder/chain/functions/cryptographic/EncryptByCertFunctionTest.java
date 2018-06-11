package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByCert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_encryptbycert;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class EncryptByCertFunctionTest {


    /**
     * EncryptByCert(Cert_ID('JanainaCert02'), @cleartext) )
     */
    public EncryptByCert example1 = f_encryptbycert(
            f_cert_id(c_string("JanainaCert02")),
            e_variable("cleartext")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getCertificateId().getClass(), Cert_Id.class);
        assertEquals(example1.getCleartextVariable().getName(), "cleartext");
    }

}