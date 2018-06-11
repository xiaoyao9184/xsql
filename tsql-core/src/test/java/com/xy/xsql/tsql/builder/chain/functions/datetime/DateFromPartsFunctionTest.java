package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.datetime.DateFromParts;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datefromparts;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DateFromPartsFunctionTest {


    /**
     * DATEFROMPARTS ( 2010, 12, 31 )
     */
    public DateFromParts example1 = f_datefromparts(
            c_number(2010),
            c_number(12),
            c_number(31)
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getYear().getClass(), NumberConstant.class);
        assertEquals(example1.getMonth().getClass(), NumberConstant.class);
        assertEquals(example1.getDay().getClass(), NumberConstant.class);
    }

}