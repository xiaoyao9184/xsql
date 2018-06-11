package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.datetime.Datetime2FromParts;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datetime2fromparts;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class Datetime2FromPartsFunctionTest {


    /**
     * DATETIME2FROMPARTS ( 2010, 12, 31, 23, 59, 59, 0, 0 )
     */
    public Datetime2FromParts exampleA = f_datetime2fromparts(
            c_number(2010),
            c_number(12),
            c_number(31),
            c_number(23),
            c_number(59),
            c_number(59),
            c_number(0),
            c_number(0)
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
        assertEquals(exampleA.getPrecision().getClass(), NumberConstant.class);
    }

    /**
     * DATETIME2FROMPARTS ( 2011, 8, 15, 14, 23, 44, 5, 1 )
     */
    public Datetime2FromParts exampleB = f_datetime2fromparts(
            c_number(2011),
            c_number(8),
            c_number(15),
            c_number(14),
            c_number(23),
            c_number(44),
            c_number(5),
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
        assertEquals(exampleB.getPrecision().getClass(), NumberConstant.class);
    }

}