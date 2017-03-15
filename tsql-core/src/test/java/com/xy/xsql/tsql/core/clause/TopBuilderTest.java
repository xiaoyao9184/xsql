package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.model.clause.Top;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_number;
import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class TopBuilderTest {


     //Basic syntax

    /**
     * TOP(10)
     */
    @Test
    public void testExample1A(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();

        Assert.assertEquals(top.getExpression().toString(),"10");
    }

    /**
     * TOP(@p)
     */
    @Test
    public void testExample1B(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_variable("p"))
                .build();

        Assert.assertEquals(top.getExpression().toString(),"@p");
    }

    /**
     * TOP(5)PERCENT
     */
    @Test
    public void testExample1C(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .withPercent()
                .build();

        Assert.assertEquals(top.getExpression().toString(),"5");
        Assert.assertEquals(top.isUsePercent(),true);
    }

    //Including tie values

    /**
     * TOP(10)WITH TIES
     */
    @Test
    public void testExample2A(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .withTies()
                .build();

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(top.isUseTies(),true);
    }

    //Limiting the rows affected by DELETE, INSERT, or UPDATE

    /**
     * TOP (20)
     */
    @Test
    public void testExample3A(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(20))
                .build();

        Assert.assertEquals(top.getExpression().toString(),"20");
    }

    /**
     * TOP (5)
     */
    @Test
    public void testExample3B(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .build();

        Assert.assertEquals(top.getExpression().toString(),"5");
    }


    /**
     * TOP (10)
     * TOP 10
     */
    @Test
    public void testExample3C(){
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();

        Assert.assertEquals(top.getExpression().toString(),"10");
    }


}
