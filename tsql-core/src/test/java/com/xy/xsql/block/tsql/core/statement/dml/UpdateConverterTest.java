package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.dml.UpdateBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Update;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class UpdateConverterTest {

    @Test
    public void test() throws Exception {
        BlockMeta b = UpdateConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<UPDATE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "UPDATE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "{\n" +
                        "{ table_alias | <object>\n" +
                        "[ WITH ( <Table_Hint_Limited> [...n] ) ] }\n" +
                        "}\n" +
                        "SET\n" +
                        "{\n" +
                        "column_name = expression | DEFAULT | NULL\n" +
                        "| @variable = expression\n" +
                        "| @variable = column = expression\n" +
                        "| column_name += | -= | *= | /= | %= | &= | ^= | |= expression\n" +
                        "| @variable { += | -= | *= | /= | %= | &= | ^= | |= } expression\n" +
                        "| @variable = column { += | -= | *= | /= | %= | &= | ^= | |= } expression\n" +
                        "} [,...n]\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "[ FROM table_source [ ,...n ]  ]\n" +
                        "[ WHERE <search_condition> ]\n" +
                        "[ OPTION ( <Query Hint> [ ,...n ] ) ]");
    }


    private Map<Update,String> model2StringMap;

    @Before
    public void init(){
        UpdateBuilderTest builderTest = new UpdateBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1A,
                "UPDATE Person.Address\n" +
                        "    SET ModifiedDate = GETDATE()");

        model2StringMap.put(
                builderTest.example1B,
                "UPDATE Sales.SalesPerson\n" +
                        "    SET Bonus = 6000, CommissionPct = .10, SalesQuota = NULL");

        model2StringMap.put(
                builderTest.example2A,
                "UPDATE Production.Product\n" +
                        "    SET Color = N'Metallic Red'\n" +
                        "    WHERE Name LIKE N'Road-250%' AND Color = N'Red'");

        /*
        TOP ()
         */
        model2StringMap.put(
                builderTest.example2B,
                "UPDATE HumanResources.Employee\n" +
                        "     SET VacationHours = VacationHours + 8\n" +
                        "     FROM (SELECT TOP (10) BusinessEntityID FROM HumanResources.Employee\n" +
                        "     ORDER BY HireDate ASC) AS th\n" +
                        "     WHERE HumanResources.Employee.BusinessEntityID = th.BusinessEntityID");
//                "UPDATE HumanResources.Employee\n" +
//                        "     SET VacationHours = VacationHours + 8\n" +
//                        "     FROM (SELECT TOP 10 BusinessEntityID FROM HumanResources.Employee\n" +
//                        "     ORDER BY HireDate ASC) AS th\n" +
//                        "     WHERE HumanResources.Employee.BusinessEntityID = th.BusinessEntityID");

        model2StringMap.put(
                builderTest.example2C,
                "WITH Parts(AssemblyID, ComponentID, PerAssemblyQty, EndDate, ComponentLevel) AS\n" +
                        "    (\n" +
                        "        SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty,\n" +
                        "            b.EndDate, 0 AS ComponentLevel\n" +
                        "        FROM Production.BillOfMaterials AS b\n" +
                        "        WHERE b.ProductAssemblyID = 800\n" +
                        "              AND b.EndDate IS NULL\n" +
                        "        UNION ALL\n" +
                        "        SELECT bom.ProductAssemblyID, bom.ComponentID, p.PerAssemblyQty,\n" +
                        "            bom.EndDate, ComponentLevel + 1\n" +
                        "        FROM Production.BillOfMaterials AS bom\n" +
                        "            INNER JOIN Parts AS p\n" +
                        "            ON bom.ProductAssemblyID = p.ComponentID\n" +
                        "            AND bom.EndDate IS NULL\n" +
                        "    )\n" +
                        "    UPDATE Production.BillOfMaterials\n" +
                        "    SET PerAssemblyQty = c.PerAssemblyQty * 2\n" +
                        "    FROM Production.BillOfMaterials AS c\n" +
                        "    JOIN Parts AS d ON c.ProductAssemblyID = d.AssemblyID\n" +
                        "    WHERE d.ComponentLevel = 0");

//        model2StringMap.put(
//                builderTest.example2D,
//                "UPDATE HumanResources.EmployeePayHistory\n" +
//                        "    SET PayFrequency = 2\n" +
//                        "    WHERE CURRENT OF complex_cursor;\n" +
//                        "    CLOSE complex_cursor;\n" +
//                        "    DEALLOCATE complex_cursor");

        model2StringMap.put(
                builderTest.example3A,
                "UPDATE Production.Product\n" +
                        "    SET ListPrice = ListPrice * 2");

        model2StringMap.put(
                builderTest.example3B,
                "UPDATE Production.Product\n" +
                        "    SET ListPrice += @NewPrice\n" +
                        "    WHERE Color = N'Red'");

        model2StringMap.put(
                builderTest.example3C,
                "UPDATE Sales.SalesPerson\n" +
                        "     SET SalesYTD = SalesYTD +\n" +
                        "     (SELECT SUM(so.SubTotal)\n" +
                        "     FROM Sales.SalesOrderHeader AS so\n" +
                        "     WHERE so.OrderDate = (SELECT MAX(OrderDate)\n" +
                        "     FROM Sales.SalesOrderHeader AS so2\n" +
                        "     WHERE so2.SalesPersonID = so.SalesPersonID)\n" +
                        "     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID\n" +
                        "     GROUP BY so.SalesPersonID)");

        model2StringMap.put(
                builderTest.example3D,
                "UPDATE Production.Location\n" +
                        "    SET CostRate = DEFAULT\n" +
                        "    WHERE CostRate > 20.0");
//                "UPDATE Production.Location\n" +
//                        "    SET CostRate = DEFAULT\n" +
//                        "    WHERE CostRate > 20.00");

        model2StringMap.put(
                builderTest.example4A,
                "UPDATE Person.vStateProvinceCountryRegion\n" +
                        "    SET CountryRegionName = 'United States of America'\n" +
                        "    WHERE CountryRegionName = 'United States'");

        model2StringMap.put(
                builderTest.example4B,
                "UPDATE sr\n" +
                        "     SET sr.Name += ' - tool malfunction'");

        model2StringMap.put(
                builderTest.example4B,
                "UPDATE sr\n" +
                        "    SET sr.Name += ' - tool malfunction'\n" +
                        "    FROM Production.ScrapReason AS sr\n" +
                        "    JOIN Production.WorkOrder AS wo\n" +
                        "         ON sr.ScrapReasonID = wo.ScrapReasonID\n" +
                        "         AND wo.ScrappedQty > 300");

//        model2StringMap.put(
//                builderTest.example4C,
//                "UPDATE @MyTableVar\n" +
//                        "    SET NewVacationHours = e.VacationHours + 20,\n" +
//                        "        ModifiedDate = GETDATE()\n" +
//                        "    FROM HumanResources.Employee AS e\n" +
//                        "    WHERE e.BusinessEntityID = EmpID");

        model2StringMap.put(
                builderTest.example5A1,
                "UPDATE Sales.SalesPerson\n" +
                        "     SET SalesYTD = SalesYTD + SubTotal\n" +
                        "     FROM Sales.SalesPerson AS sp\n" +
                        "     JOIN Sales.SalesOrderHeader AS so\n" +
                        "     ON sp.BusinessEntityID = so.SalesPersonID\n" +
                        "     AND so.OrderDate = (SELECT MAX(OrderDate)\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID = sp.BusinessEntityID)");

        model2StringMap.put(
                builderTest.example5A2,
                "UPDATE Sales.SalesPerson\n" +
                        "     SET SalesYTD = SalesYTD +\n" +
                        "     (SELECT SUM(so.SubTotal)\n" +
                        "     FROM Sales.SalesOrderHeader AS so\n" +
                        "     WHERE so.OrderDate = (SELECT MAX(OrderDate)\n" +
                        "     FROM Sales.SalesOrderHeader AS so2\n" +
                        "     WHERE so2.SalesPersonID = so.SalesPersonID)\n" +
                        "     AND Sales.SalesPerson.BusinessEntityID = so.SalesPersonID\n" +
                        "     GROUP BY so.SalesPersonID)");





        model2StringMap.put(
                builderTest.example6A,
                "UPDATE MyLinkServer.AdventureWorks2012.HumanResources.Department\n" +
                        "     SET GroupName = N'Public Relations'\n" +
                        "     WHERE DepartmentID = 4");

//        model2StringMap.put(
//                builderTest.example6B,
//                "UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')\n" +
//                        "     SET GroupName = 'Sales and Marketing'");

//        model2StringMap.put(
//                builderTest.example6C,
//                "UPDATE OPENQUERY (MyLinkServer, 'SELECT GroupName FROM HumanResources.Department WHERE DepartmentID = 4')\n" +
//                        "    SET GroupName = 'Sales and Marketing'");

//        model2StringMap.put(
//                builderTest.example7A,
//                "UPDATE Production.Document\n" +
//                        "    SET DocumentSummary .WRITE (N'features',28,10)\n" +
//                        "    OUTPUT deleted.DocumentSummary,\n" +
//                        "           inserted.DocumentSummary\n" +
//                        "        INTO @MyTableVar\n" +
//                        "    WHERE Title = N'Front Reflector Bracket Installation'");

        model2StringMap.put(
                builderTest.example7B1,
                "UPDATE Production.Document\n" +
                        "    SET DocumentSummary = N'Replacing NULL value'\n" +
                        "    WHERE Title = N'Crank Arm and Tire Maintenance'");

//        model2StringMap.put(
//                builderTest.example7B2,
//                "UPDATE Production.Document\n" +
//                        "    SET DocumentSummary .WRITE(N'Carefully inspect and maintain the tires and crank arms.',0,NULL)\n" +
//                        "    WHERE Title = N'Crank Arm and Tire Maintenance'");

//        model2StringMap.put(
//                builderTest.example7B3,
//                "UPDATE Production.Document\n" +
//                        "    SET DocumentSummary .WRITE (N' Appending data to the end of the column.', NULL, 0)\n" +
//                        "    WHERE Title = N'Crank Arm and Tire Maintenance'");

//        model2StringMap.put(
//                builderTest.example7B4,
//                "UPDATE Production.Document\n" +
//                        "    SET DocumentSummary .WRITE (NULL, 56, 0)\n" +
//                        "    WHERE Title = N'Crank Arm and Tire Maintenance'");

//        model2StringMap.put(
//                builderTest.example7B5,
//                "UPDATE Production.Document\n" +
//                        "    SET DocumentSummary .WRITE ('',9, 12)\n" +
//                        "    WHERE Title = N'Crank Arm and Tire Maintenance'");

//        model2StringMap.put(
//                builderTest.example7C,
//                "UPDATE Production.ProductPhoto\n" +
//                        "    SET ThumbNailPhoto = (\n" +
//                        "        SELECT *\n" +
//                        "        FROM OPENROWSET(BULK 'c:Tires.jpg', SINGLE_BLOB) AS x )\n" +
//                        "    WHERE ProductPhotoID = 1");

        model2StringMap.put(
                builderTest.example7D,
                "UPDATE Archive.dbo.Records\n" +
                        "    SET [Chart] = CAST('Xray 1' as varbinary(max))\n" +
                        "    WHERE [SerialNumber] = 2");

        model2StringMap.put(
                builderTest.example8A,
                "UPDATE dbo.Cities\n" +
                        "    SET Location = CONVERT(Point, '12.3:46.2')\n" +
                        "    WHERE Name = 'Anchorage'");

//        model2StringMap.put(
//                builderTest.example8B,
//                "UPDATE dbo.Cities\n" +
//                        "    SET Location.SetXY(23.5, 23.5)\n" +
//                        "    WHERE Name = 'Anchorage'");

        model2StringMap.put(
                builderTest.example8C,
                "UPDATE dbo.Cities\n" +
                        "    SET Location.X = 23.5\n" +
                        "    WHERE Name = 'Anchorage'");

        model2StringMap.put(
                builderTest.example9A,
                "UPDATE Production.Product\n" +
                        "    WITH (TABLOCK)\n" +
                        "    SET ListPrice = ListPrice * 1.1\n" +
                        "    WHERE ProductNumber LIKE 'BK-%'");
//                "UPDATE Production.Product\n" +
//                        "    WITH (TABLOCK)\n" +
//                        "    SET ListPrice = ListPrice * 1.10\n" +
//                        "    WHERE ProductNumber LIKE 'BK-%'");

        model2StringMap.put(
                builderTest.example9B,
                "UPDATE Production.Product\n" +
                        "    SET ListPrice = ListPrice * 1.1\n" +
                        "    WHERE ProductNumber LIKE @Product\n" +
                        "    OPTION (OPTIMIZE FOR (@Product = 'BK-%') )");
//                "UPDATE Production.Product\n" +
//                        "    SET ListPrice = ListPrice * 1.10\n" +
//                        "    WHERE ProductNumber LIKE @Product\n" +
//                        "    OPTION (OPTIMIZE FOR (@Product = 'BK-%') )");

        model2StringMap.put(
                builderTest.example10A,
                "UPDATE TOP (10) HumanResources.Employee\n" +
                        "    SET VacationHours = VacationHours * 1.25,\n" +
                        "        ModifiedDate = GETDATE()\n" +
                        "    OUTPUT INSERTED.BusinessEntityID,\n" +
                        "           DELETED.VacationHours,\n" +
                        "           INSERTED.VacationHours,\n" +
                        "           INSERTED.ModifiedDate\n" +
                        "    INTO @MyTableVar");
//                "UPDATE TOP (10) HumanResources.Employee\n" +
//                        "    SET VacationHours = VacationHours * 1.25,\n" +
//                        "        ModifiedDate = GETDATE()\n" +
//                        "    OUTPUT inserted.BusinessEntityID,\n" +
//                        "           deleted.VacationHours,\n" +
//                        "           inserted.VacationHours,\n" +
//                        "           inserted.ModifiedDate\n" +
//                        "    INTO @MyTableVar");

        model2StringMap.put(
                builderTest.example11A,
                "UPDATE HumanResources.Employee\n" +
                        "    SET VacationHours =\n" +
                        "         CASE\n" +
                        "             WHEN SalariedFlag = 0 THEN VacationHours + @NewHours\n" +
                        "             ELSE @NewHours\n" +
                        "           END\n" +
                        "        \n" +
                        "    WHERE CurrentFlag = 1");
//                "UPDATE HumanResources.Employee\n" +
//                        "    SET VacationHours =\n" +
//                        "        ( CASE\n" +
//                        "             WHEN SalariedFlag = 0 THEN VacationHours + @NewHours\n" +
//                        "             ELSE @NewHours\n" +
//                        "           END\n" +
//                        "        )\n" +
//                        "    WHERE CurrentFlag = 1");

        model2StringMap.put(
                builderTest.example11B,
                "UPDATE HumanResources.Department\n" +
                        "        SET Name = N'MyNewName'\n" +
                        "        WHERE DepartmentID BETWEEN 1 AND 2");

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