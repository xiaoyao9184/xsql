package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.OptionBuilderTest;
import com.xy.xsql.tsql.model.clause.Option;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OptionConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = OptionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPTION Clause> ::=\n" +
                        "OPTION ( <query_hint> [,...n] )");
    }

    @Test
    public void testOptionQueryOption() throws Exception {
        ReferenceBlock b = OptionConverter.QueryOptionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_option> ::=\n" +
                        "LABEL = label_name\n" +
                        "| <query_hint>");
    }

    @Test
    public void testOptionLabelQueryOption() throws Exception {
        ReferenceBlock b = OptionConverter.LabelQueryOptionConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "LABEL = label_name");
    }



    private OptionBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new OptionBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        Option option = builderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION (HASH GROUP, FAST 10)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintB() throws Exception {
        Option option = builderTest.exampleB;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION ( LABEL = 'q17' )";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintC() throws Exception {
        Option option = builderTest.exampleC;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION (HASH JOIN)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintD() throws Exception {
        Option option = builderTest.exampleD;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION ( LABEL = 'CustJoin', HASH JOIN, MERGE JOIN)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintE() throws Exception {
        Option option = builderTest.exampleE;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION (HASH JOIN)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintF() throws Exception {
        Option option = builderTest.exampleF;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION (HASH JOIN)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintG() throws Exception {
        Option option = builderTest.exampleG;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION ( FORCE ORDER )";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintH1() throws Exception {
        Option option = builderTest.exampleH1;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION (FORCE EXTERNALPUSHDOWN)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintH2() throws Exception {
        Option option = builderTest.exampleH2;

        StringWriter writer = ReferenceBlockPrinter.print(option);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "OPTION (DISABLE EXTERNALPUSHDOWN)";
        ok = ok.replaceAll(" ","")
                .replaceAll("\n","");
        Assert.assertEquals(
                check,
                ok);
    }

}