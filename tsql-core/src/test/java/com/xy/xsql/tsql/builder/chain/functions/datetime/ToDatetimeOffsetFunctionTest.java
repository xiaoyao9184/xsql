package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.datetime.ToDatetimeOffset;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_todatetimeoffset;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ToDatetimeOffsetFunctionTest {


    /**
     * TTODATETIMEOFFSET (@todaysDateTime, '-07:00');
     */
    public ToDatetimeOffset exampleA = f_todatetimeoffset(
            e_variable("todaysDateTime"),
            c_string("-07:00")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), LocalVariable.class);
        assertEquals(exampleA.getTimeZone().getClass(), StringConstant.class);
    }

    /**
     * TODATETIMEOFFSET (@todaysDate, -120)
     */
    public ToDatetimeOffset exampleB = f_todatetimeoffset(
            e_variable("todaysDate"),
            c_number(-120)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getExpression().getClass(), LocalVariable.class);
        assertEquals(exampleB.getTimeZone().getClass(), NumberConstant.class);
    }

    /**
     * TODATETIMEOFFSET (@dateTime, '+13:00')
     */
    public ToDatetimeOffset exampleC = f_todatetimeoffset(
            e_variable("dateTime"),
            c_string("+13:00")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getExpression().getClass(), LocalVariable.class);
        assertEquals(exampleC.getTimeZone().getClass(), StringConstant.class);
    }


}