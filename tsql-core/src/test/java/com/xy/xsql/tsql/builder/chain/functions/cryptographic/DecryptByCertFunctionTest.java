package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByCert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_decryptbycert;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DecryptByCertFunctionTest {


    /**
     * DecryptByCert(Cert_Id('JanainaCert02'),
     ProtectedData, N'pGFD4bb925DGvbd2439587y')
     */
    public DecryptByCert example1 = f_decryptbycert(
            f_cert_id(c_string("JanainaCert02")),
            e_variable("ProtectedData"),
            c_n_string("pGFD4bb925DGvbd2439587y")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getCertificateId().getClass(), Cert_Id.class);
        assertEquals(example1.getCiphertextVariable().getName(),"ProtectedData");
        assertEquals(example1.getCertPassword().getString(),"pGFD4bb925DGvbd2439587y");
    }
}