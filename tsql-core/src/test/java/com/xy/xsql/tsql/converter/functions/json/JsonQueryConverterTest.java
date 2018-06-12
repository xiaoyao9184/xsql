package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.json.JsonQueryFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.json.Json_Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class JsonQueryConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = JsonQueryConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<JSON_QUERY> ::=\n" +
                        "JSON_QUERY ( expression [ , path ] )");
    }

    private Map<Json_Query,String> model2StringMap;

    @Before
    public void init(){
        JsonQueryFunctionTest builderTest = new JsonQueryFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "JSON_QUERY(CustomFields,'$.OtherLanguages')");
        model2StringMap.put(
                builderTest.example11,
                "JSON_QUERY(Tags)");
        model2StringMap.put(
                builderTest.example12,
                "JSON_QUERY(CONCAT('[\"',ValidFrom,'\",\"',ValidTo,'\"]'))");
        model2StringMap.put(
                builderTest.example2,
                "JSON_QUERY(jsonContent, '$.address[0].longitude')");
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