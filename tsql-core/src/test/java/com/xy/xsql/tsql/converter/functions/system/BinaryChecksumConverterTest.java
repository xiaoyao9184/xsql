package com.xy.xsql.tsql.converter.functions.system;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.system.BinaryChecksumFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.system.Binary_Checksum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class BinaryChecksumConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = BinaryChecksumConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<BINARY_CHECKSUM> ::=\n" +
                        "BINARY_CHECKSUM ( * | expression [,...n] )");
    }

    private Map<Binary_Checksum,String> model2StringMap;

    @Before
    public void init(){
        BinaryChecksumFunctionTest builderTest = new BinaryChecksumFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "BINARY_CHECKSUM(*)");
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