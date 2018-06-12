package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.ConcatFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Concat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ConcatConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ConcatConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CONCAT> ::=\n" +
                        "CONCAT ( string_value [,...n] )");
    }

    private Map<Concat,String> model2StringMap;

    @Before
    public void init(){
        ConcatFunctionTest builderTest = new ConcatFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CONCAT ( 'Happy ', 'Birthday ', 11, '/', '25' )");
        model2StringMap.put(
                builderTest.exampleB,
                "CONCAT( emp_name, emp_middlename, emp_lastname )");
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