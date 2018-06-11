package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Sqrt;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_sqrt;
import static org.junit.Assert.assertEquals;

/*reated by xiaoyao9184 on 2018/6/5.
 */
public class SqrtFunctionTest {

    /**
     * SQRT(@myvalue)
     */
    public Sqrt example1 = f_sqrt(
            e_variable("value")
    );

    /**
     * SQRT(1.00)
     */
    public Sqrt example2 = f_sqrt(
            c_number(1.00)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getFloatExpression().getClass(), NumberConstant.class);
    }

}