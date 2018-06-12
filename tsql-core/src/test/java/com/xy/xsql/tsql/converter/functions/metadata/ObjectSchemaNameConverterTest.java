package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ObjectSchemaNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Object_Schema_Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectSchemaNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectSchemaNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OBJECT_SCHEMA_NAME> ::=\n" +
                        "OBJECT_SCHEMA_NAME ( object_id [ , database_id ] )");
    }

    private Map<Object_Schema_Name,String> model2StringMap;

    @Before
    public void init(){
        ObjectSchemaNameFunctionTest builderTest = new ObjectSchemaNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OBJECT_SCHEMA_NAME(st.objectid, st.dbid)");
        model2StringMap.put(
                builderTest.exampleB,
                "OBJECT_SCHEMA_NAME(object_id, database_id)");
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