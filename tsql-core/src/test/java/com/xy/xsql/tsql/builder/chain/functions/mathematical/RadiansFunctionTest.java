package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Radians;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_radians;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class RadiansFunctionTest {

    /**
     * RADIANS(1e-307)
     */
    public Radians exampleA = f_radians(
            c_number(1e-307)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getNumericExpression().getClass(), NumberConstant.class);
    }

    /**
     * RADIANS(@angle)
     */
    public Radians exampleB = f_radians(
            e_variable("angle")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getNumericExpression().getClass(), LocalVariable.class);
    }

}