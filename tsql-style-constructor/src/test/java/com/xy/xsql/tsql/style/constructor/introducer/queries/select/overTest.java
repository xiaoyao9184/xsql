package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.queries.select.Over;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.over.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/10.
 */
public class overTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/select-over-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)
     */
    public Over exampleA = OVER(
            PARTITION_BY(e("PostalCode")),
            ORDER_BY(e("SalesYTD"),DESC())
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getPartitionBy().getValueExpressionList().size(),1);
        assertEquals(exampleA.getOrderBy().getItems().size(),1);

        assertEquals(exampleA.getPartitionBy().getValueExpressionList().get(0).toString(),"PostalCode");

        assertEquals(exampleA.getOrderBy().getItems().get(0).isUseAsc(),false);
        assertEquals(exampleA.getOrderBy().getItems().get(0).isUseDesc(),true);
        assertEquals(exampleA.getOrderBy().getItems().get(0).getOrderByExpression().toString(),"SalesYTD");
    }

    // @formatter:off
    //parent+quick
    /**
     * OVER(PARTITION BY SalesOrderID)
     */
    public Over exampleB = OVER(
            PARTITION_BY(e("SalesOrderID"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getPartitionBy().getValueExpressionList().size(),1);
        assertEquals(exampleB.getPartitionBy().getValueExpressionList().get(0).toString(),"SalesOrderID");
    }

    // @formatter:off
    //parent+quick
    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     )
     */
    public Over exampleC1 = OVER(
            PARTITION_BY(e("TerritoryID")),
            ORDER_BY(e("DATEPART(yy,ModifiedDate) "))
    ).build();

    /**
     * OVER (ORDER BY DATEPART(yy,ModifiedDate)
     )
     */
    public Over exampleC2 = OVER(
            ORDER_BY(e("DATEPART(yy,ModifiedDate)"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleC(){
        assertEquals(exampleC1.getPartitionBy().getValueExpressionList().size(),1);
        assertEquals(exampleC1.getOrderBy().getItems().size(),1);
    }

    // @formatter:off
    //parent+quick
    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING )
     */
    public Over exampleD1 = OVER(
            PARTITION_BY(e("TerritoryID")),
            ORDER_BY(e("DATEPART(yy,ModifiedDate)")),
            ROWS(BETWEEN(CURRENT_ROW(),AND(1,FOLLOWING())))
    ).build();

    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     ROWS UNBOUNDED PRECEDING)
     */
    public Over exampleD2 = OVER(
            PARTITION_BY(e("TerritoryID")),
            ORDER_BY(e("DATEPART(yy,ModifiedDate)")),
            ROWS(UNBOUNDED(),PRECEDING())
    ).build();
    // @formatter:on

    @Test
    public void testExampleD(){
        assertEquals(exampleD1.getPartitionBy().getValueExpressionList().size(),1);
        assertEquals(exampleD1.getOrderBy().getItems().size(),1);

        assertTrue(exampleD1.getRowRange().isUseRows());
        assertEquals(exampleD1.getRowRange().getWindowFrameExtent().getClass(), Over.WindowFrameBetween.class);
        Over.WindowFrameBetween windowFrameBetween = (Over.WindowFrameBetween) exampleD1.getRowRange().getWindowFrameExtent();

        assertEquals(windowFrameBetween.getBetweenBound().getClass(), Over.WindowFramePreceding.class);
        assertEquals(windowFrameBetween.getAndBound().getClass(), Over.WindowFrameFollowing.class);

        Over.WindowFramePreceding windowFramePreceding = (Over.WindowFramePreceding) windowFrameBetween.getBetweenBound();
        assertTrue(windowFramePreceding.isUseCurrent());

        Over.WindowFrameFollowing windowFrameFollowing = (Over.WindowFrameFollowing) windowFrameBetween.getAndBound();
        assertEquals(windowFrameFollowing.getUnsignedvaluespecification().toNumberConstant().getNumber(),1);
    }


    /*
    Examples: Parallel Data Warehouse
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/select-over-clause-transact-sql#examples-includesspdwincludessspdw-mdmd
     */

    // @formatter:off
    //parent+quick
    /**
     * OVER(ORDER BY SUM(SalesAmountQuota) DESC)
     */
    public Over exampleE = OVER(
            ORDER_BY(e("SUM(SalesAmountQuota)"),DESC())
    ).build();
    // @formatter:on

    @Test
    public void testExampleE(){
        assertEquals(exampleE.getOrderBy().getItems().size(),1);
        assertEquals(exampleE.getOrderBy().getItems().get(0).isUseDesc(),true);
        assertEquals(exampleE.getOrderBy().getItems().get(0).getOrderByExpression().toString(),"SUM(SalesAmountQuota)");
    }


    // @formatter:off
    //parent+quick
    /**
     * OVER(PARTITION BY SalesOrderNumber)
     */
    public Over exampleF = OVER(
            PARTITION_BY(e("SalesOrderNumber"))
    ).build();
    // @formatter:on

    @Test
    public void testExampleF(){
        assertEquals(exampleF.getPartitionBy().getValueExpressionList().size(),1);
        assertEquals(exampleF.getPartitionBy().getValueExpressionList().get(0).toString(),"SalesOrderNumber");
    }

}