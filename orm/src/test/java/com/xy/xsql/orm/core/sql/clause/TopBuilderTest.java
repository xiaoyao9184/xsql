package com.xy.xsql.orm.core.sql.clause;

import com.xy.xsql.tsql.model.clause.Top;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.orm.core.sql.ExpressionBuilder.e;

/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class TopBuilderTest {

    /**
     * TOP (50)
     */
    @Test
    public void testTop(){
        Top top = new TopBuilder<Void>()
                .withExpression(e(50))
                .build();

        Assert.assertEquals(top.getExpression().toString(),"50");
    }

    /**
     * TOP (50) PERCENT
     */
    @Test
    public void testPercent(){
        Top top = new TopBuilder<Top>()
                .withExpression(e(50))
                .withPercent()
                .build();

        Assert.assertEquals(top.getExpression().toString(),"50");
        Assert.assertEquals(top.isUsePercent(),true);
    }


    /**
     * TOP (50) PERCENT WITH TIES
     */
    @Test
    public void testTies(){
        Top top = new TopBuilder<Top>()
                .withExpression(e(50))
                .withPercent()
                .withTies()
                .build();

        Assert.assertEquals(top.getExpression().toString(),"50");
        Assert.assertEquals(top.isUsePercent(),true);
        Assert.assertEquals(top.isUseTies(),true);
    }

}
