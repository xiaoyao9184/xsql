package com.xy.xsql.tsql.builder.chain.functions.mathematical;

import com.xy.xsql.tsql.model.datatypes.constants.MoneyConstant;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.mathematical.Floor;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_money;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.MathematicalFunctions.f_floor;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class FloorFunctionTest {

    /**
     * FLOOR(123.45)
     */
    public Floor example1 = f_floor(
            c_number(123.45)
    );

    /**
     * FLOOR($123.45)
     */
    public Floor example2 = f_floor(
            c_money("$",123.45)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getNumericExpression().getClass(), NumberConstant.class);
        assertEquals(example2.getNumericExpression().getClass(), MoneyConstant.class);
    }

}