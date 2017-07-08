package com.xy.xsql.tsql.core.statement.dml;

import com.xy.xsql.tsql.core.clause.TableValueConstructorBuilder;
import com.xy.xsql.tsql.core.statement.dml.SelectBuilder;
import com.xy.xsql.tsql.model.clause.From;
import com.xy.xsql.tsql.model.clause.TableValueConstructor;
import com.xy.xsql.tsql.model.element.ColumnName;
import com.xy.xsql.tsql.model.expression.BinaryExpression;
import com.xy.xsql.tsql.model.predicate.ComparisonSubQuery;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.hints.QueryHintBuilder.*;
import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.INDEX;
import static com.xy.xsql.tsql.core.element.ColumnNameFactory.c;
import static com.xy.xsql.tsql.core.element.TableNameFactory.t;
import static com.xy.xsql.tsql.core.expression.BinaryExpressions.*;
import static com.xy.xsql.tsql.core.expression.Expressions.*;
import static com.xy.xsql.tsql.core.predicate.Predicates.*;
import static com.xy.xsql.tsql.core.statement.dml.SelectBuilder.SELECT;

/**
 * Created by xiaoyao9184 on 2016/12/28.
 */
public class SelectBuilderTest {

    /**
     * SELECT * FROM table
     */
    @Test
    public void testBaseBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .withFrom()
                    .withItem()._Base()
                        .withTableName(t("table"))
                        .and()
                    .and()
                .build();

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
        Assert.assertEquals(select.getSelectList().size(),1);
    }

    /**
     * SELECT ALL * FROM table
     */
    @Test
    public void testAllBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withAll()
                .build();

        Assert.assertTrue(select.isUseAll());
    }

    /**
     * SELECT DISTINCT * FROM table
     */
    @Test
    public void testDistinctBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withDistinct()
                .build();

        Assert.assertTrue(select.isUseDistinct());
    }

    /**
     * SELECT TOP 50 * FROM table
     * SELECT TOP 50 PERCENT * FROM table
     */
    @Test
    public void testTopBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withTop()
                    .withExpression(e_number(50))
                    .and()
                .build();

        Assert.assertEquals(select.getTop().getExpression().toString(),"50");

        select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withTop()
                    .withExpression(e_number(50))
                    .withPercent()
                    .and()
                .build();

        Assert.assertTrue(select.getTop().isUsePercent());
    }

    /**
     * SELECT * FROM table,table2
     */
    @Test
    public void test2TableBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .withFrom()
                    .withItem()._Base()
                        .withTableName(t("table"))
                        .and()
                    .withItem()._Base()
                        .withTableName(t("table2"))
                        .and()
                    .and()
                .build();

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),2);
    }

    /**
     * SELECT * FROM table LEFT JOIN table2
     */
    @Test
    public void testTableJoinBuild(){
        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                .and()
                .withFrom()
                    .withItem()._Joined()
                        .withTableSource()._Base()
                            .withTableName(t("table"))
                            .and()
                        .withJoinType(From.JoinTypeKeywords.LEFT_JOIN)
                        .withTableSource2()._Base()
                            .withTableName(t("table2"))
                            .and()
                        .and()
                    .and()
                .build();

        Assert.assertEquals(select.getFrom().getTableSourceList().size(),1);
    }

    /**
     * SELECT * FROM (VALUES (1, 2), (3, 4), (5, 6), (7, 8), (9, 10) ) AS MyTable(a, b)
     */
    @Test
    public void testDerivedTable(){
        // @formatter:off
        TableValueConstructor values = new TableValueConstructorBuilder<Void>()
                .$(e_number(1),e_number(2))
                .$(e_number(3),e_number(4))
                .$(e_number(5),e_number(6))
                .$(e_number(7),e_number(8))
                .$(e_number(9),e_number(10))
                .build();

        Select.QuerySpecification select = new SelectBuilder.QuerySpecificationBuilder<Void>()
                .withSelectItem()
                    .withAll()
                    .and()
                .withFrom()
                    .withItem()._Derived()
                        .withValues(values)
                        .and()
                        //TODO derived_table [ [ AS ] table_alias ] [ ( column_alias [ ,...n ] ) ]
                    .and()
                .build();
        // @formatter:on
    }


//    USE AdventureWorks2012;
//    GO
//    SELECT *
//    FROM Production.Product
//    ORDER BY Name ASC;
    // @formatter:off
    public Select exampleA1 = SELECT()
                .$Select()
                    .$()
                    .$From()
                        .$(t("Production","Product"))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("Name"))
                    .$Asc()
                    .and()
                .done();
    // @formatter:on


//-- Alternate way.
//    USE AdventureWorks2012;
//    GO
//    SELECT p.*
//    FROM Production.Product AS p
//    ORDER BY Name ASC;
//    GO
    // @formatter:off
    public Select exampleA2 = SELECT()
            .$Select()
                .$(t("p"))
                .$From()
                    .$()
                        .$(t("Production","Product"))
                        .$As("p")
                        .and()
                    .and()
                .and()
            .$OrderBy()
                .$(c("Name"))
                .$Asc()
                .and()
            .build();
    // @formatter:on


