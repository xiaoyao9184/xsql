package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.datetime.DateName;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datename;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DateNameFunctionTest {


    /**
     * DATENAME(yyyy,'2007-10-30 12:15:32.1234567 +05:10')
     */
    public DateName example1 = f_datename(
            DatePart.DatePartArgument.yyyy,
            c_string("2007-10-30 12:15:32.1234567 +05:10")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getDatepart(), DatePart.DatePartArgument.yyyy);
        assertEquals(example1.getDate().getClass(), StringConstant.class);
    }

}