package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByKey;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_GUID;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._varbinary;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_convert;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_encryptbykey;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_key_guid;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class EncryptByKeyFunctionTest {


    /**
     * EncryptByKey(Key_GUID('SSN_Key_01'), NationalIDNumber)
     */
    public EncryptByKey exampleA = f_encryptbykey(
            f_key_guid(c_string("SSN_Key_01")),
            e_variable("NationalIDNumber")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getKeyGUID().getClass(), Key_GUID.class);
        assertEquals(exampleA.getCleartextVariable().getName(), "NationalIDNumber");
    }

    /**
     * EncryptByKey(Key_GUID('CreditCards_Key11'),
     CardNumber, 1, CONVERT( varbinary, CreditCardID) )
     */
    public EncryptByKey exampleB = f_encryptbykey(
            f_key_guid(c_string("CreditCards_Key11")),
            e_variable("CardNumber"),
            e_number(1),
            f_convert(_varbinary(),c("CreditCardID"))
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getKeyGUID().getClass(), Key_GUID.class);
        assertEquals(exampleB.getCleartextVariable().getName(), "CardNumber");
        assertEquals(exampleB.getAddAuthenticator().getClass(), NumberConstant.class);
        assertEquals(exampleB.getAuthenticator().getClass(), Convert.class);
    }
}