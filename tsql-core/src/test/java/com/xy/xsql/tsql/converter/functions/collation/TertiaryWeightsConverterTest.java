package com.xy.xsql.tsql.converter.functions.collation;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.collation.TertiaryWeightsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.collation.Tertiary_Weights;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TertiaryWeightsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TertiaryWeightsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TERTIARY_WEIGHTS> ::=\n" +
                        "TERTIARY_WEIGHTS ( non_Unicode_character_string_expression )");
    }

    private Map<Tertiary_Weights,String> model2StringMap;

    @Before
    public void init(){
        TertiaryWeightsFunctionTest builderTest = new TertiaryWeightsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "TERTIARY_WEIGHTS(Col1)");
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