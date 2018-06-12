package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.security.CertEncoded;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_certencoded;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CertEncodedFunctionTest {


    /**
     * CERTENCODED(CERT_ID('Shipping04'))
     */
    public CertEncoded exampleA = f_certencoded(
            f_cert_id(c_string("Shipping04"))
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCertId().getClass(), Cert_Id.class);
    }

    /**
     * CERTENCODED(CERT_ID('SOURCE_CERT'))
     */
    public CertEncoded exampleB = f_certencoded(
            f_cert_id(c_string("SOURCE_CERT"))
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCertId().getClass(), Cert_Id.class);
    }


}