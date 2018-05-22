package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.builder.chain.MockParent;
import com.xy.xsql.tsql.builder.chain.MockParentBuilder;
import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.queries.Top;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class TopBuilderTest {


    /*
    Basic syntax
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/top-transact-sql#BasicSyntax
     */

    // @formatter:off
    //parent+quick
    /**
     * TOP(10)
     */
    public Top example1A = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10))
                .get();
    // @formatter:on

    @Test
    public void testExample1A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        Assert.assertTrue(top.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)top.getExpression()).getNumber().toString(), "10");
        Assert.assertTrue(example1A.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)example1A.getExpression()).getNumber().toString(), "10");
    }


    // @formatter:off
    //parent+quick
    /**
     * TOP(@p)
     */
    public Top example1B = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_variable("p"))
                .get();
    // @formatter:on

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
    /**
     * TOP(5)PERCENT
     */
    public Top example1C = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(5),true)
                .get();
    // @formatter:on

    @Test
    public void testExample1C(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .withPercent()
                .build();
        // @formatter:on

        Assert.assertTrue(top.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)top.getExpression()).getNumber().toString(), "5");
        Assert.assertTrue(example1C.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)example1C.getExpression()).getNumber().toString(), "5");
        Assert.assertEquals(example1C.isUsePercent(),true);
    }

    /*
    Including tie values
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/top-transact-sql#tie
     */

    // @formatter:off
    //parent+quick
    /**
     * TOP(10)WITH TIES
     */
    public Top example2A = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10),false,true)
                .get();
    // @formatter:on

    @Test
    public void testExample2A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .withTies()
                .build();
        // @formatter:on

        Assert.assertTrue(top.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)top.getExpression()).getNumber().toString(), "10");
        Assert.assertEquals(top.isUseTies(),true);
        Assert.assertTrue(example2A.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)example2A.getExpression()).getNumber().toString(), "10");
        Assert.assertEquals(example2A.isUseTies(),true);
    }

    /*
    Limiting the rows affected by DELETE, INSERT, or UPDATE
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/top-transact-sql#DML
     */


    // @formatter:off
    //parent+quick
    /**
     * TOP (20)
     */
    public Top example3A = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(20))
                .get();
    // @formatter:on

    @Test
    public void testExample3A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(20))
                .build();
        // @formatter:on

        Assert.assertTrue(top.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)top.getExpression()).getNumber().toString(), "20");
        Assert.assertTrue(example3A.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)example3A.getExpression()).getNumber().toString(), "20");
    }


    // @formatter:off
    //parent+quick
    /**
     * TOP (5)
     */
    public Top example3B = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(5))
                .get();
    // @formatter:on

    @Test
    public void testExample3B(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .build();
        // @formatter:on

        Assert.assertTrue(top.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)top.getExpression()).getNumber().toString(), "5");
        Assert.assertTrue(example3B.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)example3B.getExpression()).getNumber().toString(), "5");
    }


    // @formatter:off
    //parent+quick
    /**
     * TOP (10)
     * TOP 10
     */
    public Top example3C = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10))
                .get();
    // @formatter:on

    @Test
    public void testExample3C(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();
        // @formatter:on

        Assert.assertTrue(top.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)top.getExpression()).getNumber().toString(), "10");
        Assert.assertTrue(example3C.getExpression() instanceof NumberConstant);
        Assert.assertEquals(((NumberConstant)example3C.getExpression()).getNumber().toString(), "10");
    }

}
