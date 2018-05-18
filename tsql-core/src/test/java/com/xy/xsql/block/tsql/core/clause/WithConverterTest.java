package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.WithBuilderTest;
import com.xy.xsql.tsql.model.queries.With;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class WithConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = WithConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<WITH common_table_expression> ::=\n" +
                        "WITH <common_table_expression> [,...n]");
    }

    @Test
    public void testMetaPrint_CommonTableExpression() throws Exception {
        BlockMeta b = WithConverter.CommonTableExpressionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<common_table_expression> ::=\n" +
                        "expression_name [ ( column_name [,...n] ) ]\n" +
                        "AS\n" +
                        "( CTE_query_definition )");
    }

    private Map<With,String> model2StringMap;

    @Before
    public void init(){
        WithBuilderTest builderTest = new WithBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleB,
                "WITH Sales_CTE (SalesPersonID, NumberOfOrders)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, COUNT(*)\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     GROUP BY SalesPersonID\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleC,
                "WITH Sales_CTE (SalesPersonID, TotalSales, SalesYear)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, SUM(TotalDue) AS TotalSales, YEAR(OrderDate) AS SalesYear\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     GROUP BY SalesPersonID, YEAR(OrderDate)\n" +
                        "\n" +
                        "     )," +
                        "Sales_Quota_CTE (BusinessEntityID, SalesQuota, SalesQuotaYear)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT BusinessEntityID, SUM(SalesQuota)AS SalesQuota, YEAR(QuotaDate) AS SalesQuotaYear\n" +
                        "     FROM Sales.SalesPersonQuotaHistory\n" +
                        "     GROUP BY BusinessEntityID, YEAR(QuotaDate)\n" +
                        "     )");

        //TODO derived table ()
        model2StringMap.put(
                builderTest.exampleD,
                "WITH DirectReports(ManagerID, EmployeeID, Title, EmployeeLevel) AS\n" +
                        "     (\n" +
                        "     SELECT ManagerID, EmployeeID, Title, 0 AS EmployeeLevel\n" +
                        "     FROM dbo.MyEmployees\n" +
                        "     WHERE ManagerID IS NULL\n" +
                        "     UNION ALL\n" +
                        "     SELECT e.ManagerID, e.EmployeeID, e.Title, EmployeeLevel + 1\n" +
                        "     FROM dbo.MyEmployees e\n" +
                        "     INNER JOIN DirectReports d\n" +
                        "     ON e.ManagerID = d.EmployeeID\n" +
                        "     )");

//        model2StringMap.put(
//                builderTest.exampleE,
//                "WITH DirectReports(ManagerID, EmployeeID, Title, EmployeeLevel) AS\n" +
//                        "     (\n" +
//                        "     SELECT ManagerID, EmployeeID, Title, 0 AS EmployeeLevel\n" +
//                        "     FROM dbo.MyEmployees\n" +
//                        "     WHERE ManagerID IS NULL\n" +
//                        "     UNION ALL\n" +
//                        "     SELECT e.ManagerID, e.EmployeeID, e.Title, EmployeeLevel + 1\n" +
//                        "     FROM dbo.MyEmployees e\n" +
//                        "     INNER JOIN DirectReports d\n" +
//                        "     ON e.ManagerID = d.EmployeeID\n" +
//                        "     )");

//        model2StringMap.put(
//                builderTest.exampleF,
//                "WITH DirectReports(Name, Title, EmployeeID, EmployeeLevel, Sort)\n" +
//                        "     AS (SELECT CONVERT(varchar(255), e.FirstName + ' ' + e.LastName),\n" +
//                        "     e.Title,\n" +
//                        "     e.EmployeeID,\n" +
//                        "     1,\n" +
//                        "     CONVERT(varchar(255), e.FirstName + ' ' + e.LastName)\n" +
//                        "     FROM dbo.MyEmployees e\n" +
//                        "     WHERE e.ManagerID IS NULL\n" +
//                        "     UNION ALL\n" +
//                        "     SELECT CONVERT(varchar(255), REPLICATE ('|    ' , EmployeeLevel) +\n" +
//                        "     e.FirstName + ' ' + e.LastName),\n" +
//                        "     e.Title,\n" +
//                        "     e.EmployeeID,\n" +
//                        "     EmployeeLevel + 1,\n" +
//                        "     CONVERT (varchar(255), RTRIM(Sort) + '|    ' + FirstName + ' ' +\n" +
//                        "     LastName)\n" +
//                        "     FROM dbo.MyEmployees e\n" +
//                        "     JOIN DirectReports d ON e.ManagerID = d.EmployeeID\n" +
//                        "     )");

