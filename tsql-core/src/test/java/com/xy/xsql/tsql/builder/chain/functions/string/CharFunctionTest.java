package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.string.Ascii;
import com.xy.xsql.tsql.model.functions.string.Char;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CharFunctionTest {


    /**
     * CHAR(ASCII(SUBSTRING(@string, @position, 1)))
     */
    public Char exampleA = f_char(
            f_ascii(
            f_substring(
                    e_variable("string"),
                    e_variable("position"),
                    c_number(1)
            ))
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getIntegerExpression().getClass(), Ascii.class);
    }

    /**
     * CHAR(13)
     */
    public Char exampleB = f_char(
            c_number(13)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getIntegerExpression().getClass(), NumberConstant.class);
    }

}