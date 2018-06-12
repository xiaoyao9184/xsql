package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.datetime.DateDiff;
import com.xy.xsql.tsql.model.functions.datetime.DateDiff_Big;
import com.xy.xsql.tsql.model.functions.datetime.DatePart;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datediff;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_datediff_big;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DateDiffBigFunctionTest {


    /**
     * DATEDIFF_BIG(day,startDate,endDate)
     */
    public DateDiff_Big example1 = f_datediff_big(
            DatePart.DatePartArgument.day,
            c("startDate"),
            c("endDate")
    );

    @Test
    public void testExample1(){
        assertEquals(example1.getDatepart(), DatePart.DatePartArgument.day);
        assertEquals(example1.getStartDate().getClass(), ColumnName.class);
        assertEquals(example1.getEndDate().getClass(), ColumnName.class);
    }

}