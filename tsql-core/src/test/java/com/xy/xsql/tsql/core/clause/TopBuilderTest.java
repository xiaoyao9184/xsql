package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.Top;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.e_number;
import static com.xy.xsql.tsql.core.expression.Expressions.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class TopBuilderTest {

     //Basic syntax


    // @formatter:off
    //parent+quick
    public Top example1A = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10))
                .get();
    // @formatter:on

    /**
     * TOP(10)
     */
    @Test
    public void testExample1A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(example1A.getExpression().toString(),"10");
    }


    // @formatter:off
    //parent+quick
    public Top example1B = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_variable("p"))
                .get();
    // @formatter:on

    /**
     * TOP(@p)
     */
    @Test
    public void testExample1B(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_variable("p"))
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"@p");
        Assert.assertEquals(example1B.getExpression().toString(),"@p");
    }


    // @formatter:off
    //parent+quick
    public Top example1C = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(5),true)
                .get();
    // @formatter:on

    /**
     * TOP(5)PERCENT
     */
    @Test
    public void testExample1C(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .withPercent()
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"5");
        Assert.assertEquals(top.isUsePercent(),true);
        Assert.assertEquals(example1C.getExpression().toString(),"5");
        Assert.assertEquals(example1C.isUsePercent(),true);
    }

    //Including tie values


    // @formatter:off
    //parent+quick
    public Top example2A = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10),false,true)
                .get();
    // @formatter:on

    /**
     * TOP(10)WITH TIES
     */
    @Test
    public void testExample2A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .withTies()
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(top.isUseTies(),true);
        Assert.assertEquals(example2A.getExpression().toString(),"10");
        Assert.assertEquals(example2A.isUseTies(),true);
    }

    //Limiting the rows affected by DELETE, INSERT, or UPDATE


    // @formatter:off
    //parent+quick
    public Top example3A = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(20))
                .get();
    // @formatter:on

    /**
     * TOP (20)
     */
    @Test
    public void testExample3A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(20))
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"20");
        Assert.assertEquals(example3A.getExpression().toString(),"20");
    }


    // @formatter:off
    //parent+quick
    public Top example3B = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(5))
                .get();
    // @formatter:on

    /**
     * TOP (5)
     */
    @Test
    public void testExample3B(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"5");
        Assert.assertEquals(example3B.getExpression().toString(),"5");
    }


    // @formatter:off
    //parent+quick
    public Top example3C = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10))
                .get();
    // @formatter:on


    /**
     * TOP (10)
     * TOP 10
     */
    @Test
    public void testExample3C(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(example3C.getExpression().toString(),"10");
    }


}
