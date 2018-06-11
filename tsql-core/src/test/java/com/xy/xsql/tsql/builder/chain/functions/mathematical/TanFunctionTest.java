package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.mathematical.Tan;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_division;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_pi;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_tan;
import static org.junit.Assert.assertEquals;

/*reated by xiaoyao9184 on 2018/6/5.
 */
public class TanFunctionTest {

    /**
     * TAN(PI()/2)
     */
    public Tan example1 = f_tan(
            e_division(
                    f_pi(),
                    c_number(2)
            )
    );

    /**
     * TAN(.45)
     */
    public Tan example2 = f_tan(
            c_number(.45)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), BinaryExpression.class);
        assertEquals(example2.getFloatExpression().getClass(), NumberConstant.class);
    }

}