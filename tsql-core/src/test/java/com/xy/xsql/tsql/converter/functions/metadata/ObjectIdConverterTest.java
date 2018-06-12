package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.ObjectIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ObjectIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ObjectIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OBJECT_ID> ::=\n" +
                        "OBJECT_ID ( [ database_name . [ schema_name ] . | schema_name . ] object_name [ , 'object_type' ] )");
    }

    private Map<Object_Id,String> model2StringMap;

    @Before
    public void init(){
        ObjectIdFunctionTest builderTest = new ObjectIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OBJECT_ID(N'AdventureWorks2012.Production.WorkOrder')");
        model2StringMap.put(
                builderTest.exampleB,
                "OBJECT_ID (N'dbo.AWBuildVersion', N'U')");
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