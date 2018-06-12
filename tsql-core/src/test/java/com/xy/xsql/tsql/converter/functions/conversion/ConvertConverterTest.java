package com.xy.xsql.tsql.converter.functions.conversion;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.conversion.ConvertFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.conversion.Convert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ConvertConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ConvertConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CONVERT> ::=\n" +
                        "CONVERT ( data_type [ ( length ) ] , expression [ , style ] )");
    }

    private Map<Convert,String> model2StringMap;

    @Before
    public void init(){
        ConvertFunctionTest builderTest = new ConvertFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CONVERT(int, ListPrice)");
        model2StringMap.put(
                builderTest.exampleF1,
                "CONVERT(xml, '<root><child/></root>')");
        model2StringMap.put(
                builderTest.exampleF2,
                "CONVERT(xml, '<root>          <child/>         </root>', 1)");
        model2StringMap.put(
                builderTest.exampleG1,
                "CONVERT(nvarchar(30), GETDATE(), 126)");
        model2StringMap.put(
                builderTest.exampleG2,
                "CONVERT(datetime, '2006-04-25T15:50:59.997', 126)");
        model2StringMap.put(
                builderTest.exampleH1,
                "CONVERT(char(8), 0x4E616D65, 0)");
        model2StringMap.put(
                builderTest.exampleH2,
                "CONVERT(binary(8), 'Name', 0)");
        model2StringMap.put(
                builderTest.exampleH3,
                "CONVERT(binary(4), '4E616D65', 2)");
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