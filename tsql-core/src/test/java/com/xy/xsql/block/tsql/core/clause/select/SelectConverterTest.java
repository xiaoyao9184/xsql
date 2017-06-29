package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.SelectBuilderTest;
import com.xy.xsql.tsql.model.clause.select.Select;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class SelectConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = SelectConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SELECT Clause> ::=\n" +
                        "SELECT [ ALL | DISTINCT ] \n" +
                        "[ TOP ( expression ) [ PERCENT ] [ WITH TIES ] ] \n" +
                        "<select_list>");
    }

    @Test
    public void testSelectList() throws Exception {
        ReferenceBlock b = SelectConverter.SelectListConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

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



    private SelectBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new SelectBuilderTest();
    }

    @Test
    public void testPrintA1() throws Exception {
        Select select = builderTest.exampleA1;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT *";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintA2() throws Exception {
        Select select = builderTest.exampleA2;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT p.*";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintA3() throws Exception {
        Select select = builderTest.exampleA3;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT Name, ProductNumber, ListPrice AS Price";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        Select select = builderTest.exampleB;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT p.Name AS ProductName,\n" +
                "     NonDiscountSales = (OrderQty * UnitPrice),\n" +
                "     Discounts = ((OrderQty * UnitPrice) * UnitPriceDiscount)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB2() throws Exception {
        Select select = builderTest.exampleB2;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT 'Total income is', ((OrderQty * UnitPrice) * (1.0 - UnitPriceDiscount)), ' for ',\n" +
                "     p.Name AS ProductName";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        Select select = builderTest.exampleC;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT DISTINCT JobTitle";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintE2() throws Exception {
        Select select = builderTest.exampleE2;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT DISTINCT p.LastName, p.FirstName";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintF() throws Exception {
        Select select = builderTest.exampleF;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT SalesOrderID, SUM(LineTotal) AS SubTotal";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintG() throws Exception {
        Select select = builderTest.exampleG;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT ProductID, SpecialOfferID, AVG(UnitPrice) AS [Average Price],\n" +
                "     SUM(LineTotal) AS SubTotal";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintH() throws Exception {
        Select select = builderTest.exampleH;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT ProductModelID, AVG(ListPrice) AS [Average List Price]";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintI() throws Exception {
        Select select = builderTest.exampleI;

        StringWriter writer = ReferenceBlockPrinter.print(select);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "SELECT AVG(OrderQty) AS [Average Quantity],\n" +
                "     NonDiscountSales = (OrderQty * UnitPrice)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }


}