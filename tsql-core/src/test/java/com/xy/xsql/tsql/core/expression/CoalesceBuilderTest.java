package com.xy.xsql.tsql.core.expression;

import com.xy.xsql.tsql.model.expression.Coalesce;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.operator.Operators;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.e;
import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.expression.GroupExpressions.e_binary;


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
                                Operators.MULTIPLICATION,
                                e_number(40),
                                e_number(52)
                        )
                )
                .withExpression(e("salary"))
                .withExpression(
                        e_binary(
                                e("commission"),
                                Operators.MULTIPLICATION,
                                e("num_sales")
                        )
                )
                .build();
        // @formatter:on

        Assert.assertEquals(coalesce.getExpressionList().size(),3);
        Assert.assertEquals(coalesce.getExpressionList().get(0).getClass(), GroupExpression.class);
        Assert.assertEquals(coalesce.getExpressionList().get(1).toString(),"salary");
        Assert.assertEquals(coalesce.getExpressionList().get(2).getClass(),GroupExpression.class);
    }


}
