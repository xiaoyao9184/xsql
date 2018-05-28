package com.xy.xsql.tsql.style.constructor.introducer.elements.expressions;

import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Coalesce;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_multiplication;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.style.constructor.introducer.elements.expressions.coalesce.COALESCE;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
public class coalesceTest {


    /**
     * COALESCE(Class, Color, ProductNumber)
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Coalesce coalesce = COALESCE(
                e("Class"),
                e("Color"),
                e("ProductNumber")
        ).build();
        // @formatter:on

        assertEquals(coalesce.getExpressionList().size(),3);
        assertEquals(coalesce.getExpressionList().get(0).toString(),"Class");
        assertEquals(coalesce.getExpressionList().get(1).toString(),"Color");
        assertEquals(coalesce.getExpressionList().get(2).toString(),"ProductNumber");
    }

    /**
     * COALESCE(hourly_wage * 40 * 52,
     salary,
     commission * num_sales
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Coalesce coalesce = COALESCE(
                e_multiplication(
                        e("hourly_wage"),
                        e_multiplication(
                            e_number(40),
                            e_number(52))),
                e("salary"),
                e_multiplication(
                        e("commission"),
                        e("num_sales"))
        ).build();
        // @formatter:on

        assertEquals(coalesce.getExpressionList().size(),3);
        assertEquals(coalesce.getExpressionList().get(0).getClass(), BinaryExpression.class);
        assertEquals(coalesce.getExpressionList().get(1).toString(),"salary");
        assertEquals(coalesce.getExpressionList().get(2).getClass(),BinaryExpression.class);
    }


}