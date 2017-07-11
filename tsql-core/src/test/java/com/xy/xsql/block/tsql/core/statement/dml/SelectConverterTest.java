package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.dml.SelectBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Select;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class SelectConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = SelectConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SELECT statement> ::=\n" +
                        "[ [ WITH { [ XMLNAMESPACES ,] [ <common_table_expression> [,...n] ] } ] ]\n" +
                        "<query_expression>\n" +
                        "[ ORDER BY { order_by_expression | column_position [ ASC | DESC ] } [ ,...n ] ]\n" +
                        "[ <FOR Clause> ]\n" +
                        "[ OPTION ( <query_hint> [ ,...n ] ) ]");
    }

    @Test
    public void testQueryExpression() throws Exception {
        BlockMeta b = SelectConverter.QueryExpressionConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_expression> ::=\n" +
                        "{ <query_specification> | ( <query_expression> ) }\n" +
                        "[ { UNION [ ALL ] | EXCEPT | INTERSECT }\n" +
                        "<query_specification> | ( <query_expression> ) ] [...n]");
    }

    @Test
    public void testQuerySpecification() throws Exception {
        BlockMeta b = SelectConverter.QuerySpecificationConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_specification> ::=\n" +
                        "SELECT\n" +
                        "[ ALL | DISTINCT ]\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "<select_list>\n" +
                        "[ INTO new_table ]\n" +
                        "[ FROM { <table_source> } [ ,...n ] ]\n" +
                        "[ WHERE <search_condition> ]\n" +
                        "[ <GROUP BY> ]\n" +
                        "[ HAVING < search_condition > ]");
    }

    @Test
    public void testUnionItem() throws Exception {
        BlockMeta b = SelectConverter.UnionItemConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ UNION [ ALL ] | EXCEPT | INTERSECT }\n" +
                        "<query_specification> | ( <query_expression> )");
    }


    private Map<Select,String> model2StringMap;

    @Before
    public void init(){
        SelectBuilderTest builderTest = new SelectBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA1,
                "SELECT *\n" +
                        "    FROM Production.Product\n" +
                        "    ORDER BY Name ASC");

        model2StringMap.put(
                builderTest.exampleA2,
                "SELECT p.*\n" +
                        "    FROM Production.Product AS p\n" +
                        "    ORDER BY Name ASC");

        model2StringMap.put(
                builderTest.exampleA3,
                "SELECT Name, ProductNumber, ListPrice AS Price\n" +
                        "    FROM Production.Product \n" +
                        "    ORDER BY Name ASC");

        model2StringMap.put(
                builderTest.exampleA4,
                "SELECT Name, ProductNumber, ListPrice AS Price\n" +
                        "    FROM Production.Product \n" +
                        "    WHERE ProductLine = 'R' \n" +
                        "    AND DaysToManufacture < 4\n" +
                        "    ORDER BY Name ASC");

        model2StringMap.put(
                builderTest.exampleB1,
                "SELECT p.Name AS ProductName, \n" +
                        "    NonDiscountSales = (OrderQty * UnitPrice),\n" +
                        "    Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)\n" +
                        "    FROM Production.Product AS p \n" +
                        "    INNER JOIN Sales.SalesOrderDetail AS sod\n" +
                        "    ON p.ProductID = sod.ProductID \n" +
                        "    ORDER BY ProductName DESC");

        /*
        number 1.0 to 1.00
         */
        model2StringMap.put(
                builderTest.exampleB2,
                "SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',\n" +
                        "    p.Name AS ProductName \n" +
                        "    FROM Production.Product AS p \n" +
                        "    INNER JOIN Sales.SalesOrderDetail AS sod\n" +
                        "    ON p.ProductID = sod.ProductID \n" +
                        "    ORDER BY ProductName ASC");

        model2StringMap.put(
                builderTest.exampleC,
                "SELECT DISTINCT JobTitle\n" +
                        "     FROM HumanResources.Employee\n" +
                        "     ORDER BY JobTitle");

        model2StringMap.put(
                builderTest.exampleD1,
                "SELECT * \n" +
                        "     INTO #Bicycles\n" +
                        "     FROM AdventureWorks2012.Production.Product\n" +
                        "     WHERE ProductNumber LIKE 'BK%'");

        model2StringMap.put(
                builderTest.exampleD2,
                "SELECT * INTO dbo.NewProducts\n" +
                        "     FROM Production.Product\n" +
                        "     WHERE ListPrice > $25 \n" +
                        "     AND ListPrice < $100");

        model2StringMap.put(
                builderTest.exampleE1,
                "SELECT DISTINCT Name\n" +
                        "     FROM Production.Product AS p\n" +
                        "     WHERE EXISTS\n" +
                        "         (SELECT *\n" +
                        "         FROM Production.ProductModel AS pm\n" +
                        "         WHERE p.ProductModelID = pm.ProductModelID\n" +
                        "            AND pm.Name LIKE 'Long-Sleeve Logo Jersey%')");

        model2StringMap.put(
                builderTest.exampleE2,
                "SELECT DISTINCT Name\n" +
                        "     FROM Production.Product\n" +
                        "     WHERE ProductModelID IN\n" +
                        "         (SELECT ProductModelID\n" +
                        "         FROM Production.ProductModel\n" +
                        "         WHERE Name LIKE 'Long-Sleeve Logo Jersey%')");

        model2StringMap.put(
                builderTest.exampleE3,
                "SELECT DISTINCT p.LastName, p.FirstName \n" +
                        "     FROM Person.Person AS p \n" +
                        "     JOIN HumanResources.Employee AS e\n" +
                        "         ON e.BusinessEntityID = p.BusinessEntityID WHERE 5000.0 IN\n" +
                        "         (SELECT Bonus\n" +
                        "         FROM Sales.SalesPerson AS sp\n" +
                        "         WHERE e.BusinessEntityID = sp.BusinessEntityID)");
