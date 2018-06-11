package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByPassPhrase;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._varbinary;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_convert;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_decryptbypassphrase;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DecryptByPassPhraseFunctionTest {


    /**
     * DecryptByPassphrase(@PassphraseEnteredByUser, CardNumber_EncryptedbyPassphrase, 1
     , CONVERT(varbinary, CreditCardID))
     */
    public DecryptByPassPhrase example1 = f_decryptbypassphrase(
            e_variable("PassphraseEnteredByUser"),
            e_variable("CardNumber_EncryptedbyPassphrase"),
            e_number(1),
            f_convert(_varbinary(),c("CreditCardID"))
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getPassphraseVariable().getName(), "PassphraseEnteredByUser");
        assertEquals(example1.getCiphertextVariable().getName(), "CardNumber_EncryptedbyPassphrase");
        assertEquals(example1.getAddAuthenticator().getClass(), NumberConstant.class);
        assertEquals(example1.getAuthenticator().getClass(), Convert.class);
    }
}