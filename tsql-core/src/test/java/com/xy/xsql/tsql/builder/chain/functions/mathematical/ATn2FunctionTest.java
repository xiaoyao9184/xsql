package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.ATn2;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_atn2;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ATn2FunctionTest {

    /**
     * ATN2(@y, @x)
     */
    public ATn2 example1 = f_atn2(
            e_variable("y"),
            e_variable("x")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(example1.getFloatExpression2().getClass(), LocalVariable.class);
    }

}