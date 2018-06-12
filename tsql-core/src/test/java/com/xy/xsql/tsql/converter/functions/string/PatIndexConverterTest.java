package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.PatIndexFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.PatIndex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class PatIndexConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PatIndexConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PATINDEX> ::=\n" +
                        "PATINDEX ( '%pattern%' , expression )");
    }

    private Map<PatIndex,String> model2StringMap;

    @Before
    public void init(){
        PatIndexFunctionTest builderTest = new PatIndexFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "PATINDEX('%ter%', 'interesting data')");
        model2StringMap.put(
                builderTest.exampleB,
                "PATINDEX('%ensure%',DocumentSummary)");
        model2StringMap.put(
                builderTest.exampleC,
                "PATINDEX('%en_ure%', 'please ensure the door is locked')");
//        model2StringMap.put(
//                builderTest.exampleD,
//                "PATINDEX ( '%ein%', 'Das ist ein Test'  COLLATE Latin1_General_BIN)");
        model2StringMap.put(
                builderTest.exampleE,
                "PATINDEX('%' + @MyValue + '%', DocumentSummary)");
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