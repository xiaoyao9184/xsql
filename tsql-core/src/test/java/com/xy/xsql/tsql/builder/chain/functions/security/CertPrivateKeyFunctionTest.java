package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.security.CertPrivateKey;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_certprivatekey;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CertPrivateKeyFunctionTest {


    /**
     * CERTPRIVATEKEY(CERT_ID('Shipping04'), 'jklalkaa/; uia3dd')
     */
    public CertPrivateKey example1 = f_certprivatekey(
            f_cert_id(c_string("Shipping04")),
            c_string("jklalkaa/; uia3dd")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCertId().getClass(), Cert_Id.class);
        assertEquals(example1.getEncryptionPassword().getString(), "jklalkaa/; uia3dd");
    }

}