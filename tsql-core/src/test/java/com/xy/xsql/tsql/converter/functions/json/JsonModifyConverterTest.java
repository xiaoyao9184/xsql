package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.json.JsonModifyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.json.Json_Modify;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class JsonModifyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = JsonModifyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<JSON_MODIFY> ::=\n" +
                        "JSON_MODIFY ( expression , path , newValue )");
    }

    private Map<Json_Modify,String> model2StringMap;

    @Before
    public void init(){
        JsonModifyFunctionTest builderTest = new JsonModifyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "JSON_MODIFY(@info,'$.name','Mike')");
        model2StringMap.put(
                builderTest.example3,
                "JSON_MODIFY(@info,'$.name',NULL)");
        model2StringMap.put(
                builderTest.example4,
                "JSON_MODIFY(JSON_MODIFY(JSON_MODIFY(@info,'$.name','Mike'),'$.surname','Smith'),'append $.skills','Azure')");
        model2StringMap.put(
                builderTest.example5,
                "JSON_MODIFY(\n" +
                        "     JSON_MODIFY(@product,'$.Price',CAST(JSON_VALUE(@product,'$.price') AS NUMERIC(4,2))),\n" +
                        "     '$.price',\n" +
                        "     NULL\n" +
                        "     )");
        model2StringMap.put(
                builderTest.example6,
                "JSON_MODIFY(@stats,'$.click_count',\n" +
                        "     CAST(JSON_VALUE(@stats,'$.click_count') AS INT)+1)");
        model2StringMap.put(
                builderTest.example7,
                "JSON_MODIFY(@info,'$.skills','[\"C#\",\"T-SQL\",\"Azure\"]')");
        model2StringMap.put(
                builderTest.example8,
                "JSON_MODIFY(@info,'$.skills',JSON_QUERY('[\"C#\",\"T-SQL\",\"Azure\"]'))");
        model2StringMap.put(
                builderTest.example9,
                "JSON_MODIFY(jsonCol,'$.info.address.town','London')");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrintIgnoreCase(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrintIgnoreCase(model2StringMap);
    }

}