package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.datetime.SmallDatetimeFromParts;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_smalldatetimefromparts;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SmallDatetimeFromPartsFunctionTest {


    /**
     * SMALLDATETIMEFROMPARTS ( 2010, 12, 31, 23, 59 )
     */
    public SmallDatetimeFromParts example1 = f_smalldatetimefromparts(
            c_number(2010),
            c_number(12),
            c_number(31),
            c_number(23),
            c_number(59)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getYear().getClass(), NumberConstant.class);
        assertEquals(example1.getMonth().getClass(), NumberConstant.class);
        assertEquals(example1.getDay().getClass(), NumberConstant.class);
        assertEquals(example1.getHour().getClass(), NumberConstant.class);
        assertEquals(example1.getMinute().getClass(), NumberConstant.class);
    }


}