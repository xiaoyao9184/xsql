package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.string.SubString;
import com.xy.xsql.tsql.model.functions.string.Unicode;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_substring;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_unicode;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class UnicodeFunctionTest {


    /**
     * UNICODE(@nstring)
     */
    public Unicode exampleA = f_unicode(
            e_variable("nstring")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getNcharacterExpression().getClass(), LocalVariable.class);
    }

    /**
     * UNICODE(SUBSTRING(@nstring, @position, 1))
     */
    public Unicode exampleB = f_unicode(
            f_substring(
                    e_variable("nstring"),
                    e_variable("position"),
                    c_number(1))
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getNcharacterExpression().getClass(), SubString.class);
    }

}