package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.AppLockTestFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.AppLock_Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class AppLockTestConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = AppLockTestConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<APPLOCK_TEST> ::=\n" +
                        "APPLOCK_TEST ( 'database_principal' , 'resource_name' , 'lock_mode' , 'lock_owner' )");
    }

    private Map<AppLock_Test,String> model2StringMap;

    @Before
    public void init(){
        AppLockTestFunctionTest builderTest = new AppLockTestFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "APPLOCK_TEST('public', 'Form1', 'Shared', 'Transaction')");
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