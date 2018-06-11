package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.datetime.DatetimeOffsetFromParts;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datetimeoffsetfromparts;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DatetimeOffsetFromPartsFunctionTest {


    /**
     * DATETIMEOFFSETFROMPARTS ( 2010, 12, 31, 14, 23, 23, 0, 12, 0, 7 )
     */
    public DatetimeOffsetFromParts exampleA = f_datetimeoffsetfromparts(
            c_number(2010),
            c_number(12),
            c_number(31),
            c_number(14),
            c_number(23),
            c_number(23),
            c_number(0),
            c_number(12),
            c_number(0),
            c_number(7)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getYear().getClass(), NumberConstant.class);
        assertEquals(exampleA.getMonth().getClass(), NumberConstant.class);
        assertEquals(exampleA.getDay().getClass(), NumberConstant.class);
        assertEquals(exampleA.getHour().getClass(), NumberConstant.class);
        assertEquals(exampleA.getMinute().getClass(), NumberConstant.class);
        assertEquals(exampleA.getSeconds().getClass(), NumberConstant.class);
        assertEquals(exampleA.getFractions().getClass(), NumberConstant.class);
        assertEquals(exampleA.getHourOffset().getClass(), NumberConstant.class);
        assertEquals(exampleA.getMinuteOffset().getClass(), NumberConstant.class);
        assertEquals(exampleA.getPrecision().getClass(), NumberConstant.class);
    }

    /**
     * DATETIMEOFFSETFROMPARTS ( 2011, 8, 15, 14, 30, 00, 5, 12, 30, 1 )
     */
    public DatetimeOffsetFromParts exampleB = f_datetimeoffsetfromparts(
            c_number(2011),
            c_number(8),
            c_number(15),
            c_number(14),
            c_number(30),
            c_number(00),
            c_number(5),
            c_number(12),
            c_number(30),
            c_number(1)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getYear().getClass(), NumberConstant.class);
        assertEquals(exampleB.getMonth().getClass(), NumberConstant.class);
        assertEquals(exampleB.getDay().getClass(), NumberConstant.class);
        assertEquals(exampleB.getHour().getClass(), NumberConstant.class);
        assertEquals(exampleB.getMinute().getClass(), NumberConstant.class);
        assertEquals(exampleB.getSeconds().getClass(), NumberConstant.class);
        assertEquals(exampleB.getFractions().getClass(), NumberConstant.class);
        assertEquals(exampleB.getHourOffset().getClass(), NumberConstant.class);
        assertEquals(exampleB.getMinuteOffset().getClass(), NumberConstant.class);
        assertEquals(exampleB.getPrecision().getClass(), NumberConstant.class);
    }


}