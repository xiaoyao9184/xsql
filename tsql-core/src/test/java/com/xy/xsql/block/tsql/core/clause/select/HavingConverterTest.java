package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.HavingBuilderTest;
import com.xy.xsql.tsql.model.clause.select.Having;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class HavingConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = HavingConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<HAVING> ::=\n" +
                        "HAVING <search condition>");
    }


    private HavingBuilderTest builderTest;

    @Before
    public void init(){
        builderTest = new HavingBuilderTest();
    }

    @Test
    public void testPrint() throws Exception {
        Having having = builderTest.example;

        StringWriter writer = ReferenceBlockPrinter.print(having);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "HAVING SUM(LineTotal) > 100000.0";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

}