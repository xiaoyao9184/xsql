package com.xy.xsql.tsql.builder.chain.elements.expressions;

import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.Coalesce;
import com.xy.xsql.tsql.model.elements.operators.Arithmetic;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_binary;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class CoalesceBuilderTest {


    /**
     * COALESCE(Class, Color, ProductNumber)
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Coalesce coalesce = new CoalesceBuilder<Void>()
                .withExpression(e("Class"))
                .withExpression(e("Color"))
                .withExpression(e("ProductNumber"))
                .build();
        // @formatter:on

        Assert.assertEquals(coalesce.getExpressionList().size(),3);
        Assert.assertEquals(coalesce.getExpressionList().get(0).toString(),"Class");
        Assert.assertEquals(coalesce.getExpressionList().get(1).toString(),"Color");
        Assert.assertEquals(coalesce.getExpressionList().get(2).toString(),"ProductNumber");
    }

    /**
     * COALESCE(hourly_wage * 40 * 52,
     salary,
     commission * num_sales
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        Coalesce coalesce = new CoalesceBuilder<Void>()
                .withExpression(
                        e_binary(
                                e("hourly_wage"),
                                Arithmetic.MULTIPLICATION,
                                e_number(40),
                                e_number(52)
                        )
                        ,e("salary")
                        ,e_binary(
                                e("commission"),
                                Arithmetic.MULTIPLICATION,
                                e("num_sales")
                        )
                )
                .build();
        // @formatter:on

        Assert.assertEquals(coalesce.getExpressionList().size(),3);
        Assert.assertEquals(coalesce.getExpressionList().get(0).getClass(), BinaryExpression.class);
        Assert.assertEquals(coalesce.getExpressionList().get(1).toString(),"salary");
        Assert.assertEquals(coalesce.getExpressionList().get(2).getClass(),BinaryExpression.class);
    }


}
