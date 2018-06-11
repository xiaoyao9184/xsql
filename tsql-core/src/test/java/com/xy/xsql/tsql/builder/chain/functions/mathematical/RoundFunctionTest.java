package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.mathematical.Round;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_round;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class RoundFunctionTest {

    /**
     * ROUND(123.9994, 3)
     */
    public Round exampleA = f_round(
            c_number(123.9994),
            c_number(3)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getNumericExpression().getClass(), NumberConstant.class);
        assertEquals(exampleA.getLength().getClass(), NumberConstant.class);
    }

    /**
     * ROUND(123.45, -2)
     */
    public Round exampleB = f_round(
            c_number(123.45),
            c_number(-2)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getNumericExpression().getClass(), NumberConstant.class);
        assertEquals(exampleB.getLength().getClass(), NumberConstant.class);
    }

    /**
     * ROUND(150.75, 0, 1)
     */
    public Round exampleC = f_round(
            c_number(150.75),
            c_number(0),
            c_number(1)
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getNumericExpression().getClass(), NumberConstant.class);
        assertEquals(exampleC.getLength().getClass(), NumberConstant.class);
        assertEquals(exampleC.getFunction().getClass(), NumberConstant.class);
    }

}