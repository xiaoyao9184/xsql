package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.json.JsonValueFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.json.Json_Value;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class JsonValueConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = JsonValueConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<JSON_VALUE> ::=\n" +
                        "JSON_VALUE ( expression , path )");
    }

    private Map<Json_Value,String> model2StringMap;

    @Before
    public void init(){
        JsonValueFunctionTest builderTest = new JsonValueFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "JSON_VALUE(jsonInfo,'$.info.address[0].town')");
        model2StringMap.put(
                builderTest.example11,
                "JSON_VALUE(jsonInfo,'$.info.address[0].state')");
        model2StringMap.put(
                builderTest.example2,
                "JSON_VALUE(jsonContent, '$.address[0].longitude')");
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