package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.datetime.IsDate;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_isdate;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsDateFunctionTest {


    /**
     *  ISDATE('2009-05-12 10:19:41.177')
     */
    public IsDate exampleA = f_isdate(
            c_string("2009-05-12 10:19:41.177")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getExpression().getClass(), StringConstant.class);
    }


}