//    USE AdventureWorks2012;
//    GO
//    SELECT Name, ProductNumber, ListPrice AS Price
//    FROM Production.Product
//    ORDER BY Name ASC;
//    GO
    // @formatter:off
    public Select exampleA3 = SELECT()
            .$Select()
                .$(c("Name"))
                .$(c("ProductNumber"))
                .$(c("ListPrice "),"Price")
                .$From()
                    .$(t("Production","Product"))
                    .and()
                .and()
            .$OrderBy()
                .$(c("Name")).$Asc()
                .and()
            .build();
    // @formatter:on

//    USE AdventureWorks2012;
//    GO
//    SELECT Name, ProductNumber, ListPrice AS Price
//    FROM Production.Product
//    WHERE ProductLine = 'R'
//    AND DaysToManufacture < 4
//    ORDER BY Name ASC;
//    GO
    // @formatter:off
    public Select exampleA4 = SELECT()
            .$Select()
                .$(c("Name"))
                .$(c("ProductNumber"))
                .$(c("ListPrice "),"Price")
                .$From()
                    .$(t("Production","Product"))
                    .and()
                .$Where()
                    .$Predicate(p_equal(
                            c("ProductLine"),
                            e_string("R")
                    ))
                    .$_AndPredicate(p_less(
                            c("DaysToManufacture"),
                            e_number(4)
                    ))
                    .and()
                .and()
            .$OrderBy()
                .$(c("Name")).$Asc()
                .and()
            .build();
    // @formatter:on

    /**
     * SELECT *
     FROM Production.Product
     ORDER BY Name ASC;

     SELECT p.*
     FROM Production.Product AS p
     ORDER BY Name ASC;
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        Select select = new SelectBuilder()
                .withQuery()
                    .withQuerySpecification()
                        .withSelectItem()
                            .withAll()
                            .and()
                        .withFrom()
                            .withItem()._Base()
                                .withTableName(t("Production","Product"))
                                .and()
                            .and()
                        .and()
                    .and()
                .withOrderBy()
                    .$(c("p","*"))
                    .and()
                .build();

        Select select1 = new SelectBuilder()
                .withQuery()
                    .withQuerySpecification()
                        .withSelectItem()
                            .withTableAll(t("p"))
                            .and()
                        .withFrom()
                            .withItem()._Base()
                                .withTableName(t("Production","Product"))
                                .withTableAlias("p")
                                .and()
                            .and()
                        .and()
                    .and()
                .withOrderBy()
                    .$(c("Name"))
                    .$Asc()
                    .and()
                .build();
        // @formatter:on

        Select.QuerySpecification query = select.getQueryExpression().getQuerySpecification();
        Assert.assertEquals(query.getSelectList().size(),1);
//        Assert.assertTrue(select.getList().get(0).isUseAll());
//
//        Assert.assertEquals(select1.getList().size(),1);
//        Assert.assertEquals(select1.getList().get(0).getTableViewName().toString(),"p");
//        Assert.assertTrue(select1.getList().get(0).isUseTableAll());
    }


//    SELECT p.Name AS ProductName,
//    NonDiscountSales = (OrderQty * UnitPrice),
//    Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)
//    FROM Production.Product AS p
//    INNER JOIN Sales.SalesOrderDetail AS sod
//    ON p.ProductID = sod.ProductID
//    ORDER BY ProductName DESC;
    // @formatter:off
    public Select exampleB1 = SELECT()
                .$Select()
                    .$(c("p","Name"),"ProductName")
                    .$(e_assignment(
                            c("NonDiscountSales"),
                            e_multiplication(
                                    c("OrderQty"),
                                    c("UnitPrice")
                            )
                    ))
                    .$(e_assignment(
                            c("Discounts"),
                            e_multiplication(
                                    e_multiplication(
                                            c("OrderQty"),
                                            c("UnitPrice")),
                                    c("UnitPriceDiscount")
                            )
                    ))
                    .$From()
                        .$()
                            .$(t("Production","Product"))
                            .$As("p")
                            .$Inner_Join()
                            .$(t("Sales","SalesOrderDetail"),"sod")
                            .$On()
                                .$(p_equal(
                                        c("p","ProductID"),
                                        c("sod","ProductID")
                                ))
                                .and()
                            .and()
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("ProductName")).$Desc()
                    .and()
                .done();
    // @formatter:on

//    SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',
//    p.Name AS ProductName
//    FROM Production.Product AS p
//    INNER JOIN Sales.SalesOrderDetail AS sod
//    ON p.ProductID = sod.ProductID
//    ORDER BY ProductName ASC;
    public Select exampleB2 = SELECT()
            .$Select()
                .$(e_string("Total income is"))
                .$(e_multiplication(
                        e_multiplication(
                                c("OrderQty"),
                                c("UnitPrice")
                        ),
                        e_negative(
                                e_number(1),
                                c("UnitPriceDiscount")
                        )
                ))
                .$(e_string(" for "))
                .$(c("p","Name"),"ProductName")
                .$From()
                    .$()
                        .$(t("Production","Product"),"p")
                        .$Inner_Join()
                        .$(t("Sales","SalesOrderDetail"),"sod")
                        .$On()
                            .$(p_equal(
                                    c("p","ProductID"),
                                    c("sod","ProductID")
                            ))
                            .and()
                        .and()
                    .and()
                .and()
            .$OrderBy()
                .$(c("ProductName"))
                .$Asc()
                .and()
            .build();
    // @formatter:on

//    SELECT DISTINCT JobTitle
//    FROM HumanResources.Employee
//    ORDER BY JobTitle;
    public Select exampleC1 = SELECT()
            .$Select()
                .$Distinct()
                .$(c("JobTitle"))
                .$From()
                    .$(t("HumanResources","Employee"))
                    .and()
                .and()
            .$OrderBy()
                .$(c("JobTitle"))
                .and()
            .build();
    // @formatter:on

//    SELECT *
//    INTO #Bicycles
//    FROM AdventureWorks2012.Production.Product
//    WHERE ProductNumber LIKE 'BK%';
    public Select exampleD1 = SELECT()
            .$Select()
                .$()
                .$Into("#Bicycles")
                .$From()
                    .$(t("AdventureWorks2012","Production","Product"))
                    .and()
                .$Where()
                    .$Predicate(p_like(
                            c("ProductNumber"),
                            e_string("BK%")
                    ))
                    .and()
                .and()
            .build();
    // @formatter:on

//    SELECT * INTO dbo.NewProducts
//    FROM Production.Product
//    WHERE ListPrice > $25
//    AND ListPrice < $100;
    public Select exampleD2 = SELECT()
            .$Select()
                .$()
                .$Into("dbo.NewProducts")
            .$From()
                .$(t("Production","Product"))
                .and()
            .$Where()
                .$Predicate(p_greater(
                        c("ListPrice"),
                        e_money(25)
                ))
                .$_AndPredicate(p_less(
                        c("ListPrice"),
                        e_money(100)
                ))
                .and()
            .and()
            .build();
    // @formatter:on

//    SELECT DISTINCT Name
//    FROM Production.Product AS p
//    WHERE EXISTS
//            (SELECT *
//                    FROM Production.ProductModel AS pm
//                    WHERE p.ProductModelID = pm.ProductModelID
//                    AND pm.Name LIKE 'Long-Sleeve Logo Jersey%');
    public Select exampleE1 = SELECT()
            .$Select()
                .$Distinct()
                .$(c("Name"))
                .$From()
                    .$(t("Production","Product"),"p")
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_exists(
                            SELECT()
                                .$Select()
                                    .$()
                                    .$From()
                                        .$(t("Production","ProductModel"),"pm")
                                            .and()
                                        .and()
                                    .$Where()
                                        .$Predicate(p_equal(
                                                c("p","ProductModelID"),
                                                c("pm","ProductModelID")
                                        ))
                                        .$_AndPredicate(p_like(c("pm","Name"),
                                                e_string("Long-Sleeve Logo Jersey%")))
                                        .and()
                                    .and()
                                .done()
                    ))
                    .and()
                .and()
            .build();
    // @formatter:on

//    SELECT DISTINCT Name
//    FROM Production.Product
//    WHERE ProductModelID IN
//            (SELECT ProductModelID
//     FROM Production.ProductModel
//             WHERE Name LIKE 'Long-Sleeve Logo Jersey%');
    public Select exampleE2 = SELECT()
            .$Select()
                .$Distinct()
                .$(c("Name"))
                .$From()
                    .$(t("Production","Product"))
                    .and()
                .$Where()
                    .$Predicate(p_in(c("ProductModelID"),
                            SELECT()
                            .$Select()
                                .$(c("ProductModelID"))
                                .$From()
                                    .$(t("Production","ProductModel"))
                                    .and()
                                .$Where()
                                    .$Predicate(p_like(c("Name"),
                                            e_string("Long-Sleeve Logo Jersey%")))
                                    .and()
                                .and()
                            .done()
                    ))
                    .and()
                .and()
            .build();
    // @formatter:on



//    SELECT DISTINCT p.LastName, p.FirstName
//    FROM Person.Person AS p
//    JOIN HumanResources.Employee AS e
//    ON e.BusinessEntityID = p.BusinessEntityID WHERE 5000.00 IN
//            (SELECT Bonus
//     FROM Sales.SalesPerson AS sp
//             WHERE e.BusinessEntityID = sp.BusinessEntityID);
    public Select exampleE3 = SELECT()
            .$Select()
                .$Distinct()
                .$(c("p","LastName"))
                .$(c("p","FirstName"))
                .$From()
                    .$()
                        .$(t("Person","Person"),"p")
                        .$Join()
                        .$(t("HumanResources","Employee"),"e")
                        .$On()
                            .$Predicate(p_equal(
                                    c("e","BusinessEntityID"),
                                    c("p","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_in(e_number(5000.00),
                            SELECT()
                            .$Select()
                                .$(c("Bonus"))
                                .$From()
                                    .$(t("Sales","SalesPerson"),"sp")
                                        .and()
                                    .and()
                                .$Where()
                                    .$Predicate(p_equal(
                                            c("e","BusinessEntityID"),
                                            c("sp","BusinessEntityID")))
                                    .and()
                                .and()
                            .done()
                    ))
                    .and()
                .and()
            .build();
    // @formatter:on



//    SELECT p1.ProductModelID
//    FROM Production.Product AS p1
//    GROUP BY p1.ProductModelID
//    HAVING MAX(p1.ListPrice) >= ALL
//            (SELECT AVG(p2.ListPrice)
//    FROM Production.Product AS p2
//    WHERE p1.ProductModelID = p2.ProductModelID);
    public Select exampleE4 = SELECT()
            .$Select()
                .$(c("p1","ProductModelID"))
                .$From()
                    .$(t("Production","Product"),"p1")
                        .and()
                    .and()
                .$GroupBy()
                    .$(c("p1","ProductModelID"))
                    .and()
                .$Having()
                    .$Predicate(p_greater_equal(
                            e("MAX(p1.ListPrice)"),
                            ComparisonSubQuery.ALL_SOME_ANY.ALL,
                            SELECT().$Select()
                                .$(e("AVG(p2.ListPrice)"))
                                .$From()
                                    .$(t("Production","Product"),"p2")
                                        .and()
                                    .and()
                                .$Where()
                                    .$Predicate(p_equal(
                                            c("p1","ProductModelID"),
                                            c("p2","ProductModelID")
                                    ))
                                    .and()
                                .and()
                            .done()
                    ))
                    .and()
                .and()
            .build();
    // @formatter:on


//    SELECT DISTINCT pp.LastName, pp.FirstName
//    FROM Person.Person pp JOIN HumanResources.Employee e
//    ON e.BusinessEntityID = pp.BusinessEntityID WHERE pp.BusinessEntityID IN
//    (SELECT SalesPersonID
//    FROM Sales.SalesOrderHeader
//    WHERE SalesOrderID IN
//    (SELECT SalesOrderID
//    FROM Sales.SalesOrderDetail
//    WHERE ProductID IN
//    (SELECT ProductID
//    FROM Production.Product p
//    WHERE ProductNumber = 'BK-M68B-42')));
    public Select exampleE5 = SELECT()
            .$Select()
                .$Distinct()
                .$(c("pp","LastName"))
                .$(c("pp","FirstName"))
                .$From()
                    .$()
                        .$(t("Person","Person"),"pp")
                        .$Join()
                        .$(t("HumanResources","Employee"),"e")
                        .$On()
                            .$Predicate(p_equal(
                                    c("e","BusinessEntityID"),
                                    c("pp","BusinessEntityID")
                            ))
                            .and()
                        .and()
                    .and()
                .$Where()
                    .$Predicate(p_in(
                            c("pp","BusinessEntityID"),
                            SELECT()
                                .$Select()
                                    .$(c("SalesPersonID"))
                                    .$From()
                                        .$(t("Sales","SalesOrderHeader"))
                                        .and()
                                    .$Where()
                                        .$Predicate(p_in(
                                                c("SalesOrderID"),
                                                SELECT().$Select()
                                                    .$(c("SalesOrderID"))
                                                    .$From()
                                                        .$(t("Sales","SalesOrderDetail"))
                                                        .and()
                                                    .$Where()
                                                        .$Predicate(p_in(
                                                                c("ProductID"),
                                                                SELECT().$Select()
                                                                    .$(c("ProductID"))
                                                                    .$From()
                                                                        .$(t("Production","Product"),"p")
                                                                            .and()
                                                                        .and()
                                                                    .$Where()
                                                                        .$Predicate(p_equal(
                                                                                c("ProductNumber"),
                                                                                e_string("BK-M68B-42")
                                                                        ))
                                                                        .and()
                                                                    .and()
                                                                .done()
                                                        ))
                                                        .and()
                                                    .and()
                                                .done()
                                        ))
                                        .and()
                                    .and()
                                .done()
                    ))
                    .and()
                .and()
            .done();
    // @formatter:on



//    SELECT SalesOrderID, SUM(LineTotal) AS SubTotal
//    FROM Sales.SalesOrderDetail
//    GROUP BY SalesOrderID
//    ORDER BY SalesOrderID;
    // @formatter:off
    public Select exampleF = SELECT()
                .$Select()
                    .$(c("SalesOrderID"))
                    .$(e("SUM(LineTotal)"),"SubTotal")
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("SalesOrderID"))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("SalesOrderID"))
                    .and()
                .done();
    // @formatter:on



//    SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],
//    SUM(LineTotal) AS SubTotal
//    FROM Sales.SalesOrderDetail
//    GROUP BY ProductID, SpecialOfferID
//    ORDER BY ProductID;
    // @formatter:off
    public Select exampleG = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$(c("SpecialOfferID"))
                    .$(e("AVG(UnitPrice)"),"[Average Price]")
                    .$(e("SUM(LineTotal)"),"SubTotal")
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .$(c("SpecialOfferID"))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("ProductID"))
                    .and()
                .done();
    // @formatter:on



//    SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]
//    FROM Production.Product
//    WHERE ListPrice > $1000
//    GROUP BY ProductModelID
//    ORDER BY ProductModelID;
    // @formatter:off
    public Select exampleH = SELECT()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(e("AVG(ListPrice)"),"[Average List Price]")
                    .$From()
                        .$(t("Production","Product"))
                        .and()
                    .$Where()
                        .$Predicate(p_greater(
                                c("ListPrice"),
                                e_money(1000)
                        ))
                        .and()
                    .$GroupBy()
                        .$(c("ProductModelID"))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("ProductModelID"))
                    .and()
                .done();
    // @formatter:on



//    SELECT AVG(OrderQty) AS [Average Quantity],
//    NonDiscountSales = (OrderQty * UnitPrice)
//    FROM Sales.SalesOrderDetail
//    GROUP BY (OrderQty * UnitPrice)
//    ORDER BY (OrderQty * UnitPrice) DESC;
    // @formatter:off
    public Select exampleI = SELECT()
                .$Select()
                    .$(e("AVG(OrderQty)"),"[Average Quantity]")
                    .$(e_assignment(
                            c("NonDiscountSales"),
                            e_multiplication(
                                    c("OrderQty"),c("UnitPrice")
                            )
                    ))
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(e_multiplication(
                                c("OrderQty"),
                                c("UnitPrice")
                        ))
                        .and()
                    .and()
                .$OrderBy()
                    .$(e_multiplication(
                            c("OrderQty"),
                            c("UnitPrice")
                    )).$Desc()
                    .and()
                .done();
    // @formatter:on



//    SELECT ProductID, AVG(UnitPrice) AS [Average Price]
//    FROM Sales.SalesOrderDetail
//    WHERE OrderQty > 10
//    GROUP BY ProductID
//    ORDER BY AVG(UnitPrice);
    // @formatter:off
    public Select exampleJ = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$(e("AVG(UnitPrice)"),"[Average Price]")
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$Where()
                        .$Predicate(p_greater(
                                c("OrderQty"),e_number(10)
                        ))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .and()
                    .and()
                .$OrderBy()
                    .$(e("AVG(UnitPrice)"))
                    .and()
                .done();
    // @formatter:on



//    SELECT ProductID
//    FROM Sales.SalesOrderDetail
//    GROUP BY ProductID
//    HAVING AVG(OrderQty) > 5
//    ORDER BY ProductID;
    // @formatter:off
    public Select exampleK1 = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .and()
                    .$Having()
                        .$Predicate(p_greater(
                                e("AVG(OrderQty)"),e_number(5)
                        ))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("ProductID"))
                    .and()
                .done();
    // @formatter:on



//    SELECT SalesOrderID, CarrierTrackingNumber
//    FROM Sales.SalesOrderDetail
//    GROUP BY SalesOrderID, CarrierTrackingNumber
//    HAVING CarrierTrackingNumber LIKE '4BD%'
//    ORDER BY SalesOrderID ;
    // @formatter:off
    public Select exampleK2 = SELECT()
                .$Select()
                    .$(c("SalesOrderID"))
                    .$(c("CarrierTrackingNumber"))
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("SalesOrderID"))
                        .$(c("CarrierTrackingNumber"))
                        .and()
                    .$Having()
                        .$Predicate(p_like(
                                e("CarrierTrackingNumber"),e_string("4BD%")
                        ))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("SalesOrderID"))
                    .and()
                .done();
    // @formatter:on



//    SELECT ProductID
//    FROM Sales.SalesOrderDetail
//    WHERE UnitPrice < 25.00
//    GROUP BY ProductID
//    HAVING AVG(OrderQty) > 5
//    ORDER BY ProductID;
    // @formatter:off
    public Select exampleL = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$Where()
                        .$Predicate(p_less(
                                c("UnitPrice"),e_number(25.00)
                        ))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .and()
                    .$Having()
                        .$Predicate(p_greater(
                                e("AVG(OrderQty)"),e_number(5)
                        ))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("ProductID"))
                    .and()
                .done();
    // @formatter:on



//    USE AdventureWorks2012;
//    GO
//    SELECT ProductID, AVG(OrderQty) AS AverageQuantity, SUM(LineTotal) AS Total
//    FROM Sales.SalesOrderDetail
//    GROUP BY ProductID
//    HAVING SUM(LineTotal) > $1000000.00
//    AND AVG(OrderQty) < 3;
//    GO
    // @formatter:off
    public Select exampleM1 = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$(e("AVG(OrderQty)"),"AverageQuantity")
                    .$(e("SUM(LineTotal)"),"Total")
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .and()
                    .$Having()
                        .$Predicate(p_greater(
                                e("SUM(LineTotal)"),e_money(1000000.00)
                        ))
                        .$_AndPredicate(p_less(
                                e("AVG(OrderQty)"),e_number(3)
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on



//    USE AdventureWorks2012;
//    GO
//    SELECT ProductID, Total = SUM(LineTotal)
//    FROM Sales.SalesOrderDetail
//    GROUP BY ProductID
//    HAVING SUM(LineTotal) > $2000000.00;
//    GO
    // @formatter:off
    public Select exampleM2 = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$(e_assignment(c("Total"),e("SUM(LineTotal)")))
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .and()
                    .$Having()
                        .$Predicate(p_greater(
                                e("SUM(LineTotal)"),e_money(2000000.00)
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on



//    USE AdventureWorks2012;
//    GO
//    SELECT ProductID, SUM(LineTotal) AS Total
//    FROM Sales.SalesOrderDetail
//    GROUP BY ProductID
//    HAVING COUNT(*) > 1500;
//    GO
    // @formatter:off
    public Select exampleM3 = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$(e("SUM(LineTotal)"),"Total")
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .and()
                    .$Having()
                        .$Predicate(p_greater(
                                e("COUNT(*)"),e_number(1500)
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on



//    USE AdventureWorks2012;
//    GO
//    SELECT pp.FirstName, pp.LastName, e.NationalIDNumber
//    FROM HumanResources.Employee AS e WITH (INDEX(AK_Employee_NationalIDNumber))
//    JOIN Person.Person AS pp on e.BusinessEntityID = pp.BusinessEntityID
//    WHERE LastName = 'Johnson';
//    GO
    // @formatter:off
    public Select exampleN1 = SELECT()
                .$Select()
                    .$(c("pp","FirstName"))
                    .$(c("pp","LastName"))
                    .$(c("e","NationalIDNumber"))
                    .$From()
                        .$()
                            .$(t("HumanResources","Employee"))
                                .$With(INDEX("AK_Employee_NationalIDNumber"))
                                .$As("e")
                            .$Join()
                            .$(t("Person","Person"),"pp")
                            .$On()
                                .$Predicate(p_equal(
                                        c("e","BusinessEntityID"),
                                        c("pp","BusinessEntityID")
                                ))
                                .and()
                            .and()
                        .and()
                    .$Where()
                        .$Predicate(p_equal(
                                c("LastName"),e_string("Johnson")
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on


//-- Force a table scan by using INDEX = 0.
//    USE AdventureWorks2012;
//    GO
//    SELECT pp.LastName, pp.FirstName, e.JobTitle
//    FROM HumanResources.Employee AS e WITH (INDEX = 0) JOIN Person.Person AS pp
//    ON e.BusinessEntityID = pp.BusinessEntityID
//    WHERE LastName = 'Johnson';
//    GO
    // @formatter:off
    public Select exampleN2 = SELECT()
                .$Select()
                    .$(c("pp","LastName"))
                    .$(c("pp","FirstName"))
                    .$(c("e","JobTitle"))
                    .$From()
                        .$()
                            .$(t("HumanResources","Employee"))
                                .$With(INDEX().$EQUAL("0").build())
                                .$As("e")
                            .$Join()
                            .$(t("Person","Person"),"pp")
                            .$On()
                                .$Predicate(p_equal(
                                        c("e","BusinessEntityID"),
                                        c("pp","BusinessEntityID")
                                ))
                                .and()
                            .and()
                        .and()
                    .$Where()
                        .$Predicate(p_equal(
                                c("LastName"),e_string("Johnson")
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on


//    USE AdventureWorks2012;
//    GO
//    SELECT ProductID, OrderQty, SUM(LineTotal) AS Total
//    FROM Sales.SalesOrderDetail
//    WHERE UnitPrice < $5.00
//    GROUP BY ProductID, OrderQty
//    ORDER BY ProductID, OrderQty
//    OPTION (HASH GROUP, FAST 10);
//    GO
    // @formatter:off
    public Select exampleM = SELECT()
                .$Select()
                    .$(c("ProductID"))
                    .$(c("OrderQty"))
                    .$(e("SUM(LineTotal)"),"Total")
                    .$From()
                        .$(t("Sales","SalesOrderDetail"))
                        .and()
                    .$Where()
                        .$Predicate(p_less(
                                c("UnitPrice"),e_money(5.00)
                        ))
                        .and()
                    .$GroupBy()
                        .$(c("ProductID"))
                        .$(c("OrderQty"))
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("ProductID"))
                    .$(c("OrderQty"))
                    .and()
                .$Option()
                    .$(HASH_GROUP(),FAST(10))
                    .and()
                .done();
    // @formatter:on


//    USE AdventureWorks2012;
//    GO
//    SELECT BusinessEntityID, JobTitle, HireDate, VacationHours, SickLeaveHours
//    FROM HumanResources.Employee AS e1
//            UNION
//    SELECT BusinessEntityID, JobTitle, HireDate, VacationHours, SickLeaveHours
//    FROM HumanResources.Employee AS e2
//    OPTION (MERGE UNION);
//    GO
    // @formatter:off
    public Select exampleO = SELECT()
                .$()
                    .$Select()
                        .$(c("BusinessEntityID"))
                        .$(c("JobTitle"))
                        .$(c("HireDate"))
                        .$(c("VacationHours"))
                        .$(c("SickLeaveHours"))
                        .$From()
                            .$(t("HumanResources","Employee"),"e1")
                            .and().and()
                        .and()
                    .$Union_Select()
                        .$(c("BusinessEntityID"))
                        .$(c("JobTitle"))
                        .$(c("HireDate"))
                        .$(c("VacationHours"))
                        .$(c("SickLeaveHours"))
                        .$From()
                            .$(t("HumanResources","Employee"),"e2")
                                .and()
                            .and()
                        .and()
                    .and()
                .$Option()
                    .$(MERGE_UNION())
                    .and()
                .done();
    // @formatter:on


//-- Create Gloves table.
//            SELECT ProductModelID, Name
//    INTO dbo.Gloves
//    FROM Production.ProductModel
//    WHERE ProductModelID IN (3, 4);
//    GO
    // @formatter:off
    public Select exampleP1 = SELECT()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$Into("dbo.Gloves")
                    .$From()
                        .$(t("Production","ProductModel"))
                        .and()
                    .$Where()
                        .$Predicate(p_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on


//-- Here is the simple union.
//            USE AdventureWorks2012;
//    GO
//    SELECT ProductModelID, Name
//    FROM Production.ProductModel
//    WHERE ProductModelID NOT IN (3, 4)
//    UNION
//    SELECT ProductModelID, Name
//    FROM dbo.Gloves
//    ORDER BY Name;
//    GO
    // @formatter:off
    public Select exampleP2 = SELECT()
                .$()
                    .$Select()
                        .$(c("ProductModelID"))
                        .$(c("Name"))
                        .$From()
                            .$(t("Production","ProductModel"))
                            .and()
                        .$Where()
                            .$Predicate(p_not_in(
                                    c("ProductModelID"),
                                    e_number(3),
                                    e_number(4)
                            ))
                            .and()
                        .and()
                    .$Union_Select()
                        .$(c("ProductModelID"))
                        .$(c("Name"))
                        .$From()
                            .$(t("dbo","Gloves"))
                            .and()
                        .and()
                    .and()
                .$OrderBy()
                    .$(c("Name"))
                    .and()
                .done();
    // @formatter:on



//-- Create Gloves table.
//            SELECT ProductModelID, Name
//    INTO dbo.Gloves
//    FROM Production.ProductModel
//    WHERE ProductModelID IN (3, 4);
//    GO
    // @formatter:off
    public Select exampleQ1 = SELECT()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$Into("dbo.Gloves")
                    .$From()
                        .$(t("Production","ProductModel"))
                        .and()
                    .$Where()
                        .$Predicate(p_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
                        .and()
                    .and()
                .done();
    // @formatter:on


//    USE AdventureWorks2012;
//    GO
//    SELECT ProductModelID, Name
//    INTO dbo.ProductResults
//    FROM Production.ProductModel
//    WHERE ProductModelID NOT IN (3, 4)
//    UNION
//    SELECT ProductModelID, Name
//    FROM dbo.Gloves;
//    GO
    // @formatter:off
    public Select exampleQ2 = SELECT()
                .$()
                    .$Select()
                        .$(c("ProductModelID"))
                        .$(c("Name"))
                        .$Into("dbo.ProductResults")
                        .$From()
                            .$(t("Production","ProductModel"))
                            .and()
                        .$Where()
                            .$Predicate(p_not_in(
                                    c("ProductModelID"),
                                    e_number(3),
                                    e_number(4)
                            ))
                            .and()
                        .and()
                    .$Union_Select()
                        .$(c("ProductModelID"))
                        .$(c("Name"))
                        .$From()
                            .$(t("dbo","Gloves"))
                            .and()
                        .and()
                    .and()
                .done();
    // @formatter:on


//    SELECT ProductModelID, Name
//    FROM dbo.ProductResults;
    // @formatter:off
    public Select exampleQ3 = SELECT()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$From()
                        .$(t("dbo","ProductResults"))
                        .and()
                    .and()
                .done();
    // @formatter:on


//-- Create Gloves table.
//            SELECT ProductModelID, Name
//    INTO dbo.Gloves
//    FROM Production.ProductModel
//    WHERE ProductModelID IN (3, 4);
//    GO
    // @formatter:off
    public Select exampleR1 = SELECT()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$Into("dbo.Gloves")
                    .$From()
                        .$(t("Production","ProductModel"))
                        .and()
                    .$Where()
                        .$Predicate(p_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
                        .and()
                    .and()
                .done();
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
    public Select exampleR2_INCORRECT = SELECT()
            .$()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$From()
                        .$(t("Production","ProductModel"))
                        .and()
                    .$Where()
                        .$Predicate(p_not_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
                        .and()
        //CANT use ORDER BY in QuerySpecification
//                    .$OrderBy()
//                        .$(c("Name"))
//                        .and()
                    .and()
                .$Union_Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$From()
                        .$(t("dbo","Gloves"))
                        .and()
                    .and()
                .and()
            .done();
    // @formatter:on


//    /* CORRECT */
//    USE AdventureWorks2012;
//    GO
//    SELECT ProductModelID, Name
//    FROM Production.ProductModel
//    WHERE ProductModelID NOT IN (3, 4)
//    UNION
//    SELECT ProductModelID, Name
//    FROM dbo.Gloves
//    ORDER BY Name;
//    GO
    // @formatter:off
    public Select exampleR3 = SELECT()
            .$()
                .$Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$From()
                        .$(t("Production","ProductModel"))
                        .and()
                    .$Where()
                        .$Predicate(p_not_in(
                                c("ProductModelID"),
                                e_number(3),
                                e_number(4)
                        ))
                        .and()
                    .and()
                .$Union_Select()
                    .$(c("ProductModelID"))
                    .$(c("Name"))
                    .$From()
                        .$(t("dbo","Gloves"))
                        .and()
                    .and()
                .and()
            .$OrderBy()
                .$(c("Name"))
                .and()
            .done();
    // @formatter:on


