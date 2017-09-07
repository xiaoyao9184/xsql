package com.xy.xsql.block.tsql.core.statement.ddl.create;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.statement.ddl.CreateViewBuilderTest;
import com.xy.xsql.tsql.model.statement.ddl.create.CreateView;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class CreateViewConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CreateViewConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Create a view> ::=\n" +
                        "CREATE [ OR ALTER ] VIEW [ schema_name . ] view_name [ ( column [,...n] ) ]\n" +
                        "[ WITH <view_attribute> [,...n] ]\n" +
                        "AS select_statement\n" +
                        "[ WITH CHECK OPTION ]");
    }

    @Test
    public void testMetaPrint_ViewAttribute() throws Exception {
        BlockMeta b = CreateViewConverter.ViewAttributeConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<view_attribute> ::=\n" +
                        "{\n" +
                        "\t[ ENCRYPTION ]\n" +
                        "\t[ SCHEMABINDING ]\n" +
                        "\t[ VIEW_METADATA ]\n" +
                        "}");
    }


    private Map<CreateView,String> model2StringMap;

    @Before
    public void init(){
        CreateViewBuilderTest builderTest = new CreateViewBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CREATE VIEW hiredate_view\n" +
                        "    AS\n" +
                        "    SELECT p.FirstName, p.LastName, e.BusinessEntityID, e.HireDate\n" +
                        "    FROM HumanResources.Employee e\n" +
                        "    JOIN Person.Person AS p ON e.BusinessEntityID = p.BusinessEntityID");

        model2StringMap.put(
                builderTest.exampleB,
                "CREATE VIEW Purchasing.PurchaseOrderReject\n" +
                        "    WITH ENCRYPTION\n" +
                        "    AS\n" +
                        "    SELECT PurchaseOrderID, ReceivedQty, RejectedQty,\n" +
                        "        RejectedQty / ReceivedQty AS RejectRatio, DueDate\n" +
                        "    FROM Purchasing.PurchaseOrderDetail\n" +
                        "    WHERE RejectedQty / ReceivedQty > 0\n" +
                        "    AND DueDate > CONVERT(DATETIME,'20010630',101)");

        model2StringMap.put(
                builderTest.exampleC,
                "CREATE VIEW dbo.SeattleOnly\n" +
                        "    AS\n" +
                        "    SELECT p.LastName, p.FirstName, e.JobTitle, a.City, sp.StateProvinceCode\n" +
                        "    FROM HumanResources.Employee e\n" +
                        "    INNER JOIN Person.Person p\n" +
                        "    ON p.BusinessEntityID = e.BusinessEntityID\n" +
                        "        INNER JOIN Person.BusinessEntityAddress bea\n" +
                        "        ON bea.BusinessEntityID = e.BusinessEntityID\n" +
                        "        INNER JOIN Person.Address a\n" +
                        "        ON a.AddressID = bea.AddressID\n" +
                        "        INNER JOIN Person.StateProvince sp\n" +
                        "        ON sp.StateProvinceID = a.StateProvinceID\n" +
                        "    WHERE a.City = 'Seattle'\n" +
                        "    WITH CHECK OPTION");

        model2StringMap.put(
                builderTest.exampleD,
                "CREATE VIEW Sales.SalesPersonPerform\n" +
                        "    AS\n" +
                        "    SELECT TOP (100) SalesPersonID, SUM(TotalDue) AS TotalSales\n" +
                        "    FROM Sales.SalesOrderHeader\n" +
                        "    WHERE OrderDate > CONVERT(DATETIME,'20001231',101)\n" +
                        "    GROUP BY SalesPersonID");

        model2StringMap.put(
                builderTest.exampleE,
                "CREATE VIEW dbo.all_supplier_view\n" +
                        "    WITH SCHEMABINDING\n" +
                        "    AS\n" +
                        "    SELECT supplyID, supplier\n" +
                        "      FROM dbo.SUPPLY1\n" +
                        "    UNION ALL\n" +
                        "    SELECT supplyID, supplier\n" +
                        "      FROM dbo.SUPPLY2\n" +
                        "    UNION ALL\n" +
                        "    SELECT supplyID, supplier\n" +
                        "      FROM dbo.SUPPLY3\n" +
                        "    UNION ALL\n" +
                        "    SELECT supplyID, supplier\n" +
                        "      FROM dbo.SUPPLY4");

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
                    .replaceAll("\t", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }
}