package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.system.ChecksumFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.system.Checksum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class ChecksumConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ChecksumConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CHECKSUM> ::=\n" +
                        "CHECKSUM ( * | expression [,...n] )");
    }

    private Map<Checksum,String> model2StringMap;

    @Before
    public void init(){
        ChecksumFunctionTest builderTest = new ChecksumFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CHECKSUM(Name)");
        model2StringMap.put(
                builderTest.example2,
                "CHECKSUM(N'Bearing Ball')");
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