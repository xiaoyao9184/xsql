package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.string.CharIndex;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_charindex;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CharIndexFunctionTest {


    /**
     * CHARINDEX('bicycle', @document)
     */
    public CharIndex exampleA = f_charindex(
            c_string("bicycle"),
            e_variable("document")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpressionToFind().getClass(), StringConstant.class);
        assertEquals(exampleA.getExpressionToSearch().getClass(), LocalVariable.class);
    }

    /**
     * CHARINDEX('vital', @document, 5)
     */
    public CharIndex exampleB = f_charindex(
            c_string("vital"),
            e_variable("document"),
            c_number(5)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpressionToFind().getClass(), StringConstant.class);
        assertEquals(exampleB.getExpressionToSearch().getClass(), LocalVariable.class);
        assertEquals(exampleB.getStartLocation().getClass(), NumberConstant.class);
    }

    /**
     * CHARINDEX ( 'TEST',
     'This is a Test'
     COLLATE Latin1_General_CS_AS)
     */
    public CharIndex exampleD = f_charindex(
            c_string("TEST"),
            //TODO String with COLLATE Expression
            c_string("This is a Test")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getExpressionToFind().getClass(), StringConstant.class);
        assertEquals(exampleD.getExpressionToSearch().getClass(), StringConstant.class);
    }

}