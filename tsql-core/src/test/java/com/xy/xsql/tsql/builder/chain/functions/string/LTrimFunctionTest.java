package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.string.LTrim;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_ltrim;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LTrimFunctionTest {


    /**
     * LTRIM('     Five spaces are at the beginning of this string.')
     */
    public LTrim exampleA = f_ltrim(
            c_string("     Five spaces are at the beginning of this string.")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCharacterExpression().getClass(), StringConstant.class);
    }

    /**
     * LTRIM(@string_to_trim)
     */
    public LTrim exampleB = f_ltrim(
            e_variable("string_to_trim")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCharacterExpression().getClass(), LocalVariable.class);
    }

}