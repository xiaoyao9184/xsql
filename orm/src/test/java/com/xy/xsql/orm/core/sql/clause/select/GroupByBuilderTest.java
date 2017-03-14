package com.xy.xsql.orm.core.sql.clause.select;

import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.orm.data.sql.expression.NumberString;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class GroupByBuilderTest {

    /**
     * GROUP BY 1, 2
     */
    @Test
    public void testBaseBuild(){
        GroupBy groupBy = new GroupByBuilder<GroupBy>()
                .withGroupList()
                    .withBaseItem()
                        .withColumnExpression(new NumberString(1))
                        .out()
                    .withBaseItem()
                        .withColumnExpression(new NumberString(2))
                        .out()
                    .out()
                .build();

        Assert.assertEquals(groupBy.getItems().get(0).getColumnExpression().toString(),"1");
        Assert.assertEquals(groupBy.getItems().get(1).getColumnExpression().toString(),"2");
    }

    /**
     * GROUP BY 1, ROLLUP ( 2, (3, 4) ), CUBE (5), GROUPING SETS ( (6, ROLLUP ( 7)))
     */
    @Test
    public void testRollupBuild(){
        GroupBy groupBy = new GroupByBuilder<GroupBy>()
                .withGroupList()
                    .withBaseItem()
                        .withColumnExpression(new NumberString(1))
                        .out()
                    .withRollupItem()
                        .withGroupByExpressionList()
                            .withGroupByExpression()
                                .withColumnExpression(new NumberString(2))
                                .out()
                            .withGroupByExpression()
                                .withColumnExpression(new NumberString(3))
                                .withColumnExpression(new NumberString(4))
                                .out()
                            .out()
                        .out()
                    .withCubeItem()
                        .withGroupByExpressionList()
                            .withGroupByExpression()
                                .withColumnExpression(new NumberString(5))
                                .out()
                            .out()
                        .out()
                    .withGroupingSetsItem()
                        .withGroupingSetList()
                            .withGroupingSet()
                                .withGroupingSetItemList()
                                    .withGroupingSetItem()
                                        .withGroupByExpression()
                                            .withColumnExpression(new NumberString(6))
                                            .out()
                                        .out()
                                    .withGroupingSetItem()
                                        .withCubeGroupByExpressionList()
                                            .withGroupByExpression()
                                                .withColumnExpression(new NumberString(7))
                                                .out()
                                            .out()
                                        .out()
                                    .out()
                                .out()
                            .out()
                        .out()
                    .out()
                .build();

        Assert.assertEquals(groupBy.getItems().get(0).getColumnExpression().toString(),"1");
        Assert.assertEquals(groupBy.getItems().get(1).getGroupByExpressionList().size(),2);
        Assert.assertEquals(groupBy.getItems().get(2).getGroupByExpressionList().size(),1);
        Assert.assertEquals(groupBy.getItems().get(3).getGroupingSetList().size(),1);
    }

}
