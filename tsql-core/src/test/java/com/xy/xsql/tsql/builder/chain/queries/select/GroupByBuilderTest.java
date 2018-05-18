package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.tsql.builder.chain.MockParent;
import com.xy.xsql.tsql.builder.chain.MockParentBuilder;
import com.xy.xsql.tsql.model.queries.select.GroupBy;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_multiplication;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class GroupByBuilderTest {

    /**
     * GROUP BY c1, c2
     */
    @Test
    public void testBaseBuild(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<GroupBy>()
                .withItem()._Base()
                    .withColumnExpression(c("c1"))
                    .and()
                .withItem()._Base()
                    .withColumnExpression(c("c2"))
                    .and()
                .build();

        //parent+quick
        MockParent<GroupBy> parent = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("c1"))
                    .$(c("c2"))
                    .and();
        // @formatter:on

        Assert.assertEquals(groupBy.getItems().size(),2);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"c1");

        Assert.assertEquals(groupBy.getItems().get(1).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item2 = (GroupBy.BaseItem) groupBy.getItems().get(1);
        Assert.assertEquals(item2.getExpression().toString(),"c2");
    }

    /**
     * GROUP BY c1, ROLLUP ( c2, (c3, c4) ), CUBE (c5), GROUPING SETS ( (), c6, CUBE( c7 ), (c8, ROLLUP ( c9 ) ) )
     */
    @Test
    public void testRollupBuild(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<GroupBy>()
                .withItem()._Base()
                    .withColumnExpression(c("c1"))
                    .and()
                .withItem()._Rollup()
                    .withItem()
                        .withColumnExpression(c("c2"))
                        .and()
                    .withItem()
                        .withColumnExpression(c("c3"))
                        .withColumnExpression(c("c4"))
                        .and()
                    .and()
                .withItem()._Cube()
                    .withItem()
                        .withColumnExpression(c("c5"))
                        .and()
                    .and()
                .withItem()._GroupingSets()
                    .withItem()
                        .withTotal()
                        .and()
                    .withItem()
                        .withItem()._Base()
                            .withColumnExpression(c("c6"))
                            .and()
                        .and()
                    .withItem()
                        .withItem()._Cube()
                            .withItem()
                                .withColumnExpression(c("c7"))
                                .and()
                            .and()
                        .and()
                    .withItem()
                        .withItem()._Base()
                            .withColumnExpression(c("c8"))
                            .and()
                        .withItem()._Rollup()
                            .withItem()
                                .withColumnExpression(c("c9"))
                                .and()
                            .and()
                        .and()
                    .and()
                .build();

        //parent+quick
        MockParent<GroupBy> parent = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("c1"))
                    .$Rollup()
                        .$(c("c2"))
                        .$(c("c3"),c("c4"))
//                        .$()
//                            .$(c("c3"))
//                            .$(c("c4"))
//                            .and()
                        .and()
                    .$Cube()
                        .$(c("c5"))
                        .and()
                    .$Grouping_Sets()
                        //grouping_set:()
                        .$()
                            .$_()
                            .and()
                        //grouping_set:<grouping_set_item>
                        .$()
                            .$(e("c6"))
                            .and()
                        //grouping_set:<grouping_set_item>
                        .$()
                            .$Cube()
                                .$(e("c7"))
                                .and()
                            .and()
                        //grouping_set:( <grouping_set_item> [ ,...n ] )
                        .$()
                            .$(c("c8"))
                            .$Rollup()
                                .$(c("c9"))
                                .and()
                            .and()
                        .and()
                    .and();
        // @formatter:on


        Assert.assertEquals(groupBy.getItems().size(),4);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"c1");

        Assert.assertEquals(groupBy.getItems().get(1).getClass(), GroupBy.RollupItem.class);
        GroupBy.RollupItem item1 = (GroupBy.RollupItem) groupBy.getItems().get(1);
        Assert.assertEquals(item1.getGroupByExpressionList().size(),2);

        Assert.assertEquals(groupBy.getItems().get(2).getClass(), GroupBy.CubeItem.class);
        GroupBy.CubeItem item2 = (GroupBy.CubeItem) groupBy.getItems().get(2);
        Assert.assertEquals(item2.getGroupByExpressionList().size(),1);

        Assert.assertEquals(groupBy.getItems().get(3).getClass(), GroupBy.GroupingSetsItem.class);
        GroupBy.GroupingSetsItem item3 = (GroupBy.GroupingSetsItem) groupBy.getItems().get(3);
        Assert.assertEquals(item3.getGroupingSetItemList().size(),4);
    }


    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-group-by-transact-sql#examples
     */


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY SalesOrderID
     */
    public GroupBy exampleA = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("SalesOrderID"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleA(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<Void>()
                .withItem()._Base()
                    .withColumnExpression(c("SalesOrderID"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(groupBy.getItems().size(),1);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"SalesOrderID");
    }


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY a.City
     */
    public GroupBy exampleB = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("a","City"))
                    .and()
                .get();
    // @formatter:on

    @Test
    public void testExampleB(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<Void>()
                .withItem()._Base()
                    .withColumnExpression(c("a","City"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(groupBy.getItems().size(),1);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"a.City");
    }


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY DATEPART(yyyy,OrderDate)
     */
    public GroupBy exampleC = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(e("DATEPART(yyyy,OrderDate)"))
                    .and()
                .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleC(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<Void>()
                .withItem()._Base()
                    .withColumnExpression(e("DATEPART(yyyy,OrderDate)"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(groupBy.getItems().size(),1);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"DATEPART(yyyy,OrderDate)");
    }


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY DATEPART(yyyy,OrderDate)
     */
    public GroupBy exampleD = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(e("DATEPART(yyyy,OrderDate)"))
                    .and()
                .get();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleD(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<Void>()
                .withItem()._Base()
                    .withColumnExpression(e("DATEPART(yyyy,OrderDate)"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(groupBy.getItems().size(),1);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"DATEPART(yyyy,OrderDate)");
    }


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-group-by-transact-sql#examples-sql-data-warehouse-and-parallel-data-warehouse
     */

    // @formatter:off
    /**
     * GROUP BY CustomerKey WITH (DISTRIBUTED_AGG)
     */
    public GroupBy exampleF = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("CustomerKey"),true)
                    .and()
                .get();
    // @formatter:on


    /**
     * GROUP BY SalesAmount, SalesAmount*1.10
     */
    // @formatter:off
    public GroupBy exampleG5 = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("SalesAmount"))
                    .$(e_multiplication(
                            c("SalesAmount"),
                            e_number(1.10)
                    ))
                    .and()
                .get();
    // @formatter:on

}
