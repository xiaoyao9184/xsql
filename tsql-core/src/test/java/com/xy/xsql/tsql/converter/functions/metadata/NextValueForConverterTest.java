package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.NextValueForFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.NextValueFor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class NextValueForConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = NextValueForConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<NEXT VALUE FOR> ::=\n" +
                        "NEXT VALUE FOR [ database_name . ] [ schema_name . ] sequence_name\n" +
                        "\t[ OVER ( \n" +
                        "\t\t[ <PARTITION BY clause> ]\n" +
                        "\t\t[ <ORDER BY clause> ]\n" +
                        "\t\t[ <ROW or RANGE clause> ]\n" +
                        "\t ) ]");
    }

    private Map<NextValueFor,String> model2StringMap;

    @Before
    public void init(){
        NextValueForFunctionTest builderTest = new NextValueForFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "NEXT VALUE FOR Test.CountBy1");
        model2StringMap.put(
                builderTest.exampleC,
                "NEXT VALUE FOR Test.CountBy1 OVER (ORDER BY LastName)");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrint(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}