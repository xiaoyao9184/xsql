package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.ASin;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_asin;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ASinFunctionTest {

    /**
     * ASIN(@angle)
     */
    public ASin example1 = f_asin(
            e_variable("angle")
    );

    /**
     * ASIN(1.1472738)
     */
    public ASin example2 = f_asin(
            c_number(1.1472738)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getFloatExpression().getClass(), NumberConstant.class);
    }

}