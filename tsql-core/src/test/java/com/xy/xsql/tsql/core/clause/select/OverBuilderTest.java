package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.select.Over;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.Expressions.e;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class OverBuilderTest {

    /*
    Examples
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/select-over-clause-transact-sql#examples
     */

    // @formatter:off
    //parent+quick
    /**
     * OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)
     */
    public Over exampleA = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$PartitionBy(e("PostalCode"))
                    .$OrderBy_Desc(e("SalesYTD"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withPartitionBy()
                    .withExpression(e("PostalCode"))
                    .and()
                .withOrderBy()
                    .withItems()
                        .withExpression(e("SalesYTD"))
                        .withDesc()
                        .out()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().size(),1);
        Assert.assertEquals(over.getOrderBy().getItems().size(),1);

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().get(0).toString(),"PostalCode");

        Assert.assertEquals(over.getOrderBy().getItems().get(0).isUseAsc(),false);
        Assert.assertEquals(over.getOrderBy().getItems().get(0).isUseDesc(),true);
        Assert.assertEquals(over.getOrderBy().getItems().get(0).getOrderByExpression().toString(),"SalesYTD");
    }

    // @formatter:off
    //parent+quick
    /**
     * OVER(PARTITION BY SalesOrderID)
     */
    public Over exampleB = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$PartitionBy(e("SalesOrderID"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withPartitionBy()
                    .withExpression(e("SalesOrderID"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().size(),1);
        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().get(0).toString(),"SalesOrderID");
    }

    // @formatter:off
    //parent+quick
    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     )
     */
    public Over exampleC1 = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$PartitionBy(e("TerritoryID"))
                    .$OrderBy(e("DATEPART(yy,ModifiedDate) "))
                    .and()
                .get();

    /**
     * OVER (ORDER BY DATEPART(yy,ModifiedDate)
    )
     */
    public Over exampleC2 = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$OrderBy(e("DATEPART(yy,ModifiedDate) "))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleC(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withPartitionBy()
                    .withExpression(e("TerritoryID"))
                    .and()
                .withOrderBy()
                    .withItems()
                        .withExpression(e("DATEPART(yy,ModifiedDate) "))
                        .out()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().size(),1);
        Assert.assertEquals(over.getOrderBy().getItems().size(),1);
    }

    // @formatter:off
    //parent+quick
    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING )
     */
    public Over exampleD1 = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$PartitionBy(e("TerritoryID"))
                    .$OrderBy(e("DATEPART(yy,ModifiedDate)"))
                    .$Rows()
                        .$Between()
                        .$CurrentRow()
                        .$And()
                        .$Following(1)
                        .and()
                    .and()
                .get();

    /**
     * OVER (PARTITION BY TerritoryID
    ORDER BY DATEPART(yy,ModifiedDate)
    ROWS UNBOUNDED PRECEDING)
     */
    public Over exampleD2 = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$PartitionBy(e("TerritoryID"))
                    .$OrderBy(e("DATEPART(yy,ModifiedDate)"))
                    .$Rows()
                        .$UnboundedPreceding()
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleD(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withPartitionBy()
                    .withExpression(e("TerritoryID"))
                    .and()
                .withOrderBy()
                    .withItems()
                        .withExpression(e("DATEPART(yy,ModifiedDate) "))
                        .out()
                    .and()
                .withRowRange()
                    .withRows()
                    .withWindowFrameExtent()._Between()
                        .withBetweenBound()._Preceding()
                            .withCurrent()
                            .and()
                        .withAndBound()._Following()
                            .withUnsignedvaluespecification(1)
                            .and()
                        .and()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().size(),1);
        Assert.assertEquals(over.getOrderBy().getItems().size(),1);

        Assert.assertTrue(over.getRowRange().isUseRows());
        Assert.assertEquals(over.getRowRange().getWindowFrameExtent().getClass(), Over.WindowFrameBetween.class);
        Over.WindowFrameBetween windowFrameBetween = (Over.WindowFrameBetween) over.getRowRange().getWindowFrameExtent();

        Assert.assertEquals(windowFrameBetween.getBetweenBound().getClass(), Over.WindowFramePreceding.class);
        Assert.assertEquals(windowFrameBetween.getAndBound().getClass(), Over.WindowFrameFollowing.class);

        Over.WindowFramePreceding windowFramePreceding = (Over.WindowFramePreceding) windowFrameBetween.getBetweenBound();
        Assert.assertTrue(windowFramePreceding.isUseCurrent());

        Over.WindowFrameFollowing windowFrameFollowing = (Over.WindowFrameFollowing) windowFrameBetween.getAndBound();
        Assert.assertEquals(windowFrameFollowing.getUnsignedvaluespecification().toNumberConstant().getNumber(),1);
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
    public Over exampleE = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$OrderBy_Desc(e("SUM(SalesAmountQuota)"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleE(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withOrderBy()
                    .withItems()
                        .withExpression(e("SUM(SalesAmountQuota)"))
                        .withDesc()
                        .and()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getOrderBy().getItems().size(),1);
        Assert.assertEquals(over.getOrderBy().getItems().get(0).isUseDesc(),true);
        Assert.assertEquals(over.getOrderBy().getItems().get(0).getOrderByExpression().toString(),"SUM(SalesAmountQuota)");
    }


    // @formatter:off
    //parent+quick
    /**
     * OVER(PARTITION BY SalesOrderNumber)
     */
    public Over exampleF = new MockParentBuilder<OverBuilder<MockParent<Over>>,Over>
                (OverBuilder.class,Over.class)
                .$child()
                    .$PartitionBy(e("SalesOrderNumber"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleF(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withPartitionBy()
                    .withExpression(e("SalesOrderNumber"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().size(),1);
        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().get(0).toString(),"SalesOrderNumber");
    }

}
