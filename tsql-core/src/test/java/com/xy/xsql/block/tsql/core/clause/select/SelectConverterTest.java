package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.select.SelectBuilderTest;
import com.xy.xsql.tsql.model.clause.select.Select;
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
    public void test() throws Exception {
        BlockMeta b = SelectConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SELECT Clause> ::=\n" +
                        "SELECT [ ALL | DISTINCT ] \n" +
                        "[ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ] \n" +
                        "<select_list>");
    }

    @Test
    public void testSelectList() throws Exception {
        BlockMeta b = SelectConverter.SelectListConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<select_list> ::=\n" +
                        "{\n" +
                        "*\n" +
                        "| { table_name | view_name | table_alias }.* \n" +
                        "| {\n" +
                        "[ { table_name | view_name | table_alias }. ] { column_name | $IDENTITY | $ROWGUID }\n" +
                        "| expression\n" +
                        "[ [ AS ] column_alias ]\n" +
                        "}\n" +
                        "| column_alias = expression\n" +
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

}