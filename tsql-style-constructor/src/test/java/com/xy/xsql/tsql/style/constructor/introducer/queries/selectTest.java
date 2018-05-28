package com.xy.xsql.tsql.style.constructor.introducer.queries;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.queries.Select;
import com.xy.xsql.tsql.model.queries.predicates.ComparisonSubQuery;
import com.xy.xsql.tsql.style.constructor.builder.queries.b_TOP;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_money;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.TableNameFactory.t;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.*;
import static com.xy.xsql.tsql.builder.chain.queries.predicates.Predicates.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.from.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.hints.table_hint.INDEX;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.option.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.having.HAVING;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.into.INTO;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.order_by.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.group_by.GROUP_BY;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.select.AS;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.select_.*;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.top.TOP;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.values.VALUES;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.where.WHERE;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/11.
 */
public class selectTest {

    /**
     * SELECT * FROM table
     */
    @Test
    public void testBaseBuild(){
        Select.QuerySpecification select = SELECT(
                $(),
                FROM(
                        t("table")
                )
        ).build();

        assertEquals(select.getFrom().getTableSourceList().size(),1);
        assertEquals(select.getSelectList().size(),1);
    }

    /**
     * SELECT ALL * FROM table
     */
    @Test
    public void testAllBuild(){
        Select.QuerySpecification select = SELECT(
                ALL(),
                $(),
                FROM(t("table"))
        ).build();

        assertTrue(select.isUseAll());
    }

    /**
     * SELECT DISTINCT * FROM table
     */
    @Test
    public void testDistinctBuild(){
        Select.QuerySpecification select = SELECT(
                DISTINCT(),
                $(),
                FROM(t("table"))
        ).build();

        assertTrue(select.isUseDistinct());
    }

    /**
     * SELECT TOP 50 * FROM table
     * SELECT TOP 50 WILDCARD_PERCENT * FROM table
     */
    @Test
    public void testTopBuild(){
        Select.QuerySpecification select = SELECT(
                TOP(50),
                $(),
                FROM(t("table"))
        ).build();

        assertTrue(select.getTop().getExpression() instanceof NumberConstant);
        assertEquals(((NumberConstant)select.getTop().getExpression()).getNumber().toString(), "50");

        select = SELECT(
                TOP(50, top.PERCENT()),
                $(),
                FROM(t("table"))
        ).build();

        assertTrue(select.getTop().isUsePercent());
    }

    /**
     * SELECT * FROM table,table2
     */
    @Test
    public void test2TableBuild(){
        Select.QuerySpecification select = SELECT(
                $(),
                FROM(t("table")).$$(t("table2"))
        ).build();

        assertEquals(select.getFrom().getTableSourceList().size(),2);
    }

    /**
     * SELECT * FROM table LEFT JOIN table2
     */
    @Test
    public void testTableJoinBuild(){
        Select.QuerySpecification select = SELECT(
                $(),
                FROM(t("table"))
                    .LEFT(JOIN(t("table2")))
        ).build();

        assertEquals(select.getFrom().getTableSourceList().size(),1);
    }

    /**
     * SELECT * FROM (VALUES (1, 2), (3, 4), (5, 6), (7, 8), (9, 10) ) AS MyTable(a, b)
     */
    @Test
    public void testDerivedTable(){
        // @formatter:off
        Select.QuerySpecification select = SELECT(
                $(),
                FROM(VALUES(
                        e_number(1),e_number(2)
                ).$$(
                        e_number(3),e_number(4)
                ).$$(
                        e_number(5),e_number(6)
                ).$$(
                       e_number(7),e_number(8)
                ).$$(
                       e_number(9),e_number(10)
                ))
        ).build();
        // @formatter:on
    }


    /*
    See https://docs.microsoft.com/zh-cn/sql/t-sql/queries/select-examples-transact-sql
     */

