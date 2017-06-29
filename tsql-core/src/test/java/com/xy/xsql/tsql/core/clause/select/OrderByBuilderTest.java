package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.core.MockParent;
import com.xy.xsql.tsql.core.MockParentBuilder;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import com.xy.xsql.tsql.model.expression.GroupExpression;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.subquery.SubQueryBuilder.QUERY;
import static com.xy.xsql.tsql.core.predicate.Predicates.p_equal;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_addition;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.e_subtraction;

/**
 * Created by xiaoyao9184 on 2017/3/22.
 */
public class OrderByBuilderTest {

    // @formatter:off
    //parent+quick
    public OrderBy example1A = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("ProductID"))
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY ProductID;
     */
    @Test
    public void testExample1A(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("ProductID"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = orderBy.getItems().get(0);
        Assert.assertEquals(item.getOrderByExpression().toString(),"ProductID");
    }

    // @formatter:off
    //parent+quick
    public OrderBy example1D = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DATEPART(year, HireDate)"))
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY DATEPART(year, HireDate);
     */
    @Test
    public void testExample1D(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(e("DATEPART(year, HireDate)"))
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = orderBy.getItems().get(0);
        Assert.assertEquals(item.getOrderByExpression().toString(),"DATEPART(year, HireDate)");
    }

    // @formatter:off
    //parent+quick
    public OrderBy example2A = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("ProductID"))
                    .$Desc()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY ProductID DESC;
     */
    @Test
    public void testExample2A(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("ProductID"))
                    .withDesc()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = orderBy.getItems().get(0);
        Assert.assertEquals(item.getOrderByExpression().toString(),"ProductID");
        Assert.assertTrue(item.isUseDesc());
    }

    // @formatter:off
    //parent+quick
    public OrderBy example2B = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("Name"))
                    .$Asc()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY Name ASC ;
     */
    @Test
    public void testExample2B(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("Name"))
                    .withAsc()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = orderBy.getItems().get(0);
        Assert.assertEquals(item.getOrderByExpression().toString(),"Name");
        Assert.assertTrue(item.isUseAsc());
    }


    // @formatter:off
    //parent+quick
    public OrderBy example2C = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("FirstName"))
                    .$Asc()
                    .$(c("LastName"))
                    .$Desc()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY FirstName ASC, LastName DESC ;
     */
    @Test
    public void testExample2C(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("FirstName"))
                    .withAsc()
                    .and()
                .withItem()
                    .withExpression(c("LastName"))
                    .withDesc()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),2);

        Assert.assertEquals(orderBy.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = orderBy.getItems().get(0);
        Assert.assertEquals(item.getOrderByExpression().toString(),"FirstName");
        Assert.assertTrue(item.isUseAsc());

        Assert.assertEquals(orderBy.getItems().get(1).getClass(), OrderBy.Item.class);
        OrderBy.Item item1 = orderBy.getItems().get(1);
        Assert.assertEquals(item1.getOrderByExpression().toString(),"LastName");
        Assert.assertTrue(item1.isUseDesc());
    }


    // @formatter:off
    //parent+quick
    public OrderBy example3A1 = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DepartmentID"))
                    .$Offset(5)
                    .$Rows()
                    .and()
                .get();
    public OrderBy example3A2 = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DepartmentID"))
                    .$Offset(0).$Rows()
                    .$Fetch_Next(e_number(10)).$Rows()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY DepartmentID OFFSET 5 ROWS;
     * ORDER BY DepartmentID
     OFFSET 0 ROWS
     FETCH NEXT 10 ROWS ONLY;
     */
    @Test
    public void testExample3A(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("DepartmentID"))
                    .withAsc()
                    .and()
                .withOffsetFetch()
                    .withIntegerConstant(5)
                    .withUseRows()
                    .and()
                .build();


        OrderBy orderBy1 = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("DepartmentID"))
                    .withAsc()
                    .and()
                .withOffsetFetch()
                    .withIntegerConstant(0)
                    .withUseRows()
                    .withUseFetchNext()
                    .withFetchIntegerConstant(10)
                    .withUseFetchRows()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);
        Assert.assertEquals(orderBy.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        Assert.assertEquals(orderBy.getOffsetFetch().getIntegerConstant().toString(),"5");
        Assert.assertTrue(orderBy.getOffsetFetch().isUseRows());


        Assert.assertEquals(orderBy1.getItems().size(),1);
        Assert.assertEquals(orderBy1.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        Assert.assertEquals(orderBy1.getOffsetFetch().getIntegerConstant().toString(),"0");
        Assert.assertTrue(orderBy.getOffsetFetch().isUseRows());
        Assert.assertEquals(orderBy1.getOffsetFetch().getFetchIntegerConstant().toString(),"10");
        Assert.assertTrue(orderBy1.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    public OrderBy example3B = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DepartmentID")).$Asc()
                    .$Offset(e_variable("StartingRowNumber")).$Rows()
                    .$Fetch_Next(e_variable("FetchRows")).$Rows()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber ROWS
     FETCH NEXT @FetchRows ROWS ONLY;
     */
    @Test
    public void testExample3B(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("DepartmentID"))
                    .withAsc()
                    .and()
                .withOffsetFetch()
                    .withOffsetRowCountExpression(e_variable("StartingRowNumber"))
                    .withUseRows()
                    .withUseFetchNext()
                    .withFetchOffsetRowCountExpression(e_variable("FetchRows"))
                    .withUseFetchRows()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        Assert.assertTrue(orderBy.getItems().get(0).isUseAsc());

        Assert.assertEquals(orderBy.getOffsetFetch().getOffsetRowCountExpression().toString(),"@StartingRowNumber");
        Assert.assertTrue(orderBy.getOffsetFetch().isUseRows());
        Assert.assertTrue(!orderBy.getOffsetFetch().isUseFetchFirst());
        Assert.assertEquals(orderBy.getOffsetFetch().getFetchOffsetRowCountExpression().toString(),"@FetchRows");
        Assert.assertTrue(orderBy.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    public OrderBy example3C = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DepartmentID"))
                    .$Asc()
                    .$Offset(e_subtraction(
                                    e_variable("StartingRowNumber"),
                                    e_number(1))).$Rows()
                    .$Fetch_Next(e_addition(
                                    e_subtraction(
                                        e_variable("EndingRowNumber"),
                                        e_variable("StartingRowNumber")),
                                    e_number(1)
                            )).$Rows()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber - 1 ROWS
     FETCH NEXT @EndingRowNumber - @StartingRowNumber + 1 ROWS ONLY
     */
    @Test
    public void testExample3C(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("DepartmentID"))
                    .withAsc()
                    .and()
                .withOffsetFetch()
                    .withOffsetRowCountExpression(
                            e_subtraction(
                                    e_variable("StartingRowNumber"),
                                    e_number(1)))
                    .withUseRows()
                    .withUseFetchNext()
                    .withFetchOffsetRowCountExpression(
                            e_addition(
                                    e_subtraction(
                                        e_variable("EndingRowNumber"),
                                        e_variable("StartingRowNumber")),
                                    e_number(1)
                            ))
                    .withUseFetchRows()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        Assert.assertTrue(orderBy.getItems().get(0).isUseAsc());

        Assert.assertEquals(orderBy.getOffsetFetch().getOffsetRowCountExpression().getClass(), GroupExpression.class);
        Assert.assertTrue(orderBy.getOffsetFetch().isUseRows());
        Assert.assertEquals(orderBy.getOffsetFetch().getFetchOffsetRowCountExpression().getClass(), GroupExpression.class);
        Assert.assertTrue(orderBy.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick

    private Select.QuerySpecification querySpecification = QUERY()
            .$(c("PageSize"))
            .$From()
                .$(t("dbo","AppSettings"))
                .and()
            .$Where()
                .$Predicate(p_equal(c("AppSettingID"),e_number(1)))
                .and()
            .build();


    public OrderBy example3D = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DepartmentID"))
                    .$Asc()
                    .$Offset(e_variable("StartingRowNumber")).$Rows()
                    .$Fetch_Next(e_subquery(querySpecification)).$Rows()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber ROWS
     FETCH NEXT (SELECT PageSize FROM dbo.AppSettings WHERE AppSettingID = 1) ROWS ONLY;
     */
    @Test
    public void testExample3D(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("DepartmentID"))
                    .withAsc()
                    .and()
                .withOffsetFetch()
                    .withOffsetRowCountExpression(
                            e_variable("StartingRowNumber"))
                    .withUseRows()
                    .withUseFetchNext()
                    .withFetchOffsetRowCountExpression(
                            e_subquery(new Select.QuerySpecification())
                    )
                    .withUseFetchRows()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        Assert.assertTrue(orderBy.getItems().get(0).isUseAsc());

        Assert.assertEquals(orderBy.getOffsetFetch().getOffsetRowCountExpression().toString(), "@StartingRowNumber");
        Assert.assertTrue(orderBy.getOffsetFetch().isUseRows());
        Assert.assertEquals(orderBy.getOffsetFetch().getFetchOffsetRowCountExpression().getClass(), GroupExpression.class);
        Assert.assertTrue(orderBy.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    public OrderBy example3E = new MockParentBuilder<OrderByBuilder<MockParent<OrderBy>>,OrderBy>
                (OrderByBuilder.class,OrderBy.class)
                .$child()
                    .$(c("DepartmentID"))
                    .$Asc()
                    .$Offset(
                            e_subtraction(
                                    e_variable("StartingRowNumber"),
                                    e_number(1))).$Rows()
                    .$Fetch_Next(e_variable("RowCountPerPage")).$Rows()
                    .and()
                .get();
    // @formatter:on

    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber - 1 ROWS
     FETCH NEXT @RowCountPerPage ROWS ONLY;
     */
    @Test
    public void testExample3E(){
        // @formatter:off
        OrderBy orderBy = new OrderByBuilder<Void>()
                .withItem()
                    .withExpression(c("DepartmentID"))
                    .withAsc()
                    .and()
                .withOffsetFetch()
                    .withOffsetRowCountExpression(
                            e_subtraction(
                                    e_variable("StartingRowNumber"),
                                    e_number(1)))
                    .withUseRows()
                    .withUseFetchNext()
                    .withFetchOffsetRowCountExpression(e_variable("RowCountPerPage"))
                    .withUseFetchRows()
                    .and()
                .build();
        // @formatter:on

        Assert.assertEquals(orderBy.getItems().size(),1);

        Assert.assertEquals(orderBy.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        Assert.assertTrue(orderBy.getItems().get(0).isUseAsc());

        Assert.assertEquals(orderBy.getOffsetFetch().getOffsetRowCountExpression().getClass(), GroupExpression.class);
        Assert.assertTrue(orderBy.getOffsetFetch().isUseRows());
        Assert.assertEquals(orderBy.getOffsetFetch().getFetchOffsetRowCountExpression().toString(), "@RowCountPerPage");
        Assert.assertTrue(orderBy.getOffsetFetch().isUseFetchRows());
    }

}
