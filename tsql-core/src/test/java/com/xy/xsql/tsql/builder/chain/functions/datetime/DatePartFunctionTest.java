package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datepart;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DatePartFunctionTest {


    /**
     * DATEPART(year, 0)
     */
    public DatePart example1 = f_datepart(
            DatePart.DatePartArgument.year,
            c_number(0)
    );

    /**
     * DATEPART (day,'12/20/1974')
     */
    public DatePart example2 = f_datepart(
            DatePart.DatePartArgument.day,
            c_string("12/20/1974")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getDatepart(), DatePart.DatePartArgument.year);
        assertEquals(example1.getDate().getClass(), NumberConstant.class);
        assertEquals(example2.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(example2.getDate().getClass(), StringConstant.class);
    }

}