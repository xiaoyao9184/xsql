package com.xy.xsql.tsql.builder.chain.functions.datetime;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.datetime.EoMonth;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.DataTimeFunctions.f_eomonth;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class EoMonthFunctionTest {


    /**
     *  EOMONTH ( @date )
     */
    public EoMonth exampleA = f_eomonth(
            e_variable("date")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getStartDate().getClass(), LocalVariable.class);
    }


    /**
     * EOMONTH ( @date, 1 )
     */
    public EoMonth exampleC = f_eomonth(
            e_variable("date"),
            c_number(1)
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getStartDate().getClass(), LocalVariable.class);
        assertEquals(exampleC.getMonthToAdd().getClass(), NumberConstant.class);
    }

}