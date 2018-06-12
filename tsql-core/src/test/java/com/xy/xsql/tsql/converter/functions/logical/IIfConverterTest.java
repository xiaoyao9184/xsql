package com.xy.xsql.tsql.converter.functions.logical;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.logical.IIfFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.logical.IIf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IIfConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IIfConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IIF> ::=\n" +
                        "IIF ( boolean_expression , true_value , false_value )");
    }

    private Map<IIf,String> model2StringMap;

    @Before
    public void init(){
        IIfFunctionTest builderTest = new IIfFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "IIF ( @a > @b, 'TRUE', 'FALSE' )");
        model2StringMap.put(
                builderTest.exampleB,
                "IIF ( 45 > 30, NULL, NULL )");
        model2StringMap.put(
                builderTest.exampleC,
                "IIF ( 45 > 30, @p, @s )");
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