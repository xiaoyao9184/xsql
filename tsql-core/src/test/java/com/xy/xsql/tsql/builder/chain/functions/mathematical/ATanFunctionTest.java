package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.mathematical.ATan;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_atan;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ATanFunctionTest {

    /**
     * ATAN(-45.01)
     */
    public ATan example1 = f_atan(
            c_number(-45.01)
    );

    /**
     * ATAN(0)
     */
    public ATan example2 = f_atan(
            c_number(0)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), NumberConstant.class);
        assertEquals(example2.getFloatExpression().getClass(), NumberConstant.class);
    }

}