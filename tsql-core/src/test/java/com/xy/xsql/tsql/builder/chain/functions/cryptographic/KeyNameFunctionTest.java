package com.xy.xsql.tsql.builder.chain.functions.cryptographic;

import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.CryptographicFunctions.f_key_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class KeyNameFunctionTest {


    /**
     * KEY_NAME(@guid)
     */
    public Key_Name exampleA = f_key_name(
            e_variable("guid")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCiphertext().getClass(), LocalVariable.class);
    }

}