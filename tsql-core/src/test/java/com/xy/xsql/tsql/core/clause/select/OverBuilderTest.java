package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.model.clause.select.Over;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class OverBuilderTest {

    /**
     * OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)
     */
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

    /**
     * OVER(PARTITION BY SalesOrderID)
     */
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

    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     )
     */
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

    /**
     * OVER (PARTITION BY TerritoryID
     ORDER BY DATEPART(yy,ModifiedDate)
     ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING )
     )
     */
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
                //TODO
                .build();
        // @formatter:on

        Assert.assertEquals(over.getPartitionBy().getValueExpressionList().size(),1);
        Assert.assertEquals(over.getOrderBy().getItems().size(),1);
    }

    /**
     * OVER(ORDER BY SUM(SalesAmountQuota) DESC)
     )
     */
    @Test
    public void testExampleE(){
        // @formatter:off
        Over over = new OverBuilder<Void>()
                .withOrderBy()
                    .withItems()
                        .withExpression(e("SUM(SalesAmountQuota)"))
                        .withDesc()
                        .out()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(over.getOrderBy().getItems().size(),1);
        Assert.assertEquals(over.getOrderBy().getItems().get(0).isUseDesc(),true);
        Assert.assertEquals(over.getOrderBy().getItems().get(0).getOrderByExpression().toString(),"SUM(SalesAmountQuota)");
    }

    /**
     * OVER(PARTITION BY SalesOrderNumber)
     */
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
