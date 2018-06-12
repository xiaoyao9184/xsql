package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.system.GetAnsiNullFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.system.GetAnsiNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class GetAnsiNullConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = GetAnsiNullConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<GETANSINULL> ::=\n" +
                        "GETANSINULL ( [ 'database' ] )");
    }

    private Map<GetAnsiNull,String> model2StringMap;

    @Before
    public void init(){
        GetAnsiNullFunctionTest builderTest = new GetAnsiNullFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "GETANSINULL('AdventureWorks2012')");
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