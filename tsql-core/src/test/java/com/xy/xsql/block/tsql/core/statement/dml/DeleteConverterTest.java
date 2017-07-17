package com.xy.xsql.block.tsql.core.statement.dml;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.dml.DeleteBuilderTest;
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
    public void testMetaPrint() throws Exception {
        BlockMeta b = DeleteConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DELETE> ::=\n" +
                        "[ WITH <common_table_expression> [ ,...n ] ]\n" +
                        "DELETE\n" +
                        "[ TOP ( expression ) [ PERCENT ] ]\n" +
                        "[ FROM ]\n" +
                        "{\n" +
                        "{ table_alias\n" +
                        "| <object>\n" +
                        "[ WITH ( table_hint_limited [...n] ) ] }\n" +
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
                builderTest.exampleB1,
                "DELETE FROM Production.ProductCostHistory\n" +
                        "    WHERE StandardCost > 1000.0");
//                "DELETE FROM Production.ProductCostHistory\n" +
//                        "    WHERE StandardCost > 1000.00");

        model2StringMap.put(
                builderTest.exampleB2,
                "DELETE Production.ProductCostHistory\n" +
                        "    WHERE StandardCost BETWEEN 12.0 AND 14.0\n" +
                        "          AND EndDate IS NULL");
//                "DELETE Production.ProductCostHistory\n" +
//                        "    WHERE StandardCost BETWEEN 12.00 AND 14.00\n" +
//                        "          AND EndDate IS NULL");

        //TODO support CURRENT OF
//        model2StringMap.put(
//                builderTest.exampleC,
//                "DELETE FROM HumanResources.EmployeePayHistory  \n" +
//                        "    WHERE CURRENT OF complex_cursor");

        model2StringMap.put(
                builderTest.exampleD1,
                "DELETE FROM Sales.SalesPersonQuotaHistory\n" +
                        "     WHERE BusinessEntityID IN\n" +
                        "     (SELECT BusinessEntityID\n" +
                        "     FROM Sales.SalesPerson\n" +
                        "     WHERE SalesYTD > 2500000.0)");
//                "DELETE FROM Sales.SalesPersonQuotaHistory\n" +
//                        "     WHERE BusinessEntityID IN\n" +
//                        "     (SELECT BusinessEntityID\n" +
//                        "     FROM Sales.SalesPerson\n" +
//                        "     WHERE SalesYTD > 2500000.00)");

        model2StringMap.put(
                builderTest.exampleD2,
                "DELETE FROM Sales.SalesPersonQuotaHistory   \n" +
                        "    FROM Sales.SalesPersonQuotaHistory AS spqh  \n" +
                        "    INNER JOIN Sales.SalesPerson AS sp  \n" +
                        "    ON spqh.BusinessEntityID = sp.BusinessEntityID  \n" +
                        "    WHERE sp.SalesYTD > 2500000.0");
//                "DELETE FROM Sales.SalesPersonQuotaHistory   \n" +
//                        "    FROM Sales.SalesPersonQuotaHistory AS spqh  \n" +
//                        "    INNER JOIN Sales.SalesPerson AS sp  \n" +
//                        "    ON spqh.BusinessEntityID = sp.BusinessEntityID  \n" +
//                        "    WHERE sp.SalesYTD > 2500000.00");

        model2StringMap.put(
                builderTest.exampleD3,
                "DELETE spqh  \n" +
                        "      FROM  \n" +
                        "            Sales.SalesPersonQuotaHistory AS spqh  \n" +
                        "        INNER JOIN Sales.SalesPerson AS sp  \n" +
                        "            ON spqh.BusinessEntityID = sp.BusinessEntityID  \n" +
                        "      WHERE  sp.SalesYTD > 2500000.0");
//                "DELETE spqh  \n" +
//                        "      FROM  \n" +
//                        "            Sales.SalesPersonQuotaHistory AS spqh  \n" +
//                        "        INNER JOIN Sales.SalesPerson AS sp  \n" +
//                        "            ON spqh.BusinessEntityID = sp.BusinessEntityID  \n" +
//                        "      WHERE  sp.SalesYTD > 2500000.00");

        model2StringMap.put(
                builderTest.exampleE1,
                "DELETE TOP (20)\n" +
                        "    FROM Purchasing.PurchaseOrderDetail\n" +
                        "    WHERE DueDate < '20020701'");

        /*
        Top ()
         */
        model2StringMap.put(
                builderTest.exampleE2,
                "DELETE FROM Purchasing.PurchaseOrderDetail\n" +
                        "     WHERE PurchaseOrderDetailID IN\n" +
                        "         (SELECT TOP (10) PurchaseOrderDetailID\n" +
                        "         FROM Purchasing.PurchaseOrderDetail\n" +
                        "         ORDER BY DueDate ASC)");
//                "DELETE FROM Purchasing.PurchaseOrderDetail\n" +
//                        "     WHERE PurchaseOrderDetailID IN\n" +
//                        "         (SELECT TOP 10 PurchaseOrderDetailID\n" +
//                        "         FROM Purchasing.PurchaseOrderDetail\n" +
//                        "         ORDER BY DueDate ASC)");

        model2StringMap.put(
                builderTest.exampleF,
                "DELETE MyLinkServer.AdventureWorks2012.HumanResources.Department\n" +
                        "        WHERE DepartmentID > 16");

//        model2StringMap.put(
//                builderTest.exampleG,
//                "DELETE OPENQUERY (MyLinkServer, 'SELECT Name, GroupName \n" +
//                        "    FROM AdventureWorks2012.HumanResources.Department  \n" +
//                        "    WHERE DepartmentID = 18')");

//        model2StringMap.put(
//                builderTest.exampleH,
//                "DELETE FROM OPENDATASOURCE('SQLNCLI',\n" +
//                        "        'Data Source= <server_name>; Integrated Security=SSPI')\n" +
//                        "        .AdventureWorks2012.HumanResources.Department\n" +
//                        "    WHERE DepartmentID = 17;'");

        model2StringMap.put(
                builderTest.exampleI,
                "DELETE Sales.ShoppingCartItem\n" +
                        "    OUTPUT DELETED.*\n" +
                        "    WHERE ShoppingCartID = 20621");

        /*
          Case ignored
         */
        model2StringMap.put(
                builderTest.exampleJ,
                "DELETE Production.ProductProductPhoto\n" +
                        "    OUTPUT DELETED.ProductID,\n" +
                        "           p.Name,\n" +
                        "           p.ProductModelID,\n" +
                        "           DELETED.ProductPhotoID\n" +
                        "        INTO @MyTableVar\n" +
                        "    FROM Production.ProductProductPhoto AS ph\n" +
                        "    JOIN Production.Product AS p\n" +
                        "        ON ph.ProductID = p.ProductID\n" +
                        "        WHERE p.ProductModelID BETWEEN 120 AND 130");
//                "DELETE Production.ProductProductPhoto\n" +
//                        "    OUTPUT DELETED.ProductID,\n" +
//                        "           p.Name,\n" +
//                        "           p.ProductModelID,\n" +
//                        "           DELETED.ProductPhotoID\n" +
//                        "        INTO @MyTableVar\n" +
//                        "    FROM Production.ProductProductPhoto AS ph\n" +
//                        "    JOIN Production.Product as p\n" +
//                        "        ON ph.ProductID = p.ProductID\n" +
//                        "        WHERE p.ProductModelID BETWEEN 120 and 130");


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
            System.out.println("==========");

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