package com.xy.xsql.tsql.builder.chain.queries;

import com.xy.xsql.tsql.model.queries.AtTimeZone;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;


/**
 * Created by xiaoyao9184 on 2017/3/11.
 */
public class AtTimeZoneBuilderTest {


    /**
     * OrderDate AT TIME ZONE 'Pacific Standard Time'
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        AtTimeZone atTimeZone = new AtTimeZoneBuilder<Void>()
                .withExpression(
                        e("OrderDate")
                )
                .withTimezone("Pacific Standard Time")
                .build();
        // @formatter:on

        Assert.assertEquals(atTimeZone.getInputExpression().toString(),"OrderDate");
        Assert.assertEquals(atTimeZone.getTimezone().getString(),"Pacific Standard Time");
    }

    /**
     * OrderDate AT TIME ZONE 'Pacific Standard Time'
     AT TIME ZONE 'Central European Standard Time' AS OrderDate_TimeZoneCET
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        AtTimeZone atTimeZone0 = new AtTimeZoneBuilder<Void>()
                .withExpression(
                        e("OrderDate")
                )
                .withTimezone("Pacific Standard Time")
                .build();

        AtTimeZone atTimeZone = new AtTimeZoneBuilder<Void>()
                .withExpression(
                        atTimeZone0
                )
                .withTimezone("Central European Standard Time")
                .build();
        // @formatter:on

        Assert.assertEquals(atTimeZone.getInputExpression().getClass(),AtTimeZone.class);
        Assert.assertEquals(atTimeZone.getTimezone().getString(),"Central European Standard Time");

        Assert.assertEquals(atTimeZone0.getInputExpression().toString(),"OrderDate");
        Assert.assertEquals(atTimeZone0.getTimezone().getString(),"Pacific Standard Time");
    }

    /**
     *  DATEADD (month, -1, GETDATE()) AT TIME ZONE 'UTC'
     *  ValidFrom AT TIME ZONE 'Pacific Standard Time'
     */
    @Test
    public void testExampleC(){
        // @formatter:off
        AtTimeZone atTimeZone0 = new AtTimeZoneBuilder<Void>()
                .withExpression(
                        e("DATEADD (month, -1, GETDATE())")
                )
                .withTimezone("UTC")
                .build();

        AtTimeZone atTimeZone = new AtTimeZoneBuilder<Void>()
                .withExpression(
                        e("ValidFrom")
                )
                .withTimezone("Pacific Standard Time")
                .build();
        // @formatter:on

        Assert.assertEquals(atTimeZone.getInputExpression().toString(),"ValidFrom");
        Assert.assertEquals(atTimeZone.getTimezone().getString(),"Pacific Standard Time");

        Assert.assertEquals(atTimeZone0.getInputExpression().toString(),"DATEADD (month, -1, GETDATE())");
        Assert.assertEquals(atTimeZone0.getTimezone().getString(),"UTC");
    }

}