    // @formatter:off
    /**
     * SELECT *
    FROM Production.Product
    ORDER BY Name ASC
     */
    public Select exampleA1 = $(
            SELECT(
                $(),
                FROM(t("",""))
            ),
            ORDER_BY(
                    c("Name"),ASC()
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT p.*
    FROM Production.Product AS p
    ORDER BY Name ASC
     */
    public Select exampleA2 = $(
            SELECT(
                    $(t("p")),
                    FROM(
                            t("Production","Product"),
                            AS("P")
                    )
            ),
            ORDER_BY(
                    c("Name"),ASC()
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT Name, ProductNumber, ListPrice AS Price
    FROM Production.Product
    ORDER BY Name ASC
     */
    public Select exampleA3 = $(
            SELECT(
                $(c("Name"))
                .$$(c("ProductNumber"))
                .$$(c("ListPrice "),"Price"),
                FROM(t("Production","Product"))
            ),
            ORDER_BY(c("Name"),ASC())
    ).build();
    // @formatter:on

//    USE AdventureWorks2012;
//    GO
//    SELECT Name, ProductNumber, ListPrice AS Price
//    FROM Production.Product
//    WHERE ProductLine = 'R'
//    $AND DaysToManufacture < 4
//    ORDER BY Name ASC;
//    GO
    // @formatter:off
    /**
     * SELECT Name, ProductNumber, ListPrice AS Price
    FROM Production.Product
    WHERE ProductLine = 'R'
    AND DaysToManufacture < 4
    ORDER BY Name ASC
     */
    public Select exampleA4 = $(
            SELECT(
                $(c("Name"))
                .$$(c("ProductNumber"))
                .$$(c("ListPrice "),AS("Price")),
                FROM(t("Production","Product")),
                WHERE(p_equal(
                        c("ProductLine"),
                        e_string("R")
                )).AND(p_less(
                        c("DaysToManufacture"),
                        e_number(4)
                ))
            ),ORDER_BY(c("Name"),ASC())
    ).build();
    // @formatter:on

    @Test
    public void testExampleA(){
        Select.QuerySpecification query = exampleA1.getQueryExpression().getQuerySpecification();
        assertEquals(query.getSelectList().size(),1);
//        assertTrue(select.getList().get(0).isUseAll());
//
//        assertEquals(select1.getList().size(),1);
//        assertEquals(select1.getList().get(0).getTableViewName().toString(),"p");
//        assertTrue(select1.getList().get(0).isUseTableAll());
    }


    // @formatter:off
    /**
     * SELECT p.Name AS ProductName,
    NonDiscountSales = (OrderQty * UnitPrice),
    Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)
    FROM Production.Product AS p
    INNER JOIN Sales.SalesOrderDetail AS sod
    ON p.ProductID = sod.ProductID
    ORDER BY ProductName DESC
     */
    public Select exampleB1 = $(
            SELECT(
                    $(c("p","Name"),AS("ProductName"))
                    .$$(e_assignment(
                            c("NonDiscountSales"),
                            e(e_multiplication(
                                    c("OrderQty"),
                                    c("UnitPrice")
                            ))
                    ))
                    .$$(e_assignment(
                            c("Discounts"),
                            e(e_multiplication(
                                    e(e_multiplication(
                                            c("OrderQty"),
                                            c("UnitPrice"))),
                                    c("UnitPriceDiscount")
                            ))
                    )),
                    FROM(t("Production","Product"),AS("p"))
                    .INNER(JOIN(t("Sales","SalesOrderDetail"),AS("sod"))
                        ,ON(p_equal(
                                c("p","ProductID"),
                                c("sod","ProductID")
                            ))
                    )
            ),
            ORDER_BY(c("ProductName"),DESC())
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',
    p.Name AS ProductName
    FROM Production.Product AS p
    INNER JOIN Sales.SalesOrderDetail AS sod
    ON p.ProductID = sod.ProductID
    ORDER BY ProductName ASC
     */
    public Select exampleB2 = $(
            SELECT(
                    $(e_string("Total income is"))
                    .$$(e(e_multiplication(
                            e(e_multiplication(
                                    c("OrderQty"),
                                    c("UnitPrice")
                            )),
                            e(e_subtraction(
                                    e_number(1.0),
                                    c("UnitPriceDiscount")
                            )))
                    ))
                    .$$(e_string(" for "))
                    .$$(c("p","Name"),"ProductName"),
                    FROM(t("Production","Product"),AS("p"))
                    .INNER(
                            JOIN(t("Sales","SalesOrderDetail"),AS("sod")),
                            ON(p_equal(
                                    c("p","ProductID"),
                                    c("dos","ProductID")
                            ))
                    )
            ),ORDER_BY(c("ProductName"),ASC())
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT DISTINCT JobTitle
     FROM HumanResources.Employee
     ORDER BY JobTitle
     */
    public Select exampleC = $(
            SELECT(
                    DISTINCT(),
                    $(c("JobTitle")),
                    FROM(t("HumanResources","Employee"))
            ),
            ORDER_BY(c("JobTitle"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT *
     INTO #Bicycles
     FROM AdventureWorks2012.Production.Product
     WHERE ProductNumber LIKE 'BK%'
     */
    public Select exampleD1 = $(
            SELECT(
                    $(),
                    INTO(t("#Bicycles")),
                    FROM(t("AdventureWorks2012","Production","Product")),
                    WHERE(p_like(
                            c("ProductNumber"),
                            e_string("BK%")
                    ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT * INTO dbo.NewProducts
     FROM Production.Product
     WHERE ListPrice > $25
     AND ListPrice < $100
     */
    public Select exampleD2 = $(
            SELECT(
                    $(),
                    INTO(t("dbo","NewProducts")),
                    FROM(t("Production","Product")),
                    WHERE(p_greater(
                            c("ListPrice"),
                            c_money(25)
                    )).AND(p_less(
                            c("ListPrice"),
                            c_money(100)
                    ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT DISTINCT Name
     FROM Production.Product AS p
     WHERE EXISTS
         (SELECT *
         FROM Production.ProductModel AS pm
         WHERE p.ProductModelID = pm.ProductModelID
            AND pm.Name LIKE 'Long-Sleeve Logo Jersey%')
     */
    public Select exampleE1 = $(
            SELECT(
                    DISTINCT(),
                    $(c("Name")),
                    FROM(t("Production","Product"),AS("p")),
                    WHERE(
                            p_exists(
                                    $(SELECT(
                                            $(),
                                            FROM(t("Production","ProductModel"),AS("")),
                                            WHERE(
                                                  p_equal(
                                                          c("p","ProductModelID"),
                                                          c("pm","ProductModelID")
                                                  )
                                            ).AND(
                                                    p_like(
                                                            c("pm","Name"),
                                                            e_string("Long-Sleeve Logo Jersey%")
                                                    )
                                            )
                                    )).build()
                            )
                    )
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT DISTINCT Name
     FROM Production.Product
     WHERE ProductModelID IN
         (SELECT ProductModelID
         FROM Production.ProductModel
         WHERE Name LIKE 'Long-Sleeve Logo Jersey%')
     */
    public Select exampleE2 = $(
            SELECT(
                    DISTINCT(),
                    $(c("Name")),
                    FROM(t("Production","Product")),
                    WHERE(p_in(
                            c("ProductModelID"),
                            $(SELECT(
                                    $(c("ProductModelID")),
                                    FROM(t("Production","ProductModel"))
                                    ,WHERE(p_like(
                                            c("Name"),
                                            e_string("Long-Sleeve Logo Jersey%")
                                    ))
                                    )
                            ).build()
                    ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT DISTINCT p.LastName, p.FirstName
     FROM Person.Person AS p
     JOIN HumanResources.Employee AS e
         ON e.BusinessEntityID = p.BusinessEntityID WHERE 5000.00 IN
         (SELECT Bonus
         FROM Sales.SalesPerson AS sp
         WHERE e.BusinessEntityID = sp.BusinessEntityID)
     */
    public Select exampleE3 = $(
            SELECT(
                    DISTINCT(),
                    $(),
                    FROM(t("Person","Person"),AS("p"))
                    .$(JOIN(t("HumanResources","Employee"),AS("e"))
                        ,ON(
                              p_equal(
                                    c("e","BusinessEntityID"),
                                    c("p","BusinessEntityID")
                        ))
                    ),
                    WHERE(
                            p_in(
                                    e_number(5000.00),
                                    $(
                                          SELECT(
                                                  $(c("Bonus")),
                                                  FROM(t("Sales","SalesPerson"),AS("sp")),
                                                  WHERE(p_equal(
                                                          c("e","BusinessEntityID"),
                                                          c("sp","BusinessEntityID")
                                                  ))
                                          )
                                    ).build()
                            )
                    )
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT p1.ProductModelID
     FROM Production.Product AS p1
     GROUP BY p1.ProductModelID
     HAVING MAX(p1.ListPrice) >= ALL
         (SELECT AVG(p2.ListPrice)
         FROM Production.Product AS p2
         WHERE p1.ProductModelID = p2.ProductModelID)
     */
    public Select exampleE4 = $(
            SELECT(
                    $(c("p1.ProductModelID")),
                    FROM(t("Production.Product"),AS("p1")),
                    GROUP_BY(
                            c("p1.ProductModelID")
                    ),
                    HAVING(p_greater_equal(
                            e("MAX(p1.ListPrice)"),
                            ComparisonSubQuery.ALL_SOME_ANY.ALL,
                            $(SELECT(
                                    $(e("AVG(p2.ListPrice)"))
                                    ,FROM(t("Production","Product"),AS("p2"))
                                    ,WHERE(p_equal(
                                                c("p1","ProductModelID"),
                                                c("p2","ProductModelID")
                                    ))
                            )).build())
                    )
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT DISTINCT pp.LastName, pp.FirstName
     FROM Person.Person pp JOIN HumanResources.Employee e
     ON e.BusinessEntityID = pp.BusinessEntityID WHERE pp.BusinessEntityID IN
     (SELECT SalesPersonID
     FROM Sales.SalesOrderHeader
     WHERE SalesOrderID IN
     (SELECT SalesOrderID
     FROM Sales.SalesOrderDetail
     WHERE ProductID IN
     (SELECT ProductID
     FROM Production.Product p
     WHERE ProductNumber = 'BK-M68B-42')))
     */
    public Select exampleE5 = $(SELECT(
            DISTINCT(),
            $(c("pp.LastName")).$$(c("pp.FirstName")),
            FROM(t("Person.Person","pp"))
            .$(JOIN(t("HumanResources.Employee"),"e"),
                    ON(p_equal(
                                    c("e","BusinessEntityID"),
                                    c("pp","BusinessEntityID")
                    ))),
            WHERE(p_in(
                    c("pp.BusinessEntityID"),
                    $(SELECT(
                            $(c("SalesPersonID")),
                            FROM(t("Sales.SalesOrderHeader")),
                            WHERE(p_in(
                                    c("Sales.SalesOrderHeader"),
                                    $(SELECT(
                                            $(c("ProductID")),
                                            FROM(t("Production.Product"),"p"),
                                            WHERE(p_equal(
                                                    c("ProductNumber"),
                                                    e_string("BK-M68B-42")
                                            ))
                                    )).build()
                            ))
                    )).build()
            ))
    )).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT SalesOrderID, SUM(LineTotal) AS SubTotal
    FROM Sales.SalesOrderDetail
    GROUP BY SalesOrderID
    ORDER BY SalesOrderID
     */
    public Select exampleF = $(SELECT(
            $(c("SalesOrderID")).$$(e("SUM(LineTotal)"),AS("SubTotal")),
            FROM(t("Sales.SalesOrderDetail")),
            GROUP_BY(c("SalesOrderID"))
    ),ORDER_BY(c("SalesOrderID"))).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],
        SUM(LineTotal) AS SubTotal
    FROM Sales.SalesOrderDetail
    GROUP BY ProductID, SpecialOfferID
    ORDER BY ProductID
     */
    public Select exampleG = $(
            SELECT(
                    $(c("ProductID"))
                    .$$(c("SpecialOfferID"))
                    .$$(e("AVG(UnitPrice)"),AS("[Average Price]"))
                    .$$(e("SUM(LineTotal)"),AS("SubTotal")),
                    FROM(t("Sales","SalesOrderDetail")),
                    GROUP_BY(c("ProductID")).$$(c("SpecialOfferID"))
            ),
            ORDER_BY(c("ProductID"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]
    FROM Production.Product
    WHERE ListPrice > $1000
    GROUP BY ProductModelID
    ORDER BY ProductModelID
     */
    public Select exampleH = $(
            SELECT(
                    $(c("ProductModelID")).$$(e("AVG(ListPrice)"),AS("[Average List Price]")),
                    FROM(t("Production","Product")),
                    WHERE(p_greater(
                                c("ListPrice"),
                                c_money(1000)
                        )),
                    GROUP_BY(c("ProductModelID"))
            ),
            ORDER_BY(c("ProductModelID"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT AVG(OrderQty) AS [Average Quantity],
    NonDiscountSales = (OrderQty * UnitPrice)
    FROM Sales.SalesOrderDetail
    GROUP BY (OrderQty * UnitPrice)
    ORDER BY (OrderQty * UnitPrice) DESC
     */
    public Select exampleI = $(
            SELECT(
                    $(e("AVG(OrderQty)"),AS("[Average Quantity]"))
                    .$$(e_assignment(
                            c("NonDiscountSales"),
                            e(e_multiplication(
                                    c("OrderQty"),c("UnitPrice")
                            ))
                    )),
                    FROM(t("Sales.SalesOrderDetail")),
                    GROUP_BY(e(e_multiplication(
                                c("OrderQty"),
                                c("UnitPrice")
                        )))
            ),
            ORDER_BY(e(e_multiplication(
                            c("OrderQty"),
                            c("UnitPrice")
                    )))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID, AVG(UnitPrice) AS [Average Price]
    FROM Sales.SalesOrderDetail
    WHERE OrderQty > 10
    GROUP BY ProductID
    ORDER BY AVG(UnitPrice)
     */
    public Select exampleJ = $(
            SELECT(
                    $(c("ProductID"))
                    .$$(e("AVG(UnitPrice)"),AS("[Average Price]")),
                    FROM(t("Sales.SalesOrderDetail")),
                    WHERE(p_greater(
                                c("OrderQty"),
                                e_number(10)
                        )),
                    GROUP_BY(c("ProductID"))
            ),
            ORDER_BY(e("AVG(UnitPrice)"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID
    FROM Sales.SalesOrderDetail
    GROUP BY ProductID
    HAVING AVG(OrderQty) > 5
    ORDER BY ProductID
     */
    public Select exampleK1 = $(
            SELECT(
                    $(c("ProductID")),
                    FROM(t("Sales","SalesOrderDetail")),
                    GROUP_BY(c("ProductID")),
                    HAVING(p_greater(
                                e("AVG(OrderQty)"),
                                e_number(5)
                        ))
            ),
            ORDER_BY(c("ProductID"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT SalesOrderID, CarrierTrackingNumber
    FROM Sales.SalesOrderDetail
    GROUP BY SalesOrderID, CarrierTrackingNumber
    HAVING CarrierTrackingNumber LIKE '4BD%'
    ORDER BY SalesOrderID
     */
    public Select exampleK2 = $(
            SELECT(
                    $(c("SalesOrderID")).$$(c("CarrierTrackingNumber")),
                    FROM(t("Sales","SalesOrderDetail")),
                    GROUP_BY(c("SalesOrderID")).$$(c("CarrierTrackingNumber")),
                    HAVING(p_like(
                                e("CarrierTrackingNumber"),
                                e_string("4BD%")
                        ))
            ),
            ORDER_BY(c("SalesOrderID"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID
    FROM Sales.SalesOrderDetail
    WHERE UnitPrice < 25.00
    GROUP BY ProductID
    HAVING AVG(OrderQty) > 5
    ORDER BY ProductID
     */
    public Select exampleL = $(
            SELECT(
                    $(c("ProductID")),
                    FROM(t("Sales","SalesOrderDetail")),
                    WHERE(p_less(
                                c("UnitPrice"),
                                e_number(25.00)
                        )),
                    GROUP_BY(c("ProductID")),
                    HAVING(p_greater(
                                e("AVG(OrderQty)"),
                                e_number(5)
                        ))
            ),
            ORDER_BY(c("ProductID"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID, AVG(OrderQty) AS AverageQuantity, SUM(LineTotal) AS Total
    FROM Sales.SalesOrderDetail
    GROUP BY ProductID
    HAVING SUM(LineTotal) > $1000000.00
    AND AVG(OrderQty) < 3
     */
    public Select exampleM1 = $(
            SELECT(
                    $(c("ProductID"))
                    .$$(e("AVG(OrderQty)"),AS("AverageQuantity"))
                    .$$(e("SUM(LineTotal)"),AS("Total")),
                    FROM(t("Sales","SalesOrderDetail")),
                    GROUP_BY(c("ProductID")),
                    HAVING(p_greater(
                                e("SUM(LineTotal)"),
                                c_money(1000000.00)
                        )).AND(p_less(
                                e("AVG(OrderQty)"),
                                e_number(3)
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID, Total = SUM(LineTotal)
    FROM Sales.SalesOrderDetail
    GROUP BY ProductID
    HAVING SUM(LineTotal) > $2000000.00
     */
    public Select exampleM2 = $(
            SELECT(
                    $(c("ProductID"))
                    .$$(e_assignment(c("Total"),e("SUM(LineTotal)"))),
                    FROM(t("Sales","SalesOrderDetail")),
                    GROUP_BY(c("ProductID")),
                    HAVING(p_greater(
                                e("SUM(LineTotal)"),
                                c_money(2000000.00)
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID, SUM(LineTotal) AS Total
    FROM Sales.SalesOrderDetail
    GROUP BY ProductID
    HAVING COUNT(*) > 1500
     */
    public Select exampleM3 = $(
            SELECT(
                    $(c("ProductID"))
                    .$$(e("SUM(LineTotal)"),AS("Total")),
                    FROM(t("Sales","SalesOrderDetail")),
                    GROUP_BY(c("ProductID")),
                    HAVING(p_greater(
                                e("COUNT(*)"),
                                e_number(1500)
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT pp.FirstName, pp.LastName, e.NationalIDNumber
    FROM HumanResources.Employee AS e WITH (INDEX(AK_Employee_NationalIDNumber))
    JOIN Person.Person AS pp on e.BusinessEntityID = pp.BusinessEntityID
    WHERE LastName = 'Johnson'
     */
    public Select exampleN1 = $(
            SELECT(
                    $(c("pp","FirstName"))
                    .$$(c("pp","LastName"))
                    .$$(c("e","NationalIDNumber")),
                    FROM(t("HumanResources","Employee"),
                            WITH(INDEX("AK_Employee_NationalIDNumber")))
                    .$(JOIN(t("Person","Person"),AS("pp")),
                            ON(p_equal(
                                        c("e","BusinessEntityID"),
                                        c("pp","BusinessEntityID")
                                ))),
                    WHERE(p_equal(
                                c("LastName"),
                                e_string("Johnson")
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT pp.LastName, pp.FirstName, e.JobTitle
    FROM HumanResources.Employee AS e WITH (INDEX = 0) JOIN Person.Person AS pp
    ON e.BusinessEntityID = pp.BusinessEntityID
    WHERE LastName = 'Johnson'
     */
    public Select exampleN2 = $(
            SELECT(
                    $(c("pp","LastName"))
                    .$$(c("pp","FirstName"))
                    .$$(c("e","JobTitle")),
                    FROM(t("HumanResources","Employee"),
                            WITH(INDEX()))
                    .$(JOIN(t("Person","Person"),AS("pp")),
                            ON(p_equal(
                                        c("e","BusinessEntityID"),
                                        c("pp","BusinessEntityID")
                                ))),
                    WHERE(p_equal(
                                c("LastName"),
                                e_string("Johnson")
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductID, OrderQty, SUM(LineTotal) AS Total
    FROM Sales.SalesOrderDetail
    WHERE UnitPrice < $5.00
    GROUP BY ProductID, OrderQty
    ORDER BY ProductID, OrderQty
    OPTION (HASH GROUP, FAST 10)
     */
    public Select exampleM = $(
            SELECT(
                    $(c("ProductID"))
                    .$$(c("OrderQty"))
                    .$$(e("SUM(LineTotal)"),AS("Total")),
                    FROM(t("Sales","SalesOrderDetail")),
                    WHERE(p_less(
                                c("UnitPrice"),
                                c_money(5.00)
                        )),
                    GROUP_BY(c("ProductID"))
                            .$$(c("OrderQty"))
            ),
            ORDER_BY(c("ProductID"))
                    .$$(c("OrderQty")),
            OPTION(HASH_GROUP(),FAST(10))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT BusinessEntityID, JobTitle, HireDate, VacationHours, SickLeaveHours
    FROM HumanResources.Employee AS e1
    UNION
    SELECT BusinessEntityID, JobTitle, HireDate, VacationHours, SickLeaveHours
    FROM HumanResources.Employee AS e2
    OPTION (MERGE UNION)
     */
    public Select exampleO = $(
            SELECT(
                    $(c("BusinessEntityID"))
                    .$$(c("JobTitle"))
                    .$$(c("HireDate"))
                    .$$(c("VacationHours"))
                    .$$(c("SickLeaveHours")),
                    FROM(t("HumanResources","Employee"),AS("e1"))
            ).UNION_(
                    SELECT(
                         $(c("BusinessEntityID"))
                        .$$(c("JobTitle"))
                        .$$(c("HireDate"))
                        .$$(c("VacationHours"))
                        .$$(c("SickLeaveHours")),
                        FROM(t("HumanResources","Employee"),AS("e2"))
                    )
            ),
            OPTION(MERGE_UNION())
    ).build();
    // @formatter:on

    // @formatter:off
    /**
     * -- Create Gloves table.
    SELECT ProductModelID, Name
    INTO dbo.Gloves
    FROM Production.ProductModel
    WHERE ProductModelID IN (3, 4)
     */
    public Select exampleP1 = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    INTO(t("dbo.Gloves")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
            )
    ).build();
    // @formatter:on

    // @formatter:off
    /**
     * SELECT ProductModelID, Name
    FROM Production.ProductModel
    WHERE ProductModelID NOT IN (3, 4)
    UNION
    SELECT ProductModelID, Name
    FROM dbo.Gloves
    ORDER BY Name
     */
    public Select exampleP2 = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_not_in(
                                    c("ProductModelID"),
                                    e_number(3),
                                    e_number(4)
                            ))
            ).UNION(SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    FROM(t("dbo","Gloves"))
            )),
            ORDER_BY(c("Name"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * -- Create Gloves table.
    SELECT ProductModelID, Name
    INTO dbo.Gloves
    FROM Production.ProductModel
    WHERE ProductModelID IN (3, 4)
     */
    public Select exampleQ1 = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    INTO(t("dbo.Gloves")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductModelID, Name
    INTO dbo.ProductResults
    FROM Production.ProductModel
    WHERE ProductModelID NOT IN (3, 4)
    UNION
    SELECT ProductModelID, Name
    FROM dbo.Gloves
     */
    public Select exampleQ2 = $(
            SELECT(
                    $(c("ProductModelID"))
                        .$$(c("Name")),
                    INTO(t("dbo.ProductResults")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_not_in(
                                    c("ProductModelID"),
                                    e_number(3),
                                    e_number(4)
                            ))
            ).UNION(SELECT(
                    $(c("ProductModelID"))
                        .$$(c("Name")),
                    FROM(t("dbo","Gloves"))
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductModelID, Name
    FROM dbo.ProductResults
     */
    public Select exampleQ3 = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    FROM(t("dbo","ProductResults"))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * -- Create Gloves table.
    SELECT ProductModelID, Name
    INTO dbo.Gloves
    FROM Production.ProductModel
    WHERE ProductModelID IN (3, 4)
     */
    public Select exampleR1 = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    INTO(t("dbo.Gloves")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
            )
    ).build();
    // @formatter:on


    //    /* INCORRECT */
//    USE AdventureWorks2012;
//    GO
//    SELECT ProductModelID, Name
//    FROM Production.ProductModel
//    WHERE ProductModelID NOT IN (3, 4)
//    ORDER BY Name
//            UNION
//    SELECT ProductModelID, Name
//    FROM dbo.Gloves;
//    GO
    // @formatter:off
    public Select exampleR2_INCORRECT = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_not_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
        //CANT use ORDER BY in QuerySpecification
//                    .$OrderBy()
//                        .$(c("Name"))
//                        .and()
            ).UNION(SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    FROM(t("dbo","Gloves"))
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT ProductModelID, Name
    FROM Production.ProductModel
    WHERE ProductModelID NOT IN (3, 4)
    UNION
    SELECT ProductModelID, Name
    FROM dbo.Gloves
    ORDER BY Name
     */
    public Select exampleR3 = $(
            SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name")),
                    FROM(t("Production","ProductModel")),
                    WHERE(p_not_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
            ).UNION(SELECT(
                    $(c("ProductModelID"))
                    .$$(c("Name"))
                    ,FROM(t("dbo","Gloves"))
            ))
            ,ORDER_BY(c("Name"))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT pp.LastName, pp.FirstName, e.JobTitle
    INTO dbo.EmployeeOne
    FROM Person.Person AS pp JOIN HumanResources.Employee AS e
    ON e.BusinessEntityID = pp.BusinessEntityID
    WHERE LastName = 'Johnson'
     */
    public Select exampleS1 = $(
            SELECT(
                    $(c("pp","LastName"))
                    .$$(c("pp","FirstName"))
                    .$$(c("e","JobTitle"))
                    ,INTO(t("dbo.EmployeeOne"))
                    ,FROM(t("Person","Person"),AS("pp"))
                            .$(JOIN(t("HumanResources","Employee"),AS("e"))
                            ,ON(p_equal(
                                        c("e","BusinessEntityID"),
                                        c("pp","BusinessEntityID")
                                )))
                    ,WHERE(p_equal(
                                c("LastName"),
                                e_string("Johnson")
                        ))
            )
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT LastName, FirstName, JobTitle
    FROM dbo.EmployeeOne
    UNION ALL
    SELECT LastName, FirstName ,JobTitle
    FROM dbo.EmployeeTwo
    UNION ALL
    SELECT LastName, FirstName,JobTitle
    FROM dbo.EmployeeThree
     */
    public Select exampleS4 = $(
            SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeOne"))
            ).UNION(SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeTwo"))
            )).UNION_ALL(SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeThree"))
            ))
        ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT LastName, FirstName,JobTitle
    FROM dbo.EmployeeOne
    UNION
    SELECT LastName, FirstName, JobTitle
    FROM dbo.EmployeeTwo
    UNION
    SELECT LastName, FirstName, JobTitle
    FROM dbo.EmployeeThree
     */
    public Select exampleS5 = $(
            SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeOne"))
            ).UNION(SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeTwo"))
            )).UNION(SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeThree"))
            ))
    ).build();
    // @formatter:on


    // @formatter:off
    /**
     * SELECT LastName, FirstName,JobTitle
    FROM dbo.EmployeeOne
    UNION ALL
    (
    SELECT LastName, FirstName, JobTitle
    FROM dbo.EmployeeTwo
    UNION
    SELECT LastName, FirstName, JobTitle
    FROM dbo.EmployeeThree
    )
     */
    public Select exampleS6 = $(
            SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeOne"))
            ).UNION_ALL(SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeTwo"))
            )).UNION(SELECT(
                    $(c("LastName"))
                    .$$(c("FirstName"))
                    .$$(c("JobTitle"))
                    ,FROM(t("dbo","EmployeeThree"))
            ))
    ).build();
    // @formatter:on

}