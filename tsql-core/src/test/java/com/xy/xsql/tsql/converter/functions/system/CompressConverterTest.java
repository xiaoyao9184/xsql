package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.system.CompressFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.system.Compress;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class CompressConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CompressConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COMPRESS> ::=\n" +
                        "COMPRESS ( expression )");
    }

    private Map<Compress,String> model2StringMap;

    @Before
    public void init(){
        CompressFunctionTest builderTest = new CompressFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "COMPRESS(N'{\"sport\":\"Tennis\",\"age\": 28,\"rank\":1,\"points\":15258, turn\":17}')");
        model2StringMap.put(
                builderTest.exampleB,
                "COMPRESS(info)");
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