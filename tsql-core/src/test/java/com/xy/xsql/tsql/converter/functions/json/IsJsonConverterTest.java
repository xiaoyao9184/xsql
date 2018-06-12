package com.xy.xsql.tsql.converter.functions.json;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.json.IsJsonFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.json.IsJson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IsJsonConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IsJsonConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<ISJSON> ::=\n" +
                        "ISJSON ( expression )");
    }

    private Map<IsJson,String> model2StringMap;

    @Before
    public void init(){
        IsJsonFunctionTest builderTest = new IsJsonFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "ISJSON(@param)");
        model2StringMap.put(
                builderTest.example2,
                "ISJSON(json_col)");
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