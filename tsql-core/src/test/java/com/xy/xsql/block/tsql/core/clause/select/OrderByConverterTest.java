package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.IntoBuilderTest;
import com.xy.xsql.tsql.core.clause.select.OrderByBuilderTest;
import com.xy.xsql.tsql.model.clause.select.OrderBy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OrderByConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OrderByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ORDER BY Clause> ::=\n" +
                        "ORDER BY \n" +
                        "order_by_expression [ ASC | DESC ] [,...n] \n" +
                        "[ <offset_fetch> ]");
    }

    @Test
    public void testOffsetFetch() throws Exception {
        ReferenceBlock b = OrderByConverter.OffsetFetchConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<offset_fetch> ::=\n" +
                        "OFFSET { integer_constant | offset_row_count_expression } { ROW | ROWS } \n" +
                        "[\n" +
                        "FETCH { FIRST | NEXT } { integer_constant | fetch_row_count_expression } { ROW | ROWS } ONLY\n" +
                        "]");
    }



    private OrderByBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new OrderByBuilderTest();
    }

    @Test
    public void testPrint1A() throws Exception {
        OrderBy orderBy = builderTest.example1A;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY ProductID";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint1D() throws Exception {
        OrderBy orderBy = builderTest.example1D;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DATEPART(year, HireDate)";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint2A() throws Exception {
        OrderBy orderBy = builderTest.example2A;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY ProductID DESC";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint2B() throws Exception {
        OrderBy orderBy = builderTest.example2B;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY Name ASC";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint2C() throws Exception {
        OrderBy orderBy = builderTest.example2C;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY FirstName ASC, LastName DESC";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint2A1() throws Exception {
        OrderBy orderBy = builderTest.example3A1;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DepartmentID OFFSET 5 ROWS";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint2A2() throws Exception {
        OrderBy orderBy = builderTest.example3A2;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DepartmentID\n" +
                "     OFFSET 0 ROWS\n" +
                "     FETCH NEXT 10 ROWS ONLY";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint3B() throws Exception {
        OrderBy orderBy = builderTest.example3B;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DepartmentID ASC\n" +
                "     OFFSET @StartingRowNumber ROWS\n" +
                "     FETCH NEXT @FetchRows ROWS ONLY";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint3C() throws Exception {
        OrderBy orderBy = builderTest.example3C;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DepartmentID ASC\n" +
                "     OFFSET @StartingRowNumber - 1 ROWS\n" +
                "     FETCH NEXT @EndingRowNumber - @StartingRowNumber + 1 ROWS ONLY";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint3D() throws Exception {
        OrderBy orderBy = builderTest.example3D;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DepartmentID ASC\n" +
                "     OFFSET @StartingRowNumber ROWS\n" +
                "     FETCH NEXT (SELECT PageSize FROM dbo.AppSettings WHERE AppSettingID = 1) ROWS ONLY";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrint3E() throws Exception {
        OrderBy orderBy = builderTest.example3E;

        StringWriter writer = ReferenceBlockPrinter.print(orderBy);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "ORDER BY DepartmentID ASC\n" +
                "     OFFSET @StartingRowNumber - 1 ROWS\n" +
                "     FETCH NEXT @RowCountPerPage ROWS ONLY";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

}