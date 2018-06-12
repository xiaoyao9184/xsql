package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.RTrimFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.RTrim;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class RTrimConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = RTrimConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<RTRIM> ::=\n" +
                        "RTRIM ( character_expression )");
    }

    private Map<RTrim,String> model2StringMap;

    @Before
    public void init(){
        RTrimFunctionTest builderTest = new RTrimFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "RTRIM('Removes trailing spaces.   ')");
        model2StringMap.put(
                builderTest.exampleB,
                "RTRIM('Four spaces are after the period in this sentence.    ')");
        model2StringMap.put(
                builderTest.exampleC,
                "RTRIM(@string_to_trim)");
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