package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByCert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_signbycert;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SignByCertFunctionTest {


    /**
     * SignByCert( Cert_Id( 'ABerglundCert07' ),
     @SensitiveData, N'pGFD4bb925DGvbd2439587y' )
     */
    public SignByCert example1 = f_signbycert(
            f_cert_id(c_string("ABerglundCert07")),
            e_variable("SensitiveData"),
            c_n_string("pGFD4bb925DGvbd2439587y")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getCertificateId().getClass(), Cert_Id.class);
        assertEquals(example1.getCleartext().getClass(), LocalVariable.class);
        assertEquals(example1.getPassword().getClass(), StringConstant.class);
    }

}