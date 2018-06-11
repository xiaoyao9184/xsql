package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Trim;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_trim;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TrimFunctionTest {


    /**
     * TRIM( '     test    ')
     */
    public Trim exampleA = f_trim(
            c_string("     test    ")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getString().getClass(), StringConstant.class);
    }

    /**
     * TRIM( '.,! ' FROM  '#     test    .')
     */
    public Trim exampleB = f_trim(
            c_string(".,! "),
            c_string("#     test    .")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCharacters().getClass(), StringConstant.class);
        assertEquals(exampleB.getString().getClass(), StringConstant.class);
    }

}