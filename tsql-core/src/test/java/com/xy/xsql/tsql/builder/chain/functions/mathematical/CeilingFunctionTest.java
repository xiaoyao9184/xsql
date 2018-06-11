package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.MoneyConstant;
import com.xy.xsql.tsql.model.functions.mathematical.Ceiling;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_money;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_ceiling;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CeilingFunctionTest {

    /**
     * CEILING($123.45)
     */
    public Ceiling example1 = f_ceiling(
            c_money(123.45)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getNumericExpression().getClass(), MoneyConstant.class);
    }

}