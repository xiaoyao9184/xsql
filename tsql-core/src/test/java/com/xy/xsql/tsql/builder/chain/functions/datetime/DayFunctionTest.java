package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.datetime.Day;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_day;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DayFunctionTest {


    /**
     * DAY('2015-04-30 01:01:01.1234567')
     */
    public Day example1 = f_day(
            c_string("2015-04-30 01:01:01.1234567")
    );

    /**
     * DAY(0)
     */
    public Day example2 = f_day(
            c_number(0)
    );


    @Test
    public void testExample(){
        assertEquals(example1.getDate().getClass(), StringConstant.class);
        assertEquals(example2.getDate().getClass(), NumberConstant.class);
    }

}