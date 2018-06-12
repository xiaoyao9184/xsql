package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ObjectDefinitionFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Object_Definition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectDefinitionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectDefinitionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OBJECT_DEFINITION> ::=\n" +
                        "OBJECT_DEFINITION ( object_id )");
    }

    private Map<Object_Definition,String> model2StringMap;

    @Before
    public void init(){
        ObjectDefinitionFunctionTest builderTest = new ObjectDefinitionFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OBJECT_DEFINITION (OBJECT_ID(N'Person.uAddress'))");
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