package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.functions.string.NChar;
import com.xy.xsql.tsql.model.functions.string.Unicode;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class NCharFunctionTest {


    /**
     * NCHAR(UNICODE(SUBSTRING(@nstring, 2, 1)))
     */
    public NChar exampleA = f_nchar(
            f_unicode(f_substring(e_variable("nstring"),c_number(2),c_number(1)))
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getIntegerExpression().getClass(), Unicode.class);
    }

    /**
     * NCHAR(UNICODE(SUBSTRING(@nstring, @position, 1)))
     */
    public NChar exampleB = f_nchar(
            f_unicode(f_substring(e_variable("nstring"),e_variable("position"),c_number(1)))
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getIntegerExpression().getClass(), Unicode.class);
    }

}