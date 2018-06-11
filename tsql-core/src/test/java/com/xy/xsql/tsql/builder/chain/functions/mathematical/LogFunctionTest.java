package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.mathematical.Exp;
import com.xy.xsql.tsql.model.functions.mathematical.Log;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_exp;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_log;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LogFunctionTest {

    /**
     * LOG(@var)
     */
    public Log example1 = f_log(
            e_variable("var")
    );

    /**
     * LOG (EXP (10))
     */
    public Log example2 = f_log(
            f_exp(
                    c_number(10)
            )
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), LocalVariable.class);
        assertEquals(example2.getFloatExpression().getClass(), Exp.class);
    }

}