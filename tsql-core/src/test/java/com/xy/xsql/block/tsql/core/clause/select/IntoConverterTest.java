package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.IntoBuilderTest;
import com.xy.xsql.tsql.model.clause.select.Into;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class IntoConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = IntoConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INTO Clause> ::=\n" +
                        "INTO new_table [ ON filegroup ]");
    }

    private IntoBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new IntoBuilderTest();
    }

    @Test
    public void testPrintA() throws Exception {
        Into into = builderTest.exampleA;

        StringWriter writer = ReferenceBlockPrinter.print(into);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "INTO dbo.EmployeeAddresses";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

    @Test
    public void testPrintF() throws Exception {
        Into into = builderTest.exampleF;

        StringWriter writer = ReferenceBlockPrinter.print(into);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "INTO [dbo].[FactResellerSalesXL] ON FG2";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

}