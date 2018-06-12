package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.rowset.OpenQueryFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.rowset.OpenQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenQueryConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenQueryConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENQUERY> ::=\n" +
                        "OPENQUERY ( linked_server , 'query' )");
    }

    private Map<OpenQuery,String> model2StringMap;

    @Before
    public void init(){
        OpenQueryFunctionTest builderTest = new OpenQueryFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles WHERE id = 101') ");
        model2StringMap.put(
                builderTest.exampleB,
                "OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles')");
        model2StringMap.put(
                builderTest.exampleC,
                "OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles WHERE name = ''NewTitle''')");
        model2StringMap.put(
                builderTest.exampleD,
                "OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles WHERE name = ''NewTitle''')");
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