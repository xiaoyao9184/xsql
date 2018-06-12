package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Sin;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_sin;
import static org.junit.Assert.assertEquals;

/*reated by xiaoyao9184 on 2018/6/5.
 */
public class SinFunctionTest {

    /**
     * SIN(@value)
     */
    public Sin example1 = f_sin(
            e_variable("value")
    );

    /**
     * SIN(45.175643)
     */
    public Sin example2 = f_sin(
            c_number(45.175643)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getFloatExpression().getClass(), NumberConstant.class);
    }

}