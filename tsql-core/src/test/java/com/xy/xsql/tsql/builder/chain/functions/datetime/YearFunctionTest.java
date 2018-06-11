package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.datetime.Year;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_year;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class YearFunctionTest {


    /**
     *  YEAR(0)
     */
    public Year example1 = f_year(
            c_number(0)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDate().getClass(), NumberConstant.class);
    }


}