package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.HasPermsByNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.Has_Perms_By_Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class HasPermsByNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = HasPermsByNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<HAS_PERMS_BY_NAME> ::=\n" +
                        "HAS_PERMS_BY_NAME ( securable , securable_class , permission [ , sub-securable ] [ , sub-securable_class ] )");
    }

    private Map<Has_Perms_By_Name,String> model2StringMap;

    @Before
    public void init(){
        HasPermsByNameFunctionTest builderTest = new HasPermsByNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "HAS_PERMS_BY_NAME(null, null, 'VIEW SERVER STATE')");
        model2StringMap.put(
                builderTest.exampleB,
                "HAS_PERMS_BY_NAME('Ps', 'LOGIN', 'IMPERSONATE')");
        model2StringMap.put(
                builderTest.exampleC,
                "HAS_PERMS_BY_NAME(db_name(), 'DATABASE', 'ANY')");
        model2StringMap.put(
                builderTest.exampleG,
                "HAS_PERMS_BY_NAME('Sales.SalesPerson', 'OBJECT', 'INSERT')");
        model2StringMap.put(
                builderTest.exampleH,
                "HAS_PERMS_BY_NAME('T', 'OBJECT', 'SELECT', name, 'COLUMN')");
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