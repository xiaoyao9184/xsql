package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.statement.DeleteBuilderTest;
import com.xy.xsql.tsql.model.statement.dml.Delete;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/17.
 */
public class DeleteConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = DeleteConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DELETE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "DELETE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ FROM ]\n" +
                        "{\n" +
                        "table_alias\n" +
                        "| <object>\n" +
                        "}\n" +
                        "[ <OUTPUT Clause> ]\n" +
                        "[ FROM table_source [ ,...n ] ]\n" +
                        "[ WHERE { <search_condition> ]\n" +
                        "[ OPTION ( <Query Hint> [ ,...n ] ) ]");
    }

    private Map<Delete,String> model2StringMap;

    @Before
    public void init(){
        DeleteBuilderTest builderTest = new DeleteBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DELETE FROM Sales.SalesPersonQuotaHistory");

        model2StringMap.put(
                builderTest.exampleB,
                "DELETE FROM Production.ProductCostHistory\n" +
                        "     WHERE StandardCost > 1000.0");

        model2StringMap.put(
                builderTest.example2A1,
                "DELETE FROM Production.ProductCostHistory\n" +
                        "     WHERE StandardCost BETWEEN 12.0 AND 14.0\n" +
                        "     AND EndDate IS NULL");

        //TODO support CURRENT OF
//        model2StringMap.put(
//                builderTest.example2B,
//                "DELETE FROM HumanResources.EmployeePayHistory\n" +
//                        "     WHERE CURRENT OF complex_cursor");

        model2StringMap.put(
                builderTest.example2C,
                "DELETE FROM Sales.SalesPersonQuotaHistory\n" +
                        "     WHERE BusinessEntityID IN\n" +
                        "     (SELECT BusinessEntityID\n" +
                        "     FROM Sales.SalesPerson\n" +
                        "     WHERE SalesYTD > 2500000.0)");

        model2StringMap.put(
                builderTest.example2C1,
                "DELETE FROM Sales.SalesPersonQuotaHistory\n" +
                        "     FROM Sales.SalesPersonQuotaHistory spqh\n" +
                        "     INNER JOIN Sales.SalesPerson sp\n" +
                        "     ON spqh.BusinessEntityID = sp.BusinessEntityID\n" +
                        "     WHERE sp.SalesYTD > 2500000.0");

        model2StringMap.put(
                builderTest.example2C2,
                "DELETE spqh\n" +
                        "     FROM\n" +
                        "     Sales.SalesPersonQuotaHistory spqh\n" +
                        "     INNER JOIN Sales.SalesPerson sp ON spqh.BusinessEntityID = sp.BusinessEntityID\n" +
                        "     WHERE\n" +
                        "     sp.SalesYTD > 2500000.0");

        model2StringMap.put(
                builderTest.example2D,
                "DELETE TOP (20)\n" +
                        "     FROM Purchasing.PurchaseOrderDetail\n" +
                        "     WHERE DueDate < '20020701'");

        model2StringMap.put(
                builderTest.example2D1,
                "DELETE FROM Purchasing.PurchaseOrderDetail\n" +
                        "     WHERE PurchaseOrderDetailID IN\n" +
                        "     (SELECT TOP (10) PurchaseOrderDetailID\n" +
                        "     FROM Purchasing.PurchaseOrderDetail\n" +
                        "     ORDER BY DueDate ASC)");

        model2StringMap.put(
                builderTest.example4A,
                "DELETE Sales.ShoppingCartItem\n" +
                        "     OUTPUT DELETED.*\n" +
                        "     WHERE ShoppingCartID = 20621");

        model2StringMap.put(
                builderTest.example4B,
                "DELETE Production.ProductProductPhoto\n" +
                        "     OUTPUT DELETED.ProductID,\n" +
                        "     p.Name,\n" +
                        "     p.ProductModelID,\n" +
                        "     DELETED.ProductPhotoID\n" +
                        "     INTO @MyTableVar\n" +
                        "     FROM Production.ProductProductPhoto ph\n" +
                        "     JOIN Production.Product p\n" +
                        "     ON ph.ProductID = p.ProductID\n" +
                        "     WHERE p.ProductModelID BETWEEN 120 AND 130");


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