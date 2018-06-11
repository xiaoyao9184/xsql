package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.cryptographic.Cert_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByCert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_cert_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_verifysignedbycert;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class VerifySignedByCertFunctionTest {


    /**
     * VerifySignedByCert( Cert_Id( 'Shipping04' ),
     Signed_Data, DataSignature )
     */
    public VerifySignedByCert exampleA = f_verifysignedbycert(
            f_cert_id(c_string("Shipping04")),
            e_variable("Signed_Data"),
            c("DataSignature")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCertId().getClass(), Cert_Id.class);
        assertEquals(exampleA.getSignedData().getName(), "Signed_Data");
        assertEquals(exampleA.getSignature().getClass(), ColumnName.class);
    }

    /**
     * VerifySignedByCert( Cert_Id( 'Shipping04' ), Data,
     DataSignature )
     */
    public VerifySignedByCert exampleB = f_verifysignedbycert(
            f_cert_id(c_string("Shipping04")),
            e_variable("Data"),
            c("DataSignature")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCertId().getClass(), Cert_Id.class);
        assertEquals(exampleB.getSignedData().getName(), "Data");
        assertEquals(exampleB.getSignature().getClass(), ColumnName.class);
    }

}