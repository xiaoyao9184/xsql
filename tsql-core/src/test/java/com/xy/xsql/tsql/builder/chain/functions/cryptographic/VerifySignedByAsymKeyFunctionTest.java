package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.VerifySignedByAsymKey;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkey_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_verifysignedbyasymkey;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class VerifySignedByAsymKeyFunctionTest {


    /**
     * VerifySignedByAsymKey( AsymKey_Id( 'WillisKey74' ), SignedData,
     DataSignature )
     */
    public VerifySignedByAsymKey exampleA = f_verifysignedbyasymkey(
            f_asymkey_id(c_string("WillisKey74")),
            e_variable("SignedData"),
            c("DataSignature")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getAsymKeyId().getClass(), AsymKey_Id.class);
        assertEquals(exampleA.getCleartext().getName(), "SignedData");
        assertEquals(exampleA.getSignature().getClass(), ColumnName.class);
    }

    /**
     * VerifySignedByAsymKey( AsymKey_Id( 'WillisKey74' ), Data,
     DataSignature )
     */
    public VerifySignedByAsymKey exampleB = f_verifysignedbyasymkey(
            f_asymkey_id(c_string("WillisKey74")),
            e_variable("Data"),
            c("DataSignature")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getAsymKeyId().getClass(), AsymKey_Id.class);
        assertEquals(exampleB.getCleartext().getName(), "Data");
        assertEquals(exampleB.getSignature().getClass(), ColumnName.class);
    }

}