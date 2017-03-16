package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.model.clause.select.GroupBy;
import com.xy.xsql.tsql.model.datatype.NumberConstant;
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
                        .withColumnExpression(new NumberConstant(1))
                        .out()
                    .withBaseItem()
                        .withColumnExpression(new NumberConstant(2))
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
                        .withColumnExpression(new NumberConstant(1))
                        .out()
                    .withRollupItem()
                        .withGroupByExpressionList()
                            .withGroupByExpression()
                                .withColumnExpression(new NumberConstant(2))
                                .out()
                            .withGroupByExpression()
                                .withColumnExpression(new NumberConstant(3))
                                .withColumnExpression(new NumberConstant(4))
                                .out()
                            .out()
                        .out()
                    .withCubeItem()
                        .withGroupByExpressionList()
                            .withGroupByExpression()
                                .withColumnExpression(new NumberConstant(5))
                                .out()
                            .out()
                        .out()
                    .withGroupingSetsItem()
                        .withGroupingSetList()
                            .withGroupingSet()
                                .withGroupingSetItemList()
                                    .withGroupingSetItem()
                                        .withGroupByExpression()
                                            .withColumnExpression(new NumberConstant(6))
                                            .out()
                                        .out()
                                    .withGroupingSetItem()
                                        .withCubeGroupByExpressionList()
                                            .withGroupByExpression()
                                                .withColumnExpression(new NumberConstant(7))
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

    /**
     * GROUP BY SalesOrderID
     */
    public void testExampleA(){
        // @formatter:off
//        GroupBy groupBy = new GroupByBuilder<Void>()
//                .withGroupList(HASH_GROUP(),FAST(10))
//                .build();
//        // @formatter:on
//
//        Assert.assertEquals(option.getQueryOption().size(),2);
//        Assert.assertEquals(option.getQueryOption().get(0).getQueryHint().getType(), QueryHint.Type.HASH_GROUP);
//        Assert.assertEquals(option.getQueryOption().get(1).getQueryHint().getType(), QueryHint.Type.FAST);
    }

}
