package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datatype.IdentSeedFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Seed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IdentSeedConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IdentSeedConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IDENT_SEED> ::=\n" +
                        "IDENT_SEED ( 'table_or_view' )");
    }

    private Map<Ident_Seed,String> model2StringMap;

    @Before
    public void init(){
        IdentSeedFunctionTest builderTest = new IdentSeedFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "IDENT_SEED('Person.Address')");
        model2StringMap.put(
                builderTest.exampleB,
                "IDENT_SEED(TABLE_SCHEMA + '.' + TABLE_NAME)");
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