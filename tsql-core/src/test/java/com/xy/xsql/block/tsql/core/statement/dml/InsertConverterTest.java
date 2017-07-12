package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.core.MetaContextKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.dml.InsertBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Insert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class InsertConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = InsertConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INSERT> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "INSERT\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ INTO ]\n" +
                        "{\n" +
                        "<object>\n" +
                        "[ WITH ( <Table_Hint_Limited> [...n] ) ]\n" +
                        "}\n" +
                        "[ ( column_list ) ]\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "{\n" +
                        "<VALUES ( { DEFAULT | NULL | expression } [ ,...n ] ) [ ,...n     ]>\n" +
                        "| DEFAULT VALUES\n" +
                        "}");
    }

    private Map<Insert,String> model2StringMap;

    @Before
    public void init(){
        InsertBuilderTest builderTest = new InsertBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "INSERT INTO Production.UnitMeasure\n" +
                        "    VALUES (N'FT', N'Feet', '20080414')");

        model2StringMap.put(
                builderTest.exampleB,
                "INSERT INTO Production.UnitMeasure\n" +
                        "    VALUES (N'FT2', N'Square Feet ', '20080923'), (N'Y', N'Yards', '20080923')\n" +
                        "        , (N'Y3', N'Cubic Yards', '20080923')");

        model2StringMap.put(
                builderTest.exampleC,
                "INSERT INTO Production.UnitMeasure (Name, UnitMeasureCode,  \n" +
                        "        ModifiedDate)  \n" +
                        "    VALUES (N'Square Yards', N'Y2', GETDATE())");

        model2StringMap.put(
                builderTest.exampleD1,
                "INSERT INTO dbo.T1 (column_4)\n" +
                        "        VALUES ('Explicit value')");

        model2StringMap.put(
                builderTest.exampleD2,
                "INSERT INTO dbo.T1 (column_2, column_4)\n" +
                        "        VALUES ('Explicit value', 'Explicit value')");

        model2StringMap.put(
                builderTest.exampleD3,
                "INSERT INTO dbo.T1 (column_2)\n" +
                        "        VALUES ('Explicit value')");

        model2StringMap.put(
                builderTest.exampleD4,
                "INSERT INTO T1 DEFAULT VALUES");

        model2StringMap.put(
                builderTest.exampleE1,
                "INSERT T1 VALUES ('Row #1')");

        model2StringMap.put(
                builderTest.exampleE2,
                "INSERT T1 (column_2) VALUES ('Row #2')");

        model2StringMap.put(
                builderTest.exampleE3,
                "INSERT INTO T1 (column_1,column_2)\n" +
                        "    VALUES (-99, 'Explicit identity value')");

        model2StringMap.put(
                builderTest.exampleF1,
                "INSERT INTO dbo.T1 (column_2)\n" +
                        "        VALUES (NEWID())");

        model2StringMap.put(
                builderTest.exampleF2,
                "INSERT INTO T1 DEFAULT VALUES");

        model2StringMap.put(
                builderTest.exampleG1,
                "INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '3,4'))");

        model2StringMap.put(
                builderTest.exampleG2,
                "INSERT INTO dbo.Points (PointValue) VALUES (CONVERT(Point, '1,5'))");

//        model2StringMap.put(
//                builderTest.exampleH1,
//                "INSERT INTO dbo.EmployeeSales\n" +
//                        "    SELECT 'SELECT', sp.BusinessEntityID, c.LastName, sp.SalesYTD\n" +
//                        "    FROM Sales.SalesPerson AS sp\n" +
//                        "    INNER JOIN Person.Person AS c\n" +
//                        "        ON sp.BusinessEntityID = c.BusinessEntityID\n" +
//                        "    WHERE sp.BusinessEntityID LIKE '2%'\n" +
//                        "    ORDER BY sp.BusinessEntityID, c.LastName");
//
//        model2StringMap.put(
//                builderTest.exampleH2,
//                "INSERT INTO dbo.EmployeeSales\n" +
//                        "     EXECUTE dbo.uspGetEmployeeSales;");
//
//        model2StringMap.put(
//                builderTest.exampleH3,
//                "INSERT INTO dbo.EmployeeSales\n" +
//                        "     EXECUTE\n" +
//                        "     ('\n" +
//                        "     SELECT ''EXEC STRING'', sp.BusinessEntityID, c.LastName,\n" +
//                        "     sp.SalesYTD\n" +
//                        "     FROM Sales.SalesPerson AS sp\n" +
//                        "     INNER JOIN Person.Person AS c\n" +
//                        "     ON sp.BusinessEntityID = c.BusinessEntityID\n" +
//                        "     WHERE sp.BusinessEntityID LIKE ''2%''\n" +
//                        "     ORDER BY sp.BusinessEntityID, c.LastName\n" +
//                        "     ')");

