package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.SoundexFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Soundex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class SoundexConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SoundexConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SOUNDEX> ::=\n" +
                        "SOUNDEX ( character_expression )");
    }

    private Map<Soundex,String> model2StringMap;

    @Before
    public void init(){
        SoundexFunctionTest builderTest = new SoundexFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SOUNDEX ('Smith')");
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