//                "SELECT DISTINCT p.LastName, p.FirstName \n" +
//                        "     FROM Person.Person AS p \n" +
//                        "     JOIN HumanResources.Employee AS e\n" +
//                        "         ON e.BusinessEntityID = p.BusinessEntityID WHERE 5000.00 IN\n" +
//                        "         (SELECT Bonus\n" +
//                        "         FROM Sales.SalesPerson AS sp\n" +
//                        "         WHERE e.BusinessEntityID = sp.BusinessEntityID)");

        model2StringMap.put(
                builderTest.exampleE4,
                "SELECT p1.ProductModelID\n" +
                        "     FROM Production.Product AS p1\n" +
                        "     GROUP BY p1.ProductModelID\n" +
                        "     HAVING MAX(p1.ListPrice) >= ALL\n" +
                        "         (SELECT AVG(p2.ListPrice)\n" +
                        "         FROM Production.Product AS p2\n" +
                        "         WHERE p1.ProductModelID = p2.ProductModelID)");

        model2StringMap.put(
                builderTest.exampleE5,
                "SELECT DISTINCT pp.LastName, pp.FirstName \n" +
                        "     FROM Person.Person pp JOIN HumanResources.Employee e\n" +
                        "     ON e.BusinessEntityID = pp.BusinessEntityID WHERE pp.BusinessEntityID IN \n" +
                        "     (SELECT SalesPersonID \n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesOrderID IN \n" +
                        "     (SELECT SalesOrderID \n" +
                        "     FROM Sales.SalesOrderDetail\n" +
                        "     WHERE ProductID IN \n" +
                        "     (SELECT ProductID \n" +
                        "     FROM Production.Product p \n" +
                        "     WHERE ProductNumber = 'BK-M68B-42')))");

        model2StringMap.put(
                builderTest.exampleF,
                "SELECT SalesOrderID, SUM(LineTotal) AS SubTotal\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY SalesOrderID\n" +
                        "    ORDER BY SalesOrderID");

        model2StringMap.put(
                builderTest.exampleG,
                "SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price], \n" +
                        "        SUM(LineTotal) AS SubTotal\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY ProductID, SpecialOfferID\n" +
                        "    ORDER BY ProductID");

        model2StringMap.put(
                builderTest.exampleH,
                "SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]\n" +
                        "    FROM Production.Product\n" +
                        "    WHERE ListPrice > $1000\n" +
                        "    GROUP BY ProductModelID\n" +
                        "    ORDER BY ProductModelID");

        model2StringMap.put(
                builderTest.exampleI,
                "SELECT AVG(OrderQty) AS [Average Quantity], \n" +
                        "    NonDiscountSales = (OrderQty * UnitPrice)\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY (OrderQty * UnitPrice)\n" +
                        "    ORDER BY (OrderQty * UnitPrice) DESC");

        model2StringMap.put(
                builderTest.exampleJ,
                "SELECT ProductID, AVG(UnitPrice) AS [Average Price]\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    WHERE OrderQty > 10\n" +
                        "    GROUP BY ProductID\n" +
                        "    ORDER BY AVG(UnitPrice)");

        model2StringMap.put(
                builderTest.exampleK1,
                "SELECT ProductID \n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY ProductID\n" +
                        "    HAVING AVG(OrderQty) > 5\n" +
                        "    ORDER BY ProductID");

        model2StringMap.put(
                builderTest.exampleK2,
                "SELECT SalesOrderID, CarrierTrackingNumber   \n" +
                        "    FROM Sales.SalesOrderDetail  \n" +
                        "    GROUP BY SalesOrderID, CarrierTrackingNumber  \n" +
                        "    HAVING CarrierTrackingNumber LIKE '4BD%'  \n" +
                        "    ORDER BY SalesOrderID");

        model2StringMap.put(
                builderTest.exampleL,
                "SELECT ProductID \n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    WHERE UnitPrice < 25.0\n" +
                        "    GROUP BY ProductID\n" +
                        "    HAVING AVG(OrderQty) > 5\n" +
                        "    ORDER BY ProductID");
