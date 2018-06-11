package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import com.xy.xsql.tsql.model.functions.cryptographic.EncryptByPassPhrase;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._varchar;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_convert;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_encryptbypassphrase;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class EncryptByPassPhraseFunctionTest {


    /**
     * EncryptByPassPhrase(@PassphraseEnteredByUser
     , CardNumber, 1, CONVERT( varbinary, CreditCardID))
     */
    public EncryptByPassPhrase example1 = f_encryptbypassphrase(
            e_variable("PassphraseEnteredByUser"),
            e_variable("CardNumber"),
            e_number(1),
            f_convert(_varchar(),c("CreditCardID"))
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getPassphraseVariable().getName(), "PassphraseEnteredByUser");
        assertEquals(example1.getCleartextVariable().getName(), "CardNumber");
        assertEquals(example1.getAddAuthenticator().getClass(), NumberConstant.class);
        assertEquals(example1.getAuthenticator().getClass(), Convert.class);
    }

}