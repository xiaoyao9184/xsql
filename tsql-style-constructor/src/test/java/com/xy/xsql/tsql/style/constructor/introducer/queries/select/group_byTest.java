package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.queries.select.GroupBy;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_multiplication;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_number;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.group_by.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/5/10.
 */
public class group_byTest {

    /**
     * GROUP BY c1, c2
     */
    @Test
    public void testBaseBuild(){
        // @formatter:off
        GroupBy groupBy = GROUP_BY(
                c("c1")
        ).$(
                c("c2")
        ).build();
        // @formatter:on

        assertEquals(groupBy.getItems().size(),2);

        assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        assertEquals(item.getExpression().toString(),"c1");

        assertEquals(groupBy.getItems().get(1).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item2 = (GroupBy.BaseItem) groupBy.getItems().get(1);
        assertEquals(item2.getExpression().toString(),"c2");
    }

    /**
     * GROUP BY c1, ROLLUP ( c2, (c3, c4) ), CUBE (c5), GROUPING SETS ( (), c6, CUBE( c7 ), (c8, ROLLUP ( c9 ) ) )
     */
    @Test
    public void testRollupBuild(){
        // @formatter:off
        GroupBy groupBy = GROUP_BY(
                c("c1")
        ).$$(
                ROLLUP(c("c2"))
                .$$(
                        c("c2"),
                        c("c4")
                )
        ).$$(
                CUBE(c("c5"))
        ).$$(
                GROUPING_SETS()
                .$$(c("c6"))
                .$$(CUBE(c("c7")))
                .$$(
                        $(
                                c("c8")
                        ).$$(ROLLUP(
                                c("c9")
                        ))
                )
        ).build();
        // @formatter:on

        assertEquals(groupBy.getItems().size(),4);

        assertEquals(groupBy.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) groupBy.getItems().get(0);
        assertEquals(item.getExpression().toString(),"c1");

        assertEquals(groupBy.getItems().get(1).getClass(), GroupBy.RollupItem.class);
        GroupBy.RollupItem item1 = (GroupBy.RollupItem) groupBy.getItems().get(1);
        assertEquals(item1.getGroupByExpressionList().size(),2);

        assertEquals(groupBy.getItems().get(2).getClass(), GroupBy.CubeItem.class);
        GroupBy.CubeItem item2 = (GroupBy.CubeItem) groupBy.getItems().get(2);
        assertEquals(item2.getGroupByExpressionList().size(),1);

        assertEquals(groupBy.getItems().get(3).getClass(), GroupBy.GroupingSetsItem.class);
        GroupBy.GroupingSetsItem item3 = (GroupBy.GroupingSetsItem) groupBy.getItems().get(3);
        assertEquals(item3.getGroupingSetItemList().size(),4);
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
    public GroupBy exampleA = GROUP_BY(
            c("SalesOrderID")
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getItems().size(),1);

        assertEquals(exampleA.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) exampleA.getItems().get(0);
        assertEquals(item.getExpression().toString(),"SalesOrderID");
    }


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY a.City
     */
    public GroupBy exampleB = GROUP_BY(
            c("a","City")
    ).build();
    // @formatter:on

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getItems().size(),1);

        assertEquals(exampleB.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) exampleB.getItems().get(0);
        assertEquals(item.getExpression().toString(),"a.City");
    }


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY DATEPART(yyyy,OrderDate)
     */
    public GroupBy exampleC = GROUP_BY(
            e("DATEPART(yyyy,OrderDate)")
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleC(){
        assertEquals(exampleC.getItems().size(),1);

        assertEquals(exampleC.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) exampleC.getItems().get(0);
        assertEquals(item.getExpression().toString(),"DATEPART(yyyy,OrderDate)");
    }


    // @formatter:off
    //parent+quick
    /**
     * GROUP BY DATEPART(yyyy,OrderDate)
     */
    public GroupBy exampleD = GROUP_BY(
            e("DATEPART(yyyy,OrderDate)")
    ).build();
    // @formatter:on

    @SuppressWarnings("Duplicates")
    @Test
    public void testExampleD(){
        assertEquals(exampleD.getItems().size(),1);

        assertEquals(exampleD.getItems().get(0).getClass(), GroupBy.BaseItem.class);
        GroupBy.BaseItem item = (GroupBy.BaseItem) exampleD.getItems().get(0);
        assertEquals(item.getExpression().toString(),"DATEPART(yyyy,OrderDate)");
    }


    /*
    Examples: SQL Data Warehouse and Parallel Data Warehouse
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-group-by-transact-sql#examples-sql-data-warehouse-and-parallel-data-warehouse
     */

    // @formatter:off
    /**
     * GROUP BY CustomerKey WITH (DISTRIBUTED_AGG)
     */
    public GroupBy exampleF = GROUP_BY(
            c("CustomerKey"),WITH_DISTRIBUTED_AGG()
    ).build();
    // @formatter:on


    /**
     * GROUP BY SalesAmount, SalesAmount*1.10
     */
    // @formatter:off
    public GroupBy exampleG5 = GROUP_BY(
            c("SalesAmount")
    ).$(
            e_multiplication(
                    c("SalesAmount"),
                    e_number(1.10)
            )
    ).build();
    // @formatter:on

}