package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import com.xy.xsql.tsql.model.functions.mathematical.Power;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.DataTypes._float;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.ConversionFunctions.f_cast;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_power;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class PowerFunctionTest {

    /**
     * POWER(@input1, 3)
     */
    public Power exampleA = f_power(
            e_variable("input1"),
            c_number(3)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(exampleA.getY().getClass(), NumberConstant.class);
    }

    /**
     * POWER(CAST(2.0 AS float), -100.0)
     */
    public Power exampleB = f_power(
            f_cast(
                    c_number(2.0),
                    _float()
            ),
            c_number(-100.0)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getFloatExpression().getClass(), Cast.class);
        assertEquals(exampleB.getY().getClass(), NumberConstant.class);
    }

    /**
     * POWER(@value, @counter)
     */
    public Power exampleC = f_power(
            e_variable("value"),
            e_variable("counter")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(exampleC.getY().getClass(), LocalVariable.class);
    }

    /**
     * POWER(2.0, 3)
     */
    public Power exampleD = f_power(
            c_number(2.0),
            c_number(3)
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getFloatExpression().getClass(), NumberConstant.class);
        assertEquals(exampleD.getY().getClass(), NumberConstant.class);
    }

}