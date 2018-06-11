package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.datetime.TimeFromParts;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_timefromparts;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class TimeFromPartsFunctionTest {


    /**
     * TIMEFROMPARTS ( 23, 59, 59, 0, 0 )
     */
    public TimeFromParts exampleA = f_timefromparts(
            c_number(23),
            c_number(59),
            c_number(59),
            c_number(0),
            c_number(0)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getHour().getClass(), NumberConstant.class);
        assertEquals(exampleA.getMinute().getClass(), NumberConstant.class);
        assertEquals(exampleA.getSeconds().getClass(), NumberConstant.class);
        assertEquals(exampleA.getFractions().getClass(), NumberConstant.class);
        assertEquals(exampleA.getPrecision().getClass(), NumberConstant.class);
    }


}