//    SELECT pp.LastName, pp.FirstName, e.JobTitle
//    INTO dbo.EmployeeOne
//    FROM Person.Person AS pp JOIN HumanResources.Employee AS e
//    ON e.BusinessEntityID = pp.BusinessEntityID
//    WHERE LastName = 'Johnson';
//    GO
    // @formatter:off
    public Select exampleS1 = SELECT()
                .$Select()
                    .$(c("pp","LastName"))
                    .$(c("pp","FirstName"))
                    .$(c("e","JobTitle"))
                    .$Into("dbo.EmployeeOne")
                    .$From()
                        .$()
                            .$(t("Person","Person"),"pp")
                            .$Join()
                            .$(t("HumanResources","Employee"),"e")
                            .$On()
                                .$Predicate(p_equal(
                                        c("e","BusinessEntityID"),
                                        c("pp","BusinessEntityID")
                                ))
                                .and()
                            .and()
                        .and()
                    .$Where()
                        .$Predicate(p_equal(
                                c("LastName"),
                                e_string("Johnson")
                        ))
                    .and()
                .and()
                .done();
    // @formatter:on


//-- Union ALL
//    SELECT LastName, FirstName, JobTitle
//    FROM dbo.EmployeeOne
//    UNION ALL
//    SELECT LastName, FirstName ,JobTitle
//    FROM dbo.EmployeeTwo
//    UNION ALL
//    SELECT LastName, FirstName,JobTitle
//    FROM dbo.EmployeeThree;
//    GO
    // @formatter:off
    public Select exampleS4 = SELECT()
            .$()
                .$Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeOne"))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeTwo"))
                        .and()
                    .and()
                .$Union_All_Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeThree"))
                        .and()
                    .and()
                .and()
            .done();
    // @formatter:on


