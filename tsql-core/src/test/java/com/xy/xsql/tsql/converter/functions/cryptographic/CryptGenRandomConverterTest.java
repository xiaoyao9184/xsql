package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.CryptGenRandomFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.Crypt_Gen_Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class CryptGenRandomConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = CryptGenRandomConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CRYPT_GEN_RANDOM> ::=\n" +
                        "CRYPT_GEN_RANDOM ( length [ , seed ] )");
    }

    private Map<Crypt_Gen_Random,String> model2StringMap;

    @Before
    public void init(){
        CryptGenRandomFunctionTest builderTest = new CryptGenRandomFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "CRYPT_GEN_RANDOM(50)");
        model2StringMap.put(
                builderTest.example2,
                "CRYPT_GEN_RANDOM(4, 0x25F18060)");
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