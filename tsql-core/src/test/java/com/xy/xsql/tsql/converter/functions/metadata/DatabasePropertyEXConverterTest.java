package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.DatabasePropertyEXFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.DatabasePropertyEX;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DatabasePropertyEXConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DatabasePropertyEXConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATABASEPROPERTYEX> ::=\n" +
                        "DATABASEPROPERTYEX ( database , property )");
    }

    private Map<DatabasePropertyEX,String> model2StringMap;

    @Before
    public void init(){
        DatabasePropertyEXFunctionTest builderTest = new DatabasePropertyEXFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleB,
                "DATABASEPROPERTYEX('AdventureWorks2014', 'IsAutoShrink')");
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