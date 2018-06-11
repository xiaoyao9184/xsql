package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.cryptographic.AsymKey_Id;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByAsymKey;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_asymkey_id;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_signbyasymkey;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SignByAsymKeyFunctionTest {


    /**
     * SignByAsymKey( AsymKey_Id( 'PrimeKey' ),
     @clear_text_data, N'pGFD4bb925DGvbd2439587y' )
     */
    public SignByAsymKey example1 = f_signbyasymkey(
            f_asymkey_id(c_string("PrimeKey")),
            e_variable("clear_text_data"),
            c_n_string("pGFD4bb925DGvbd2439587y")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getAsymKeyId().getClass(), AsymKey_Id.class);
        assertEquals(example1.getPlaintext().getClass(), LocalVariable.class);
        assertEquals(example1.getPassword().getClass(), StringConstant.class);
    }

}