package com.xy.xsql.tsql.converter.queries.select;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.select.SelectBuilderTest;
import com.xy.xsql.tsql.converter.queries.select.SelectConverter;
import com.xy.xsql.tsql.model.queries.select.Select;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class SelectConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SelectConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SELECT Clause> ::=\n" +
                        "SELECT [ ALL | DISTINCT ]\n" +
                        "[ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ]\n" +
                        "<select_list>");
    }

    @Test
    public void testMetaPrint_SelectList() throws Exception {
        BlockMeta b = SelectConverter.SelectListConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<select_list> ::=\n" +
                        "{\n" +
                        "\t*\n" +
                        "\t| { table_name | view_name | table_alias }.* \n" +
                        "\t| {\n" +
                        "\t\t[ { table_name | view_name | table_alias }. ] { column_name | $IDENTITY | $ROWGUID }\n" +
                        "\t\t| expression\n" +
                        "\t\t[ [ AS ] column_alias ]\n" +
                        "\t}\n" +
                        "\t| column_alias = expression\n" +
                        "} [,...n]");
    }



    private Map<Select,String> model2StringMap;

    @Before
    public void init(){
        SelectBuilderTest builderTest = new SelectBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA1,
                "SELECT *");

        model2StringMap.put(
                builderTest.exampleA2,
                "SELECT p.*");

        model2StringMap.put(
                builderTest.exampleA3,
                "SELECT Name, ProductNumber, ListPrice AS Price");

        model2StringMap.put(
                builderTest.exampleB,
                "SELECT p.Name AS ProductName,\n" +
                        "     NonDiscountSales = (OrderQty * UnitPrice),\n" +
                        "     Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)");

        model2StringMap.put(
                builderTest.exampleB2,
                "SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',\n" +
                        "     p.Name AS ProductName");

        model2StringMap.put(
                builderTest.exampleC,
                "SELECT DISTINCT JobTitle");

        model2StringMap.put(
                builderTest.exampleE2,
                "SELECT DISTINCT p.LastName, p.FirstName");

        model2StringMap.put(
                builderTest.exampleF,
                "SELECT SalesOrderID, SUM(LineTotal) AS SubTotal");

        model2StringMap.put(
                builderTest.exampleG,
                "SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],\n" +
                        "     SUM(LineTotal) AS SubTotal");

        model2StringMap.put(
                builderTest.exampleH,
                "SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]");

        model2StringMap.put(
                builderTest.exampleI,
                "SELECT AVG(OrderQty) AS [Average Quantity],\n" +
                        "     NonDiscountSales = (OrderQty * UnitPrice)");

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