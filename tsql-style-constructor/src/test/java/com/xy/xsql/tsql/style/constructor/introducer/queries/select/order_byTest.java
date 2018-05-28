package com.xy.xsql.tsql.style.constructor.introducer.queries.select;

import com.xy.xsql.tsql.model.elements.expressions.BinaryExpression;
import com.xy.xsql.tsql.model.elements.expressions.GroupExpression;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.select.OrderBy;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.Queries.$Query;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/9.
 */
public class order_byTest {

    /*
    Basic syntax
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-order-by-clause-transact-sql#a-namebasicsyntaxa-basic-syntax
     */


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY ProductID
     */
    public OrderBy example1A = ORDER_BY(
            c("ProductID")
    ).build();
    // @formatter:on

    @Test
    public void testExample1A(){
        assertEquals(example1A.getItems().size(),1);

        assertEquals(example1A.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = example1A.getItems().get(0);
        assertEquals(item.getOrderByExpression().toString(),"ProductID");
    }


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY DATEPART(year, HireDate)
     */
    public OrderBy example1D = ORDER_BY(
            c("DATEPART(year, HireDate)")
    ).build();
    // @formatter:on

    @Test
    public void testExample1D(){
        assertEquals(example1D.getItems().size(),1);

        assertEquals(example1D.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = example1D.getItems().get(0);
        assertEquals(item.getOrderByExpression().toString(),"DATEPART(year, HireDate)");
    }

    /*
    Specifying ascending and descending sort order
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-order-by-clause-transact-sql#a-namesortordera-specifying-ascending-and-descending-sort-order
     */

    // @formatter:off
    //parent+quick
    /**
     * ORDER BY ProductID DESC
     */
    public OrderBy example2A = ORDER_BY(
            c("ProductID"), DESC()
    ).build();
    // @formatter:on

    @Test
    public void testExample2A(){
        assertEquals(example2A.getItems().size(),1);

        assertEquals(example2A.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = example2A.getItems().get(0);
        assertEquals(item.getOrderByExpression().toString(),"ProductID");
        assertTrue(item.isUseDesc());
    }


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY Name ASC
     */
    public OrderBy example2B = ORDER_BY(
            c("Name"), ASC()
    ).build();
    // @formatter:on

    @Test
    public void testExample2B(){
        assertEquals(example2B.getItems().size(),1);

        assertEquals(example2B.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = example2B.getItems().get(0);
        assertEquals(item.getOrderByExpression().toString(),"Name");
        assertTrue(item.isUseAsc());
    }


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY FirstName ASC, LastName DESC
     */
    public OrderBy example2C = ORDER_BY(
            c("FirstName"), ASC()
    ).$$(
            c("LastName"), DESC()
    ).build();
    // @formatter:on

    @Test
    public void testExample2C(){
        assertEquals(example2C.getItems().size(),2);

        assertEquals(example2C.getItems().get(0).getClass(), OrderBy.Item.class);
        OrderBy.Item item = example2C.getItems().get(0);
        assertEquals(item.getOrderByExpression().toString(),"FirstName");
        assertTrue(item.isUseAsc());

        assertEquals(example2C.getItems().get(1).getClass(), OrderBy.Item.class);
        OrderBy.Item item1 = example2C.getItems().get(1);
        assertEquals(item1.getOrderByExpression().toString(),"LastName");
        assertTrue(item1.isUseDesc());
    }


    /*
    Specifying a collation
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-order-by-clause-transact-sql#a-namecollationa-specifying-a-collation
     */

    // @formatter:off
    //parent+quick
    /**
     * ORDER BY name
     */
    public OrderBy example31 = ORDER_BY(
            c("name")
    ).build();
    /**
     * ORDER BY name COLLATE Latin1_General_CS_AS
     */
    public OrderBy example32 = ORDER_BY(
            c("name")
            //TODO support COLLATE
//                    .$Collate("Latin1_General_CS_AS")
    ).build();
    // @formatter:on


    /*
    Specifying a conditional order
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-order-by-clause-transact-sql#a-namecasea-specifying-a-conditional-order
     */

    // @formatter:off
    //parent+quick
    /**
     * ORDER BY CASE SalariedFlag WHEN 1 THEN BusinessEntityID END DESC
     ,CASE WHEN SalariedFlag = 0 THEN BusinessEntityID END
     */
    public OrderBy example41 = ORDER_BY(
            e_case(c("SalariedFlag"))
                    .$When(e_number(1)).$Then(c("BusinessEntityID"))
                    .build(),
            DESC()
    ).$$(e_case()
                    .$When(p_equal(
                            c("SalariedFlag"),
                            e_number(0)
                    )).$Then(c("BusinessEntityID"))
                    .build()
    ).build();
    /**
     * ORDER BY CASE CountryRegionName WHEN 'United States' THEN TerritoryName
     ELSE CountryRegionName END
     */
    public OrderBy example42 = ORDER_BY(
            e_case(c("CountryRegionName"))
                    .$When(e_string("United States")).$Then(c("TerritoryName"))
                    .$Else(c("CountryRegionName"))
                    .build()
    ).build();
    // @formatter:on


    /*
    Using ORDER BY in a ranking function
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-order-by-clause-transact-sql#a-nameranka-using-order-by-in-a-ranking-function
    Omitted
     */


    /*
    Limiting the number of rows returned
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-order-by-clause-transact-sql#a-nameoffseta-limiting-the-number-of-rows-returned
     */

    // @formatter:off
    //parent+quick
    /**
     * ORDER BY DepartmentID OFFSET 5 ROWS
     */
    public OrderBy example6A1 = ORDER_BY(
            c("DepartmentID"),
            OFFSET(5,ROWS())
    ).build();

    /**
     * ORDER BY DepartmentID
     OFFSET 0 ROWS
     FETCH NEXT 10 ROWS ONLY
     */
    public OrderBy example6A2 = ORDER_BY(
            c("DepartmentID"),
            OFFSET(0,ROWS(),
                    FETCH(NEXT(),10,ROWS(),ONLY())
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample6A(){
        assertEquals(example6A1.getItems().size(),1);
        assertEquals(example6A1.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        assertEquals(example6A1.getOffsetFetch().getIntegerConstant().toString(),"5");
        assertTrue(example6A1.getOffsetFetch().isUseRows());


        assertEquals(example6A2.getItems().size(),1);
        assertEquals(example6A2.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        assertEquals(example6A2.getOffsetFetch().getIntegerConstant().toString(),"0");
        assertTrue(example6A2.getOffsetFetch().isUseRows());
        assertEquals(example6A2.getOffsetFetch().getFetchIntegerConstant().toString(),"10");
        assertTrue(example6A2.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber ROWS
     FETCH NEXT @FetchRows ROWS ONLY
     */
    public OrderBy example6B = ORDER_BY(
            c("DepartmentID"),ASC(),
            OFFSET(
                    e_variable("StartingRowNumber"),
                    ROWS(),
                    FETCH(NEXT(),e_variable("FetchRows"),ROWS(),ONLY())
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample6B(){
        assertEquals(example6B.getItems().size(),1);

        assertEquals(example6B.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        assertTrue(example6B.getItems().get(0).isUseAsc());

        assertEquals(example6B.getOffsetFetch().getOffsetRowCountExpression().toString(),"@StartingRowNumber");
        assertTrue(example6B.getOffsetFetch().isUseRows());
        assertTrue(!example6B.getOffsetFetch().isUseFetchFirst());
        assertEquals(example6B.getOffsetFetch().getFetchOffsetRowCountExpression().toString(),"@FetchRows");
        assertTrue(example6B.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber - 1 ROWS
     FETCH NEXT @EndingRowNumber - @StartingRowNumber + 1 ROWS ONLY
     */
    public OrderBy example6C = ORDER_BY(
            c("DepartmentID"),ASC(),
            OFFSET(
                    e_subtraction(
                            e_variable("StartingRowNumber"),
                            e_number(1)),
                    ROWS(),
                    FETCH(NEXT(),
                            e_addition(
                                    e_subtraction(
                                            e_variable("EndingRowNumber"),
                                            e_variable("StartingRowNumber")),
                                    e_number(1)
                            ),
                            ROWS(),ONLY())
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample6C(){
        assertEquals(example6C.getItems().size(),1);

        assertEquals(example6C.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        assertTrue(example6C.getItems().get(0).isUseAsc());

        assertEquals(example6C.getOffsetFetch().getOffsetRowCountExpression().getClass(), BinaryExpression.class);
        assertTrue(example6C.getOffsetFetch().isUseRows());
        assertEquals(example6C.getOffsetFetch().getFetchOffsetRowCountExpression().getClass(), BinaryExpression.class);
        assertTrue(example6C.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    private Select.QuerySpecification querySpecification = $Query()
            .$(c("PageSize"))
            .$From()
            .$(t("dbo","AppSettings"))
            .and()
            .$Where()
            .$(p_equal(
                    c("AppSettingID"),
                    e_number(1)
            ))
            .and()
            .build();

    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber ROWS
     FETCH NEXT (SELECT PageSize FROM dbo.AppSettings WHERE AppSettingID = 1) ROWS ONLY;
     */
    public OrderBy example6D = ORDER_BY(
            c("DepartmentID"),ASC(),
            OFFSET(
                    e_variable("StartingRowNumber"),
                    ROWS(),
                    FETCH(NEXT(),e_subquery(querySpecification),ROWS(),ONLY())
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample6D(){
        assertEquals(example6D.getItems().size(),1);

        assertEquals(example6D.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        assertTrue(example6D.getItems().get(0).isUseAsc());

        assertEquals(example6D.getOffsetFetch().getOffsetRowCountExpression().toString(), "@StartingRowNumber");
        assertTrue(example6D.getOffsetFetch().isUseRows());
        assertEquals(example6D.getOffsetFetch().getFetchOffsetRowCountExpression().getClass(), GroupExpression.class);
        assertTrue(example6D.getOffsetFetch().isUseFetchRows());
    }


    // @formatter:off
    //parent+quick
    /**
     * ORDER BY DepartmentID ASC
     OFFSET @StartingRowNumber - 1 ROWS
     FETCH NEXT @RowCountPerPage ROWS ONLY
     */
    public OrderBy example6E = ORDER_BY(
            c("DepartmentID"),ASC(),
            OFFSET(
                    e_subtraction(
                            e_variable("StartingRowNumber"),
                            e_number(1)),
                    ROWS(),
                    FETCH(NEXT(),e_variable("RowCountPerPage"),ROWS(),ONLY())
            )
    ).build();
    // @formatter:on

    @Test
    public void testExample6E(){
        assertEquals(example6E.getItems().size(),1);

        assertEquals(example6E.getItems().get(0).getOrderByExpression().toString(),"DepartmentID");
        assertTrue(example6E.getItems().get(0).isUseAsc());

        assertEquals(example6E.getOffsetFetch().getOffsetRowCountExpression().getClass(), BinaryExpression.class);
        assertTrue(example6E.getOffsetFetch().isUseRows());
        assertEquals(example6E.getOffsetFetch().getFetchOffsetRowCountExpression().toString(), "@RowCountPerPage");
        assertTrue(example6E.getOffsetFetch().isUseFetchRows());
    }


    /*
    Examples: Azure SQL Data Warehouse and Parallel Data Warehouse
    Using ORDER BY with UNION, EXCEPT, and INTERSECT
    Omitted
     */
}