//                "SELECT ProductID \n" +
//                        "    FROM Sales.SalesOrderDetail\n" +
//                        "    WHERE UnitPrice < 25.00\n" +
//                        "    GROUP BY ProductID\n" +
//                        "    HAVING AVG(OrderQty) > 5\n" +
//                        "    ORDER BY ProductID");

        model2StringMap.put(
                builderTest.exampleM1,
                "SELECT ProductID, AVG(OrderQty) AS AverageQuantity, SUM(LineTotal) AS Total\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY ProductID\n" +
                        "    HAVING SUM(LineTotal) > $1000000.0\n" +
                        "    AND AVG(OrderQty) < 3");
//                "SELECT ProductID, AVG(OrderQty) AS AverageQuantity, SUM(LineTotal) AS Total\n" +
//                        "    FROM Sales.SalesOrderDetail\n" +
//                        "    GROUP BY ProductID\n" +
//                        "    HAVING SUM(LineTotal) > $1000000.00\n" +
//                        "    AND AVG(OrderQty) < 3");

        model2StringMap.put(
                builderTest.exampleM2,
                "SELECT ProductID, Total = SUM(LineTotal)\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY ProductID\n" +
                        "    HAVING SUM(LineTotal) > $2000000.0");
//                "SELECT ProductID, Total = SUM(LineTotal)\n" +
//                        "    FROM Sales.SalesOrderDetail\n" +
//                        "    GROUP BY ProductID\n" +
//                        "    HAVING SUM(LineTotal) > $2000000.00");

        model2StringMap.put(
                builderTest.exampleM3,
                "SELECT ProductID, SUM(LineTotal) AS Total\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    GROUP BY ProductID\n" +
                        "    HAVING COUNT(*) > 1500");

        model2StringMap.put(
                builderTest.exampleN1,
                "SELECT pp.FirstName, pp.LastName, e.NationalIDNumber\n" +
                        "    FROM HumanResources.Employee AS e WITH (INDEX(AK_Employee_NationalIDNumber))\n" +
                        "    JOIN Person.Person AS pp ON e.BusinessEntityID = pp.BusinessEntityID\n" +
                        "    WHERE LastName = 'Johnson'");
//                "SELECT pp.FirstName, pp.LastName, e.NationalIDNumber\n" +
//                        "    FROM HumanResources.Employee AS e WITH (INDEX(AK_Employee_NationalIDNumber))\n" +
//                        "    JOIN Person.Person AS pp on e.BusinessEntityID = pp.BusinessEntityID\n" +
//                        "    WHERE LastName = 'Johnson'");

        /*
        Hints add ()
         */
        model2StringMap.put(
                builderTest.exampleN2,
                "SELECT pp.LastName, pp.FirstName, e.JobTitle\n" +
                        "    FROM HumanResources.Employee AS e WITH (INDEX = (0)) JOIN Person.Person AS pp\n" +
                        "    ON e.BusinessEntityID = pp.BusinessEntityID\n" +
                        "    WHERE LastName = 'Johnson'");
//                "SELECT pp.LastName, pp.FirstName, e.JobTitle\n" +
//                        "    FROM HumanResources.Employee AS e WITH (INDEX = 0) JOIN Person.Person AS pp\n" +
//                        "    ON e.BusinessEntityID = pp.BusinessEntityID\n" +
//                        "    WHERE LastName = 'Johnson'");

        model2StringMap.put(
                builderTest.exampleM,
                "SELECT ProductID, OrderQty, SUM(LineTotal) AS Total\n" +
                        "    FROM Sales.SalesOrderDetail\n" +
                        "    WHERE UnitPrice < $5.0\n" +
                        "    GROUP BY ProductID, OrderQty\n" +
                        "    ORDER BY ProductID, OrderQty\n" +
                        "    OPTION (HASH GROUP, FAST 10)");
