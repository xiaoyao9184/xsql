package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.HashBytesFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.HashBytes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class HashBytesConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = HashBytesConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<HASHBYTES> ::=\n" +
                        "HASHBYTES ( '<algorithm>' , { 'input' | @input } )");
    }

    private Map<HashBytes,String> model2StringMap;

    @Before
    public void init(){
        HashBytesFunctionTest builderTest = new HashBytesFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "HASHBYTES('SHA1', @HashThis)");
        model2StringMap.put(
                builderTest.exampleB,
                "HASHBYTES('SHA1', c1)");
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