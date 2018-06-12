package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ObjectPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.ObjectProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OBJECTPROPERTY> ::=\n" +
                        "OBJECTPROPERTY ( id , property )");
    }

    private Map<ObjectProperty,String> model2StringMap;

    @Before
    public void init(){
        ObjectPropertyFunctionTest builderTest = new ObjectPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OBJECTPROPERTY (OBJECT_ID(N'Production.UnitMeasure'),'ISTABLE')");
        model2StringMap.put(
                builderTest.exampleB,
                "OBJECTPROPERTY(OBJECT_ID('dbo.ufnGetProductDealerPrice'), 'IsDeterministic')");
        model2StringMap.put(
                builderTest.exampleC,
                "OBJECTPROPERTY(object_id, N'SchemaId')");
        model2StringMap.put(
                builderTest.exampleD,
                "OBJECTPROPERTY (OBJECT_ID(N'dbo.DimReseller'),'ISTABLE')");
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