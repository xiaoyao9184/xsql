package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.rowset.OpenXmlFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.rowset.OpenXml;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenXmlConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenXmlConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENXML> ::=\n" +
                        "OPENXML ( idoc , rowpattern [ , flags ] )\n" +
                        "\t[ WITH ( <SchemaDeclaration> | TableName ) ]");
    }

    private Map<OpenXml,String> model2StringMap;

    @Before
    public void init(){
        OpenXmlFunctionTest builderTest = new OpenXmlFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OPENXML (@idoc, '/ROOT/Customer',1)");
        model2StringMap.put(
                builderTest.exampleB,
                "OPENXML (@idoc, '/ROOT/Customer/Order/OrderDetail',2)");
        model2StringMap.put(
                builderTest.exampleC,
                "OPENXML (@idoc, '/ROOT/Customers')");
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