//        model2StringMap.put(
//                builderTest.exampleI,
//                "WITH EmployeeTemp (EmpID, LastName, FirstName, Phone,\n" +
//                        "     Address, City, StateProvince,\n" +
//                        "     PostalCode, CurrentFlag)\n" +
//                        "     AS (SELECT\n" +
//                        "     e.BusinessEntityID, c.LastName, c.FirstName, pp.PhoneNumber,\n" +
//                        "     a.AddressLine1, a.City, sp.StateProvinceCode,\n" +
//                        "     a.PostalCode, e.CurrentFlag\n" +
//                        "     FROM HumanResources.Employee e\n" +
//                        "     INNER JOIN Person.BusinessEntityAddress AS bea\n" +
//                        "     ON e.BusinessEntityID = bea.BusinessEntityID\n" +
//                        "     INNER JOIN Person.Address AS a\n" +
//                        "     ON bea.AddressID = a.AddressID\n" +
//                        "     INNER JOIN Person.PersonPhone AS pp\n" +
//                        "     ON e.BusinessEntityID = pp.BusinessEntityID\n" +
//                        "     INNER JOIN Person.StateProvince AS sp\n" +
//                        "     ON a.StateProvinceID = sp.StateProvinceID\n" +
//                        "     INNER JOIN Person.Person as c\n" +
//                        "     ON e.BusinessEntityID = c.BusinessEntityID\n" +
//                        "     )\n" +
//                        "     INSERT INTO HumanResources.NewEmployee\n" +
//                        "     SELECT EmpID, LastName, FirstName, Phone,\n" +
//                        "     Address, City, StateProvince, PostalCode, CurrentFlag\n" +
//                        "     FROM EmployeeTemp");

//        model2StringMap.put(
//                builderTest.exampleJ1,
//                "INSERT TOP(5)INTO dbo.EmployeeSales\n" +
//                        "     OUTPUT inserted.EmployeeID, inserted.FirstName,\n" +
//                        "     inserted.LastName, inserted.YearlySales\n" +
//                        "     SELECT sp.BusinessEntityID, c.LastName, c.FirstName, sp.SalesYTD\n" +
//                        "     FROM Sales.SalesPerson AS sp\n" +
//                        "     INNER JOIN Person.Person AS c\n" +
//                        "     ON sp.BusinessEntityID = c.BusinessEntityID\n" +
//                        "     WHERE sp.SalesYTD > 250000.00\n" +
//                        "     ORDER BY sp.SalesYTD DESC");

//        model2StringMap.put(
//                builderTest.exampleJ2,
//                "INSERT INTO dbo.EmployeeSales\n" +
//                        "     OUTPUT inserted.EmployeeID, inserted.FirstName,\n" +
//                        "     inserted.LastName, inserted.YearlySales\n" +
//                        "     SELECT TOP (5) sp.BusinessEntityID, c.LastName, c.FirstName, sp.SalesYTD\n" +
//                        "     FROM Sales.SalesPerson AS sp\n" +
//                        "     INNER JOIN Person.Person AS c\n" +
//                        "     ON sp.BusinessEntityID = c.BusinessEntityID\n" +
//                        "     WHERE sp.SalesYTD > 250000.00\n" +
//                        "     ORDER BY sp.SalesYTD DESC");

        model2StringMap.put(
                builderTest.exampleK,
                "INSERT INTO V1\n" +
                        "     VALUES ('Row 1',1)");

//        model2StringMap.put(
//                builderTest.exampleL,
//                "INSERT INTO @MyTableVar (LocationID, CostRate, ModifiedDate)\n" +
//                        "    SELECT LocationID, CostRate, GETDATE()\n" +
//                        "    FROM Production.Location\n" +
//                        "    WHERE CostRate > 0");

        model2StringMap.put(
                builderTest.exampleM,
                "INSERT INTO MyLinkServer.AdventureWorks2012.HumanResources.Department (Name, GroupName)\n" +
                        "     VALUES (N'Public Relations', N'Executive General and Administration')");

//        model2StringMap.put(
//                builderTest.exampleN,
//                "INSERT OPENQUERY (MyLinkServer,\n" +
//                        "        'SELECT Name, GroupName\n" +
//                        "         FROM AdventureWorks2012.HumanResources.Department')\n" +
//                        "    VALUES ('Environmental Impact', 'Engineering')");

//        model2StringMap.put(
//                builderTest.exampleO,
//                "INSERT INTO OPENDATASOURCE('SQLNCLI',\n" +
//                        "        'Data Source= <server_name>; Integrated Security=SSPI')\n" +
//                        "        .AdventureWorks2012.HumanResources.Department (Name, GroupName)\n" +
//                        "        VALUES (N'Standards and Methods', 'Quality Assurance')");

