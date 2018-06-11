package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Sign;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_sign;
import static org.junit.Assert.assertEquals;

/*reated by xiaoyao9184 on 2018/6/5.
 */
public class SignFunctionTest {

    /**
     * SIGN(@value)
     */
    public Sign example1 = f_sign(
            e_variable("value")
    );

    /**
     * SIGN(-125)
     */
    public Sign example2 = f_sign(
            c_number(-125)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getNumericExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getNumericExpression().getClass(), NumberConstant.class);
    }

}