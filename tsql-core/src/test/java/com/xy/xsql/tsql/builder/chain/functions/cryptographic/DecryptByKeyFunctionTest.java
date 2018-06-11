package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByKey;
import com.xy.xsql.tsql.model.functions.cryptographic.HashBytes;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._varbinary;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_convert;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_decryptbykey;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_hashbytes;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DecryptByKeyFunctionTest {


    /**
     * DecryptByKey(EncryptedNationalID)
     */
    public DecryptByKey exampleA = f_decryptbykey(
            e_variable("EncryptedNationalID")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCiphertextVariable().getName(), "EncryptedNationalID");
    }

    /**
     * DecryptByKey(CardNumber_Encrypted, 1 ,
     HashBytes('SHA1', CONVERT(varbinary, CreditCardID)))
     */
    public DecryptByKey exampleB = f_decryptbykey(
            e_variable("CardNumber_Encrypted"),
            e_number(1),
            f_hashbytes(HashBytes.Algorithm.SHA1,f_convert(_varbinary(),c("CreditCardID")))
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCiphertextVariable().getName(), "CardNumber_Encrypted");
        assertEquals(exampleB.getAddAuthenticator().getClass(), NumberConstant.class);
        assertEquals(exampleB.getAuthenticator().getClass(), HashBytes.class);
    }
}