package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.WithBuilderTest;
import com.xy.xsql.tsql.model.clause.With;
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
    public void test() throws Exception {
        ReferenceBlock b = WithConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<WITH common_table_expression> ::=\n" +
                        "WITH <common_table_expression> [,...n]");
    }

    @Test
    public void testCommonTableExpression() throws Exception {
        ReferenceBlock b = WithConverter.CommonTableExpressionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<common_table_expression> ::=\n" +
                        "expression_name [ ( column_name [,...n] ) ] \n" +
                        "AS \n" +
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

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ReferenceBlockPrinter.print(key);
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