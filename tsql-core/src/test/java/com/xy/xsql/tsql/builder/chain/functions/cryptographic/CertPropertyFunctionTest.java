package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.CertProperty;
import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_certproperty;
import static com.xy.xsql.tsql.model.functions.cryptographic.CertProperty.Properties.Subject;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CertPropertyFunctionTest {


    /**
     * CertProperty( Cert_ID('Marketing19'), 'Subject')
     */
    public CertProperty example1 = f_certproperty(
            f_cert_id(c_string("Marketing19")),
            Subject
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getCertId().getClass(), Cert_Id.class);
        assertEquals(example1.getProperties(), CertProperty.Properties.Subject);
    }
}