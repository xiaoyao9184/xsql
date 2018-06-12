package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ColLengthFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Col_Length;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ColLengthConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ColLengthConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COL_LENGTH> ::=\n" +
                        "COL_LENGTH ( 'table' , 'column' )");
    }

    private Map<Col_Length,String> model2StringMap;

    @Before
    public void init(){
        ColLengthFunctionTest builderTest = new ColLengthFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "COL_LENGTH('t1','c1')");
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