//    SELECT LastName, FirstName,JobTitle
//    FROM dbo.EmployeeOne
//            UNION
//    SELECT LastName, FirstName, JobTitle
//    FROM dbo.EmployeeTwo
//            UNION
//    SELECT LastName, FirstName, JobTitle
//    FROM dbo.EmployeeThree;
//    GO
    // @formatter:off
    public Select exampleS5 = SELECT()
            .$()
                .$Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeOne"))
                        .and()
                    .and()
                .$Union_Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeTwo"))
                        .and()
                    .and()
                .$Union_Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeThree"))
                        .and()
                    .and()
                .and()
            .done();
    // @formatter:on


//    SELECT LastName, FirstName,JobTitle
//    FROM dbo.EmployeeOne
//    UNION ALL
//            (
//                    SELECT LastName, FirstName, JobTitle
//                    FROM dbo.EmployeeTwo
//                            UNION
//                            SELECT LastName, FirstName, JobTitle
//                            FROM dbo.EmployeeThree
//            );
//    GO
    // @formatter:off
    public Select exampleS6 = SELECT()
            .$()
                .$Select()
                    .$(c("LastName"))
                    .$(c("FirstName"))
                    .$(c("JobTitle"))
                    .$From()
                        .$(t("dbo","EmployeeOne"))
                        .and()
                    .and()
                .$Union_All_()
                    .$Select()
                        .$(c("LastName"))
                        .$(c("FirstName"))
                        .$(c("JobTitle"))
                        .$From()
                            .$(t("dbo","EmployeeTwo"))
                            .and()
                        .and()
                    .$Union_Select()
                        .$(c("LastName"))
                        .$(c("FirstName"))
                        .$(c("JobTitle"))
                        .$From()
                            .$(t("dbo","EmployeeThree"))
                            .and()
                        .and()
                    .and()
                .and()
            .done();
    // @formatter:on

}
