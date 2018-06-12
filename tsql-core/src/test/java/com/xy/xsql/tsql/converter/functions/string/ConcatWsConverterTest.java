package com.xy.xsql.tsql.converter.functions.string;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.string.ConcatWsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.string.Concat_Ws;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class ConcatWsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ConcatWsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CONCAT_WS> ::=\n" +
                        "CONCAT_WS ( separator , argument [,...n] )");
    }

    private Map<Concat_Ws,String> model2StringMap;

    @Before
    public void init(){
        ConcatWsFunctionTest builderTest = new ConcatWsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "CONCAT_WS( ' - ', database_id, recovery_model_desc, containment_desc)");
        model2StringMap.put(
                builderTest.exampleB,
                "CONCAT_WS(',','1 Microsoft Way', NULL, NULL, 'Redmond', 'WA', 98052)");
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