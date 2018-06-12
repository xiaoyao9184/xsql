package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datatype.IdentCurrentFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Current;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IdentCurrentConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IdentCurrentConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IDENT_CURRENT> ::=\n" +
                        "IDENT_CURRENT ( 'table_name' )");
    }

    private Map<Ident_Current,String> model2StringMap;

    @Before
    public void init(){
        IdentCurrentFunctionTest builderTest = new IdentCurrentFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "IDENT_CURRENT ('Person.Address')");
        model2StringMap.put(
                builderTest.exampleB,
                "IDENT_CURRENT('t7')");
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