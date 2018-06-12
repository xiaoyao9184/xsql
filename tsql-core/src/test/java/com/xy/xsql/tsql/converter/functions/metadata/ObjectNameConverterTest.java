package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ObjectNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Object_Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OBJECT_NAME> ::=\n" +
                        "OBJECT_NAME ( object_id [ , database_id ] )");
    }

    private Map<Object_Name,String> model2StringMap;

    @Before
    public void init(){
        ObjectNameFunctionTest builderTest = new ObjectNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OBJECT_NAME(@MyID)");
        model2StringMap.put(
                builderTest.exampleB,
                "OBJECT_NAME(st.objectid, st.dbid)");
        model2StringMap.put(
                builderTest.exampleD,
                "OBJECT_NAME(274100017)");
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