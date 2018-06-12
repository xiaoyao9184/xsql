package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.SchemaIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class SchemaIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SchemaIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SCHEMA_ID> ::=\n" +
                        "SCHEMA_ID ( [ schema_name ] )");
    }

    private Map<Schema_Id,String> model2StringMap;

    @Before
    public void init(){
        SchemaIdFunctionTest builderTest = new SchemaIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SCHEMA_ID()");
        model2StringMap.put(
                builderTest.example2,
                "SCHEMA_ID('dbo')");
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