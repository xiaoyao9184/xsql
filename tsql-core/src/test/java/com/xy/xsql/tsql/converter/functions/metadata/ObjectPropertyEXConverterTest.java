package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ObjectPropertyExFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.ObjectPropertyEX;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectPropertyEXConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectPropertyEXConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OBJECTPROPERTYEX> ::=\n" +
                        "OBJECTPROPERTYEX ( id , property )");
    }

    private Map<ObjectPropertyEX,String> model2StringMap;

    @Before
    public void init(){
        ObjectPropertyExFunctionTest builderTest = new ObjectPropertyExFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OBJECTPROPERTYEX ( object_id(N'MyEmployeeTable'), N'BaseType')");
        model2StringMap.put(
                builderTest.exampleB,
                "OBJECTPROPERTYEX(OBJECT_ID(N'HumanResources.Employee'), N'TABLEUPDATETRIGGERCOUNT')");
        model2StringMap.put(
                builderTest.exampleC,
                "OBJECTPROPERTYEX(object_id, N'TableHasForeignKey')");
        model2StringMap.put(
                builderTest.exampleD,
                "OBJECTPROPERTYEX ( object_id(N'dbo.DimReseller'), N'BaseType')");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrintIgnoreCase(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrintIgnoreCase(model2StringMap);
    }

}