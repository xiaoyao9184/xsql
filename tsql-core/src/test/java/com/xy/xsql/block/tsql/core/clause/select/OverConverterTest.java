package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.OverBuilderTest;
import com.xy.xsql.tsql.model.clause.select.Over;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class OverConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OverConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OVER> ::=\n" +
                        "OVER ( \n" +
                        "<PARTITION BY clause>\n" +
                        "<ORDER BY clause>\n" +
                        " )");
    }

    @Test
    public void testPartitionBy() throws Exception {
        ReferenceBlock b = OverConverter.PartitionByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PARTITION BY clause> ::=\n" +
                        "PARTITION BY value_expression [,...n]");
    }

    @Test
    public void testOrderBy() throws Exception {
        ReferenceBlock b = OverConverter.OrderByConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ORDER BY clause> ::=\n" +
                        "ORDER BY order_by_expression [ ASC | DESC ] [,...n]");
    }



    private OverBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new OverBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        Over over = builderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(over);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OVER(PARTITION BY PostalCode ORDER BY SalesYTD DESC)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        Over over = builderTest.exampleB;

        StringWriter writer = ReferenceBlockPrinter.print(over);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OVER(PARTITION BY SalesOrderID)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        Over over = builderTest.exampleC;

        StringWriter writer = ReferenceBlockPrinter.print(over);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OVER (PARTITION BY TerritoryID\n" +
                "     ORDER BY DATEPART(yy,ModifiedDate)\n" +
                "     )";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintD() throws Exception {
        Over over = builderTest.exampleD;

        StringWriter writer = ReferenceBlockPrinter.print(over);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        //TODO
        String ok = "OVER (PARTITION BY TerritoryID\n" +
                "     ORDER BY DATEPART(yy,ModifiedDate)\n"
                +
//                "     ROWS BETWEEN CURRENT ROW AND 1 FOLLOWING )\n" +
                "     )";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintE() throws Exception {
        Over over = builderTest.exampleE;

        StringWriter writer = ReferenceBlockPrinter.print(over);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OVER(ORDER BY SUM(SalesAmountQuota) DESC)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintF() throws Exception {
        Over over = builderTest.exampleF;

        StringWriter writer = ReferenceBlockPrinter.print(over);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OVER(PARTITION BY SalesOrderNumber)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

}