//        model2StringMap.put(
//                builderTest.exampleG,
//                "WITH cte (EmployeeID, ManagerID, Title) AS\n" +
//                        "     (\n" +
//                        "     SELECT EmployeeID, ManagerID, Title\n" +
//                        "     FROM dbo.MyEmployees\n" +
//                        "     WHERE ManagerID IS NOT NULL\n" +
//                        "     UNION ALL\n" +
//                        "     SELECT cte.EmployeeID, cte.ManagerID, cte.Title\n" +
//                        "     FROM cte\n" +
//                        "     JOIN  dbo.MyEmployees e\n" +
//                        "     ON cte.ManagerID = e.EmployeeID\n" +
//                        "     )");


        model2StringMap.put(
                builderTest.exampleH,
                "WITH Parts(AssemblyID, ComponentID, PerAssemblyQty, EndDate, ComponentLevel) AS\n" +
                        "     (\n" +
                        "     SELECT b.ProductAssemblyID, b.ComponentID, b.PerAssemblyQty,\n" +
                        "     b.EndDate, 0 AS ComponentLevel\n" +
                        "     FROM Production.BillOfMaterials AS b\n" +
                        "     WHERE b.ProductAssemblyID = 800\n" +
                        "     AND b.EndDate IS NULL\n" +
                        "     UNION ALL\n" +
                        "     SELECT bom.ProductAssemblyID, bom.ComponentID, p.PerAssemblyQty,\n" +
                        "     bom.EndDate, ComponentLevel + 1\n" +
                        "     FROM Production.BillOfMaterials AS bom\n" +
                        "     INNER JOIN Parts AS p\n" +
                        "     ON bom.ProductAssemblyID = p.ComponentID\n" +
                        "     AND bom.EndDate IS NULL\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleJ,
                "WITH Generation (ID) AS\n" +
                        "     (\n" +
                        "     SELECT Mother\n" +
                        "     FROM dbo.Person\n" +
                        "     WHERE Name = 'Bonnie'\n" +
                        "     UNION\n" +
                        "     SELECT Father\n" +
                        "     FROM dbo.Person\n" +
                        "     WHERE Name = 'Bonnie'\n" +
                        "     UNION ALL\n" +
                        "     SELECT Person.Father\n" +
                        "     FROM Generation, Person\n" +
                        "     WHERE Generation.ID=Person.ID\n" +
                        "     UNION ALL\n" +
                        "     SELECT Person.Mother\n" +
                        "     FROM Generation, dbo.Person\n" +
                        "     WHERE Generation.ID=Person.ID\n" +
                        "     )");

        //TODO derived table ()
//        model2StringMap.put(
//                builderTest.exampleK,
//                "WITH vw AS\n" +
//                        "     (\n" +
//                        "     SELECT itmIDComp, itmID\n" +
//                        "     FROM @t1\n" +
//                        "\n" +
//                        "     UNION ALL\n" +
//                        "\n" +
//                        "     SELECT itmIDComp, itmID\n" +
//                        "     FROM @t2\n" +
//                        "     )\n" +
//                        "     ,r AS\n" +
//                        "     (\n" +
//                        "     SELECT t.itmID AS itmIDComp\n" +
//                        "     , NULL AS itmID\n" +
//                        "     ,CAST(0 AS bigint) AS N\n" +
//                        "     ,1 AS Lvl\n" +
//                        "     FROM (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) AS t (itmID)\n" +
//                        "\n" +
//                        "     UNION ALL\n" +
//                        "\n" +
//                        "     SELECT t.itmIDComp\n" +
//                        "     , t.itmID\n" +
//                        "     , ROW_NUMBER() OVER(PARTITION BY t.itmIDComp ORDER BY t.itmIDComp, t.itmID) AS N\n" +
//                        "     , Lvl + 1\n" +
//                        "     FROM r\n" +
//                        "     JOIN vw AS t ON t.itmID = r.itmIDComp");


        model2StringMap.put(
                builderTest.exampleL,
                "WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleM,
                "WITH Sales_CTE (SalesPersonID, NumberOfOrders)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, COUNT(*)\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     GROUP BY SalesPersonID\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleN,
                "WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleO,
                "WITH Sales_CTE (SalesPersonID, SalesOrderID, SalesYear)\n" +
                        "     AS\n" +
                        "     (\n" +
                        "     SELECT SalesPersonID, SalesOrderID, YEAR(OrderDate) AS SalesYear\n" +
                        "     FROM Sales.SalesOrderHeader\n" +
                        "     WHERE SalesPersonID IS NOT NULL\n" +
                        "     )");

        model2StringMap.put(
                builderTest.exampleP,
                "WITH\n" +
                        "     CountDate (TotalCount, TableName) AS\n" +
                        "     (\n" +
                        "     SELECT COUNT(datekey), 'DimDate' FROM DimDate\n" +
                        "     ) ,\n" +
                        "     CountCustomer (TotalAvg, TableName) AS\n" +
                        "     (\n" +
                        "     SELECT COUNT(CustomerKey), 'DimCustomer' FROM DimCustomer\n" +
                        "     )");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ModelMetaBlockPrinter.print(key);
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
            String check = ModelKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);

            check = check
                    .replaceAll(" ", "")
                    .replaceAll("\t", "")
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