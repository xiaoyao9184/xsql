package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.functions.string.Replicate;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_subtraction;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_datalength;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_replicate;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ReplicateFunctionTest {


    /**
     * REPLICATE('0', 4)
     */
    public Replicate exampleA = f_replicate(
            c_string("0"),
            c_number(4)
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getStringExpression().getClass(), StringConstant.class);
        assertEquals(exampleA.getIntegerExpression().getClass(), NumberConstant.class);
    }

    /**
     * REPLICATE('0', 3 - DATALENGTH(c1))
     */
    public Replicate exampleB = f_replicate(
            c_string("0"),
            e_subtraction(
                    c_number(3),
                    f_datalength(c("c1"))
            )
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getStringExpression().getClass(), StringConstant.class);
        assertEquals(exampleB.getIntegerExpression().getClass(), BinaryExpression.class);
    }

}