//        model2StringMap.put(
//                builderTest.exampleP,
//                "INSERT INTO dbo.FastCustomer2009\n" +
//                        "    SELECT T.* FROM Insured_Customers T1 JOIN CarSensor_Data T2\n" +
//                        "    ON (T1.CustomerKey = T2.CustomerKey)\n" +
//                        "    WHERE T2.YearMeasured = 2009 and T2.Speed > 40");

//        model2StringMap.put(
//                builderTest.exampleQ,
//                "INSERT INTO Sales.SalesHistory WITH (TABLOCK)\n" +
//                        "         (SalesOrderID,\n" +
//                        "         SalesOrderDetailID,\n" +
//                        "         CarrierTrackingNumber,\n" +
//                        "         OrderQty,\n" +
//                        "         ProductID,\n" +
//                        "         SpecialOfferID,\n" +
//                        "         UnitPrice,\n" +
//                        "         UnitPriceDiscount,\n" +
//                        "         LineTotal,\n" +
//                        "         rowguid,\n" +
//                        "         ModifiedDate)\n" +
//                        "     SELECT * FROM Sales.SalesOrderDetail");

//        model2StringMap.put(
//                builderTest.exampleR,
//                "INSERT INTO HumanResources.Department WITH (IGNORE_TRIGGERS) (Name, GroupName)\n" +
//                        "    SELECT b.Name, b.GroupName\n" +
//                        "    FROM OPENROWSET (\n" +
//                        "        BULK 'C:SQLFilesDepartmentData.txt',\n" +
//                        "        FORMATFILE = 'C:SQLFilesBulkloadFormatFile.xml',\n" +
//                        "        ROWS_PER_BATCH = 15000)AS b");

        model2StringMap.put(
                builderTest.exampleS,
                "INSERT INTO Production.Location WITH (XLOCK)\n" +
                        "     (Name, CostRate, Availability)\n" +
                        "     VALUES ( N'Final Inventory', 15.0, 80.0)");
//                "INSERT INTO Production.Location WITH (XLOCK)\n" +
//                        "     (Name, CostRate, Availability)\n" +
//                        "     VALUES ( N'Final Inventory', 15.00, 80.00)");

        model2StringMap.put(
                builderTest.exampleT,
                "INSERT Production.ScrapReason\n" +
                        "        OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate\n" +
                        "            INTO @MyTableVar\n" +
                        "    VALUES (N'Operator error', GETDATE())");

//        model2StringMap.put(
//                builderTest.exampleU,
//                "INSERT INTO dbo.EmployeeSales (LastName, FirstName, CurrentSales)\n" +
//                        "      OUTPUT INSERTED.LastName,\n" +
//                        "             INSERTED.FirstName,\n" +
//                        "             INSERTED.CurrentSales\n" +
//                        "      INTO @MyTableVar\n" +
//                        "        SELECT c.LastName, c.FirstName, sp.SalesYTD\n" +
//                        "        FROM Sales.SalesPerson AS sp\n" +
//                        "        INNER JOIN Person.Person AS c\n" +
//                        "            ON sp.BusinessEntityID = c.BusinessEntityID\n" +
//                        "        WHERE sp.BusinessEntityID LIKE '2%'\n" +
//                        "        ORDER BY c.LastName, c.FirstName");

//        model2StringMap.put(
//                builderTest.exampleW,
//                "INSERT INTO EmployeeTitles\n" +
//                        "    SELECT EmployeeKey, LastName, Title\n" +
//                        "    FROM ssawPDW.dbo.DimEmployee\n" +
//                        "    WHERE EndDate IS NULL");

//        model2StringMap.put(
//                builderTest.exampleX,
//                "INSERT INTO DimCurrency\n" +
//                        "    VALUES (500, N'C1', N'Currency1')\n" +
//                        "    OPTION ( LABEL = N'label1' )");

//        model2StringMap.put(
//                builderTest.exampleY,
//                "INSERT INTO DimCustomer (CustomerKey, CustomerAlternateKey,\n" +
//                        "        FirstName, MiddleName, LastName )\n" +
//                        "    SELECT ProspectiveBuyerKey, ProspectAlternateKey,\n" +
//                        "        FirstName, MiddleName, LastName\n" +
//                        "    FROM ProspectiveBuyer p JOIN DimGeography g ON p.PostalCode = g.PostalCode\n" +
//                        "    WHERE g.CountryRegionCode = 'FR'\n" +
//                        "    OPTION ( LABEL = 'Add French Prospects', HASH JOIN)");

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

    @SuppressWarnings("Duplicates")
    @Test
    public void testKeywordPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            String check = MetaContextKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);

            check = check
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