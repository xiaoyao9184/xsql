package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.KeyIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.Key_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class KeyIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = KeyIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<Key_ID> ::=\n" +
                        "Key_ID ( 'Key_Name' )");
    }

    private Map<Key_Id,String> model2StringMap;

    @Before
    public void init(){
        KeyIdFunctionTest builderTest = new KeyIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "KEY_ID('ABerglundKey1')");
        model2StringMap.put(
                builderTest.exampleB,
                "KEY_ID('#ABerglundKey2')");
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