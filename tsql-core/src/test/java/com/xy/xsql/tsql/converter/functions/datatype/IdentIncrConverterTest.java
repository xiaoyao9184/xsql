package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datatype.IdentIncrFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Incr;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IdentIncrConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IdentIncrConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IDENT_INCR> ::=\n" +
                        "IDENT_INCR ( 'table_or_view' )");
    }

    private Map<Ident_Incr,String> model2StringMap;

    @Before
    public void init(){
        IdentIncrFunctionTest builderTest = new IdentIncrFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "IDENT_INCR('Person.Address')");
        model2StringMap.put(
                builderTest.exampleB,
                "IDENT_INCR(TABLE_SCHEMA + '.' + TABLE_NAME)");
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