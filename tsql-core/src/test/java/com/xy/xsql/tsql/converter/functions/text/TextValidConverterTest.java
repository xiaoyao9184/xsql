package com.xy.xsql.tsql.converter.functions.text;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.text.TextValidFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.text.TextValid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class TextValidConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TextValidConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TEXTVALID> ::=\n" +
                        "TEXTVALID ( 'table.column' , text_ptr )");
    }

    private Map<TextValid,String> model2StringMap;

    @Before
    public void init(){
        TextValidFunctionTest builderTest = new TextValidFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "TEXTVALID ('pub_info.logo', TEXTPTR(logo))");
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