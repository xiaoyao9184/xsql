package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.OptionBuilderTest;
import com.xy.xsql.tsql.core.clause.OutputBuilderTest;
import com.xy.xsql.tsql.model.clause.Option;
import com.xy.xsql.tsql.model.clause.Output;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.lang.management.GarbageCollectorMXBean;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OutputConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OutputConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OUTPUT Clause> ::=\n" +
                        "[ OUTPUT <dml_select_list> INTO { @table_variable | output_table } [ ( column_list ) ] ]\n" +
                        "[ OUTPUT <dml_select_list> ]");
    }

    @Test
    public void testDmlSelectList() throws Exception {
        ReferenceBlock b = OutputConverter.DmlSelectListConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<dml_select_list> ::=\n" +
                        "{ <column_name> | scalar_expression } [ [ AS ] column_alias_identifier ] [,...n]");
    }

    @Test
    public void testDmlSelect() throws Exception {
        ReferenceBlock b = OutputConverter.DmlSelectConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "{ <column_name> | scalar_expression } [ [ AS ] column_alias_identifier ]");
    }

    @Test
    public void testColumnName() throws Exception {
        ReferenceBlock b = OutputConverter.ColumnNameConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<column_name> ::=\n" +
                        "{ DELETED | INSERTED | from_table_name } . { * | column_name }\n" +
                        "| $action");
    }



    private OutputBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new OutputBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        Output output = builderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT INSERTED.ScrapReasonID, INSERTED.Name, INSERTED.ModifiedDate\n" +
                "     INTO @MyTableVar";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        Output output = builderTest.exampleB;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT DELETED.*";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        Output output = builderTest.exampleC;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT INSERTED.BusinessEntityID,\n" +
                "     DELETED.VacationHours,\n" +
                "     INSERTED.VacationHours,\n" +
                "     INSERTED.ModifiedDate";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintD() throws Exception {
        Output output = builderTest.exampleD;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT INSERTED.BusinessEntityID,\n" +
                "     DELETED.VacationHours,\n" +
                "     INSERTED.VacationHours,\n" +
                "     INSERTED.VacationHours - DELETED.VacationHours,\n" +
                "     INSERTED.ModifiedDate";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintE() throws Exception {
        Output output = builderTest.exampleE;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT DELETED.ScrapReasonID,\n" +
                "     INSERTED.ScrapReasonID,\n" +
                "     INSERTED.WorkOrderID,\n" +
                "     INSERTED.ProductID,\n" +
                "     p.Name\n" +
                "     INTO @MyTestVar";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintF() throws Exception {
        Output output = builderTest.exampleF;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT DELETED.ProductID,\n" +
                "     p.Name,\n" +
                "     p.ProductModelID,\n" +
                "     DELETED.ProductPhotoID\n" +
                "     INTO @MyTableVar";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintG() throws Exception {
        Output output = builderTest.exampleG;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT DELETED.DocumentSummary,\n" +
                "     INSERTED.DocumentSummary\n" +
                "     INTO @MyTableVar";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintH() throws Exception {
        Output output = builderTest.exampleH;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT INSERTED.ScrapReasonID, INSERTED.Name,\n" +
                "     INSERTED.ModifiedDate";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintI() throws Exception {
        Output output = builderTest.exampleI;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT INSERTED.LastName,\n" +
                "     INSERTED.FirstName,\n" +
                "     INSERTED.CurrentSales";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintJ() throws Exception {
        Output output = builderTest.exampleJ;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT DELETED.ProductID,\n" +
                "     p.Name,\n" +
                "     p.ProductModelID,\n" +
                "     DELETED.ProductPhotoID\n" +
                "     INTO @MyTableVar\n" +
                "     OUTPUT DELETED.ProductID, DELETED.ProductPhotoID, GETDATE() DeletedDate";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintK() throws Exception {
        Output output = builderTest.exampleK;

        StringWriter writer = ReferenceBlockPrinter.print(output);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OUTPUT $action, DELETED.ProductID";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

}