package com.xy.xsql.tsql.converter.functions.collation;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.collation.CollationPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.collation.CollationProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CollationPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CollationPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<COLLATIONPROPERTY> ::=\n" +
                        "COLLATIONPROPERTY ( collation_name , property )");
    }

    private Map<CollationProperty,String> model2StringMap;

    @Before
    public void init(){
        CollationPropertyFunctionTest builderTest = new CollationPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "COLLATIONPROPERTY('Traditional_Spanish_CS_AS_KS_WS', 'CodePage')");
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