package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.TranslateFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Translate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TranslateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TranslateConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TRANSLATE> ::=\n" +
                        "TRANSLATE ( inputString , characters , translations )");
    }

    private Map<Translate,String> model2StringMap;

    @Before
    public void init(){
        TranslateFunctionTest builderTest = new TranslateFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "TRANSLATE('2*[3+4]/{7-2}', '[]{}', '()()')");
        model2StringMap.put(
                builderTest.exampleB1,
                "TRANSLATE('[137.4, 72.3]' , '[,]', '( )')");
        model2StringMap.put(
                builderTest.exampleB2,
                "TRANSLATE('(137.4 72.3)' , '( )', '[,]')");
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