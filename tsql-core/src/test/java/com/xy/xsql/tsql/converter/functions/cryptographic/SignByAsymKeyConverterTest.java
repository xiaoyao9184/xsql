package com.xy.xsql.tsql.converter.functions.cryptographic;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.cryptographic.SignByAsymKeyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.cryptographic.SignByAsymKey;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class SignByAsymKeyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SignByAsymKeyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SignByAsymKey> ::=\n" +
                        "SignByAsymKey ( Asym_Key_ID , @plaintext [ , 'password' ] )");
    }

    private Map<SignByAsymKey,String> model2StringMap;

    @Before
    public void init(){
        SignByAsymKeyFunctionTest builderTest = new SignByAsymKeyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "SignByAsymKey( AsymKey_Id( 'PrimeKey' ),\n" +
                        "     @clear_text_data, N'pGFD4bb925DGvbd2439587y' )");
    }

    @Test
    public void testPrint() throws Exception {
        BaseTest.testPrintIgnoreCase(model2StringMap);
    }

    @Test
    public void testKeywordPrint() throws Exception {
        BaseTest.testKeywordPrintIgnoreCase(model2StringMap);
    }

}