package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.FromBuilderTest;
import com.xy.xsql.tsql.model.clause.From;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class FromConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = FromConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FROM> ::=\n" +
                        "FROM { <table_source> } [,...n]");
    }

    @Test
    public void testTableSource() throws Exception {
        ReferenceBlock b = FromConverter.TableSourceConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<table_source> ::=\n" +
                        "table_or_view_name [ [ AS ] table_alias ]\n" +
                        "| derived_table [ [ AS ] table_alias ] [ ( column_alias [,...n] ) ]\n" +
                        "| <joined_table>\n" +
                        "| @variable [ [ AS ] table_alias ]");
    }

    @Test
    public void testBaseTable() throws Exception {
        ReferenceBlock b = FromConverter.BaseTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "table_or_view_name [ [ AS ] table_alias ]");
    }

    @Test
    public void testDerivedTable() throws Exception {
        ReferenceBlock b = FromConverter.DerivedTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "derived_table [ [ AS ] table_alias ] [ ( column_alias [,...n] ) ]");
    }

    @Test
    public void testJoinedTable() throws Exception {
        ReferenceBlock b = FromConverter.JoinedTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<joined_table> ::=\n" +
                        "<table_source> <join_type> <table_source> ON <search_condition>\n" +
                        "| <table_source> CROSS JOIN <table_source>\n" +
                        "| left_table_source { CROSS | OUTER } APPLY right_table_source\n" +
                        "| [ ( ] <joined_table> [ ) ]");
    }

    @Test
    public void testVariableTable() throws Exception {
        ReferenceBlock b = FromConverter.VariableTableConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "@variable [ [ AS ] table_alias ]");
    }


    @Test
    public void testJoinType() throws Exception {
        ReferenceBlock b = FromConverter.JoinTypeConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "[ { INNER | { { LEFT | RIGHT | FULL } [ OUTER ] } } [ <join_hint> ] ]\n" +
                        "JOIN");
    }



    private FromBuilderTest fromBuilderTest;

    @Before
    public void init(){
        fromBuilderTest = new FromBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        From from = fromBuilderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(from);

        String ok = "FROM Sales.SalesTerritory";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        From from = fromBuilderTest.exampleC;

        StringWriter writer = ReferenceBlockPrinter.print(from);

        String ok = "FROM HumanResources.Employee e" +
                " CROSS JOIN HumanResources.Department d";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

    @Test
    public void testPrintD() throws Exception {
        From from = fromBuilderTest.exampleD;

        StringWriter writer = ReferenceBlockPrinter.print(from);

        System.out.println(writer);

        String ok = "FROM Production.Product p" +
                " FULL OUTER JOIN HumanResources.Department p" +
                " ON p.ProductID = sod.ProductID";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

    @Test
    public void testPrintH() throws Exception {
        From from = fromBuilderTest.exampleH;

        StringWriter writer = ReferenceBlockPrinter.print(from);

        System.out.println(writer);

        String ok = "FROM Production.Product p" +
                " INNER MERGE JOIN Purchasing.ProductVendor pv" +
                " ON p.ProductID = pv.ProductID" +
                " INNER HASH JOIN Purchasing.Vendor v" +
                " ON pv.BusinessEntityID = v.BusinessEntityID";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

    @Test
    public void testPrintI() throws Exception {
        From from = fromBuilderTest.exampleI;

        StringWriter writer = ReferenceBlockPrinter.print(from);

        System.out.println(writer);

        //TODO
        String ok = "FROM Person.Person p" +
                " INNER JOIN HumanResources.Employee e" +
                " ON p.BusinessEntityID = e.BusinessEntityID" +
                " INNER JOIN (" +
                " ) d" +
                " ON p.BusinessEntityID = d.BusinessEntityID";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(writer.toString().replace(" ",""),
                ok);
    }

}