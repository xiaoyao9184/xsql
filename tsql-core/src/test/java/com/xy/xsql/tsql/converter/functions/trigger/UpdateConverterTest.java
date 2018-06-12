package com.xy.xsql.tsql.converter.functions.trigger;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.trigger.UpdateFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.trigger.Update;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class UpdateConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = UpdateConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<UPDATE> ::=\n" +
                        "UPDATE ( column )");
    }

    private Map<Update,String> model2StringMap;

    @Before
    public void init(){
        UpdateFunctionTest builderTest = new UpdateFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "UPDATE (StateProvinceID)");
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