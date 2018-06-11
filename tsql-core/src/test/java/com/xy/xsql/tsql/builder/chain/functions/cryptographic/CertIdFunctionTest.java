package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CertIdFunctionTest {


    /**
     * Cert_ID('ABerglundCert3')
     */
    public Cert_Id example1 = f_cert_id(c_string("ABerglundCert3"));

    @Test
    public void testExample1(){
        assertEquals(example1.getCertName().getString(), "ABerglundCert3");
    }
}