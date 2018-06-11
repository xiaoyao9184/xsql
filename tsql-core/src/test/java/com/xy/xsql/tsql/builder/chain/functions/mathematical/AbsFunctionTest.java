package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Abs;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_abs;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AbsFunctionTest {


    /**
     * ABS(-1.0)
     */
    public Abs example1 = f_abs(
            c_number(-1.0)
    );

    /**
     * ABS(@i)
     */
    public Abs example2 = f_abs(
            e_variable("i")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getNumericExpression().getClass(), NumberConstant.class);
        assertEquals(example2.getNumericExpression().getClass(), LocalVariable.class);
    }

}