package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.select.GroupBy;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.expression.Expressions.e;

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


    // @formatter:off
    //parent+quick
    public GroupBy exampleA = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("SalesOrderID"))
                    .and()
                .get();
    // @formatter:on

    /**
     * GROUP BY SalesOrderID
     */
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
    public GroupBy exampleB = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(c("a","City"))
                    .and()
                .get();
    // @formatter:on

    /**
     * GROUP BY a.City
     */
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
    public GroupBy exampleC = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(e("DATEPART(yyyy,OrderDate)"))
                    .and()
                .get();
    // @formatter:on

    /**
     * GROUP BY DATEPART(yyyy,OrderDate)
     */
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

    /**
     * GROUP BY DATEPART(yyyy,OrderDate)
     */
    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleD(){
        // @formatter:off
        GroupBy groupBy = new GroupByBuilder<Void>()
                .withItem()._Base()
                    .withColumnExpression(e("DATEPART(yyyy,OrderDate)"))
                    .and()
                .build();

        //parent+quick
        MockParent<GroupBy> parent = new MockParentBuilder<GroupByBuilder<MockParent<GroupBy>>,GroupBy>
                (GroupByBuilder.class,GroupBy.class)
                .$child()
                    .$(e("DATEPART(yyyy,OrderDate)"))
                    .and();
        // @formatter:on

        Assert.assertEquals(groupBy.getItems().size(),1);

        Assert.assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        Assert.assertEquals(item.getExpression().toString(),"DATEPART(yyyy,OrderDate)");
    }

}
