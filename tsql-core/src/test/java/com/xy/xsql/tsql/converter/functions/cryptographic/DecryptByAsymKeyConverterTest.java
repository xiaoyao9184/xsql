package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.DecryptByAsymKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.DecryptByAsymKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class DecryptByAsymKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DecryptByAsymKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DecryptByAsymKey> ::=\n" +
                        "DecryptByAsymKey ( Asym_Key_ID , { 'ciphertext' | @ciphertext } [ , 'Asym_Key_Password' ] )");
    }

    private Map<DecryptByAsymKey,String> model2StringMap;

    @Before
    public void init(){
        DecryptByAsymKeyFunctionTest builderTest = new DecryptByAsymKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "DecryptByAsymKey( ASYMKEY_ID('JanainaAsymKey02'),\n" +
                        "     ProtectedData, N'pGFD4bb925DGvbd2439587y' )");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrintIgnoreCase(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrint(model2StringMap);
    }

}