package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Cot;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_cot;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CotFunctionTest {

    /**
     * COT(@angle)
     */
    public Cot example1 = f_cot(
            e_variable("angle")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
    }

}