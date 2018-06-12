package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.conversion.CastFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.conversion.Cast;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CastConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CastConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CAST> ::=\n" +
                        "CAST ( expression AS data_type [ ( length ) ] )");
    }

    private Map<Cast,String> model2StringMap;

    @Before
    public void init(){
        CastFunctionTest builderTest = new CastFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CAST(ListPrice AS int)");
        model2StringMap.put(
                builderTest.exampleB,
                "CAST(ROUND(SalesYTD/CommissionPCT, 0) AS int)");
        model2StringMap.put(
                builderTest.exampleC,
                "CAST(ListPrice AS varchar(12))");
        model2StringMap.put(
                builderTest.exampleD,
                "CAST(EnglishProductName AS char(10))");
        model2StringMap.put(
                builderTest.exampleE,
                "CAST(CAST(s.SalesYTD AS int) AS char(20))");
        model2StringMap.put(
                builderTest.exampleF,
                "CAST('<Name><FName>Carol</FName><LName>Elliot</LName></Name>'  AS xml)");
        model2StringMap.put(
                builderTest.exampleG1,
                "CAST(GETDATE() AS nvarchar(30))");
        model2StringMap.put(
                builderTest.exampleG2,
                "CAST('2006-04-25T15:50:59.997' AS datetime)");
        model2StringMap.put(
                builderTest.exampleH,
                "CAST (@d1 AS datetime)");
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