package com.xy.xsql.tsql.core.clause;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
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
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(10))
                .build();

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10));
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(parent.get().getExpression().toString(),"10");
    }

    /**
     * TOP(@p)
     */
    @Test
    public void testExample1B(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_variable("p"))
                .build();

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_variable("p"));
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"@p");
        Assert.assertEquals(parent.get().getExpression().toString(),"@p");
    }

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

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(5),true);
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"5");
        Assert.assertEquals(top.isUsePercent(),true);
        Assert.assertEquals(parent.get().getExpression().toString(),"5");
        Assert.assertEquals(parent.get().isUsePercent(),true);
    }

    //Including tie values

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

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10),false,true);
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(top.isUseTies(),true);
        Assert.assertEquals(parent.get().getExpression().toString(),"10");
        Assert.assertEquals(parent.get().isUseTies(),true);
    }

    //Limiting the rows affected by DELETE, INSERT, or UPDATE

    /**
     * TOP (20)
     */
    @Test
    public void testExample3A(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(20))
                .build();

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(20));
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"20");
        Assert.assertEquals(parent.get().getExpression().toString(),"20");
    }

    /**
     * TOP (5)
     */
    @Test
    public void testExample3B(){
        // @formatter:off
        Top top = new TopBuilder<Void>()
                .withExpression(e_number(5))
                .build();

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(5));
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"5");
        Assert.assertEquals(parent.get().getExpression().toString(),"5");
    }


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

        MockParent<Top> parent = new MockParentBuilder<TopBuilder<MockParent<Top>>,Top>
                (TopBuilder.class,Top.class)
                .$child()
                    .$_(e_number(10));
        // @formatter:on

        Assert.assertEquals(top.getExpression().toString(),"10");
        Assert.assertEquals(parent.get().getExpression().toString(),"10");
    }


}
