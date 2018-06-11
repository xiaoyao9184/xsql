package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.string.RTrim;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_rtrim;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class RTrimFunctionTest {


    /**
     * RTRIM('Removes trailing spaces.   ')
     */
    public RTrim exampleA = f_rtrim(
            c_string("Removes trailing spaces.   ")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCharacterExpression().getClass(), StringConstant.class);
    }

    /**
     * RTRIM('Four spaces are after the period in this sentence.    ')
     */
    public RTrim exampleB = f_rtrim(
            c_string("Four spaces are after the period in this sentence.    ")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCharacterExpression().getClass(), StringConstant.class);
    }

    /**
     * RTRIM(@string_to_trim)
     */
    public RTrim exampleC = f_rtrim(
            e_variable("string_to_trim")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getCharacterExpression().getClass(), LocalVariable.class);
    }

}