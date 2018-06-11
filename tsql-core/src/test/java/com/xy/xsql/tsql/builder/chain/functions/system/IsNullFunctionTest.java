package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.system.IsNull;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_isnull;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsNullFunctionTest {


    /**
     * ISNULL(Weight, 50)
     */
    public IsNull exampleA = f_isnull(
            c("Weight"),
            c_number(50)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getCheckExpression().getClass(), ColumnName.class);
        assertEquals(exampleA.getReplacementValue().getClass(), NumberConstant.class);
    }

    /**
     * ISNULL(MaxQty, 0.00)
     */
    public IsNull exampleB = f_isnull(
            c("MaxQty"),
            c_number(0.00)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getCheckExpression().getClass(), ColumnName.class);
        assertEquals(exampleB.getReplacementValue().getClass(), NumberConstant.class);
    }


    //ExampleC is not function

    /**
     * ISNULL(Weight, 50)
     */
    public IsNull exampleD = f_isnull(
            c("Weight"),
            c_number(50)
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getCheckExpression().getClass(), ColumnName.class);
        assertEquals(exampleD.getReplacementValue().getClass(), NumberConstant.class);
    }

    /**
     * ISNULL(MinPaymentAmount,0)
     */
    public IsNull exampleE = f_isnull(
            c("MinPaymentAmount"),
            c_number(0)
    );

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getCheckExpression().getClass(), ColumnName.class);
        assertEquals(exampleE.getReplacementValue().getClass(), NumberConstant.class);
    }


    //ExampleF is not function
}