package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.datetime.Month;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_month;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class MonthFunctionTest {


    /**
     *  MONTH('2007-04-30T01:01:01.1234567 -07:00')
     */
    public Month example1 = f_month(
            c_string("2007-04-30T01:01:01.1234567 -07:00")
    );

    /**
     *  MONTH('2007-04-30T01:01:01.1234567 -07:00')
     */
    public Month example2 = f_month(
            c_number(0)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDate().getClass(), StringConstant.class);
        assertEquals(example2.getDate().getClass(), NumberConstant.class);
    }


}