//                "SELECT ProductID, OrderQty, SUM(LineTotal) AS Total\n" +
//                        "    FROM Sales.SalesOrderDetail\n" +
//                        "    WHERE UnitPrice < $5.00\n" +
//                        "    GROUP BY ProductID, OrderQty\n" +
//                        "    ORDER BY ProductID, OrderQty\n" +
//                        "    OPTION (HASH GROUP, FAST 10)");

        model2StringMap.put(
                builderTest.exampleO,
                "SELECT BusinessEntityID, JobTitle, HireDate, VacationHours, SickLeaveHours\n" +
                        "    FROM HumanResources.Employee AS e1\n" +
                        "    UNION\n" +
                        "    SELECT BusinessEntityID, JobTitle, HireDate, VacationHours, SickLeaveHours\n" +
                        "    FROM HumanResources.Employee AS e2\n" +
                        "    OPTION (MERGE UNION)");

        model2StringMap.put(
                builderTest.exampleP1,
                "SELECT ProductModelID, Name\n" +
                        "    INTO dbo.Gloves\n" +
                        "    FROM Production.ProductModel\n" +
                        "    WHERE ProductModelID IN (3, 4)");

        model2StringMap.put(
                builderTest.exampleP2,
                "SELECT ProductModelID, Name\n" +
                        "    FROM Production.ProductModel\n" +
                        "    WHERE ProductModelID NOT IN (3, 4)\n" +
                        "    UNION\n" +
                        "    SELECT ProductModelID, Name\n" +
                        "    FROM dbo.Gloves\n" +
                        "    ORDER BY Name");

        model2StringMap.put(
                builderTest.exampleQ1,
                "SELECT ProductModelID, Name\n" +
                        "    INTO dbo.Gloves\n" +
                        "    FROM Production.ProductModel\n" +
                        "    WHERE ProductModelID IN (3, 4)");

        model2StringMap.put(
                builderTest.exampleQ2,
                "SELECT ProductModelID, Name\n" +
                        "    INTO dbo.ProductResults\n" +
                        "    FROM Production.ProductModel\n" +
                        "    WHERE ProductModelID NOT IN (3, 4)\n" +
                        "    UNION\n" +
                        "    SELECT ProductModelID, Name\n" +
                        "    FROM dbo.Gloves");

        model2StringMap.put(
                builderTest.exampleQ3,
                "SELECT ProductModelID, Name \n" +
                        "    FROM dbo.ProductResults");

        model2StringMap.put(
                builderTest.exampleR1,
                "SELECT ProductModelID, Name\n" +
                        "    INTO dbo.Gloves\n" +
                        "    FROM Production.ProductModel\n" +
                        "    WHERE ProductModelID IN (3, 4)");

        model2StringMap.put(
                builderTest.exampleR3,
                "SELECT ProductModelID, Name\n" +
                        "    FROM Production.ProductModel\n" +
                        "    WHERE ProductModelID NOT IN (3, 4)\n" +
                        "    UNION\n" +
                        "    SELECT ProductModelID, Name\n" +
                        "    FROM dbo.Gloves\n" +
                        "    ORDER BY Name");

        model2StringMap.put(
                builderTest.exampleS1,
                "SELECT pp.LastName, pp.FirstName, e.JobTitle \n" +
                        "    INTO dbo.EmployeeOne\n" +
                        "    FROM Person.Person AS pp JOIN HumanResources.Employee AS e\n" +
                        "    ON e.BusinessEntityID = pp.BusinessEntityID\n" +
                        "    WHERE LastName = 'Johnson'");

        model2StringMap.put(
                builderTest.exampleS4,
                "SELECT LastName, FirstName, JobTitle\n" +
                        "    FROM dbo.EmployeeOne\n" +
                        "    UNION ALL\n" +
                        "    SELECT LastName, FirstName ,JobTitle\n" +
                        "    FROM dbo.EmployeeTwo\n" +
                        "    UNION ALL\n" +
                        "    SELECT LastName, FirstName,JobTitle \n" +
                        "    FROM dbo.EmployeeThree");

        model2StringMap.put(
                builderTest.exampleS5,
                "SELECT LastName, FirstName,JobTitle\n" +
                        "    FROM dbo.EmployeeOne\n" +
                        "    UNION \n" +
                        "    SELECT LastName, FirstName, JobTitle \n" +
                        "    FROM dbo.EmployeeTwo\n" +
                        "    UNION \n" +
                        "    SELECT LastName, FirstName, JobTitle \n" +
                        "    FROM dbo.EmployeeThree");

        model2StringMap.put(
                builderTest.exampleS6,
                "SELECT LastName, FirstName,JobTitle\n" +
                        "    FROM dbo.EmployeeOne\n" +
                        "    UNION ALL\n" +
                        "    (\n" +
                        "    SELECT LastName, FirstName, JobTitle\n" +
                        "    FROM dbo.EmployeeTwo\n" +
                        "    UNION\n" +
                        "    SELECT LastName, FirstName, JobTitle\n" +
                        "    FROM dbo.EmployeeThree\n" +
                        "    )");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}