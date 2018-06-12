package com.xy.xsql.tsql.converter.functions.datetime;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datetime.Datetime2FromPartsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datetime.Datetime2FromParts;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class Datetime2FromPartsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = Datetime2FromPartsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATETIME2FROMPARTS> ::=\n" +
                        "DATETIME2FROMPARTS ( year , month , day , hour , minute , seconds , fractions , precision )");
    }

    private Map<Datetime2FromParts,String> model2StringMap;

    @Before
    public void init(){
        Datetime2FromPartsFunctionTest builderTest = new Datetime2FromPartsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DATETIME2FROMPARTS ( 2010, 12, 31, 23, 59, 59, 0, 0 )");
        model2StringMap.put(
                builderTest.exampleB,
                "DATETIME2FROMPARTS ( 2011, 8, 15, 14, 23, 44, 5, 1 )");
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