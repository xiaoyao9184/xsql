package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.mathematical.Degrees;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_division;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_degrees;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_pi;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DegreesFunctionTest {

    /**
     * DEGREES((PI()/2))
     */
    public Degrees example1 = f_degrees(
            e_division(
                    f_pi(),
                    c_number(2)
            )

    );

    @Test
    public void testExample(){
        assertEquals(example1.getNumericExpression().getClass(), BinaryExpression.class);
    }

}