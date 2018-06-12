package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.AppLockModeFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.AppLock_Mode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class AppLockModeConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AppLockModeConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<APPLOCK_MODE> ::=\n" +
                        "APPLOCK_MODE ( 'database_principal' , 'resource_name' , 'lock_owner' )");
    }

    private Map<AppLock_Mode,String> model2StringMap;

    @Before
    public void init(){
        AppLockModeFunctionTest builderTest = new AppLockModeFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "APPLOCK_MODE('public', 'Form1', 'Transaction')");
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