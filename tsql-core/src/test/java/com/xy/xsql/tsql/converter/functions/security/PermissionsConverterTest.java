package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.PermissionsFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.Permissions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class PermissionsConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = PermissionsConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<PERMISSIONS> ::=\n" +
                        "PERMISSIONS ( [ objectid [ 'column' ] ] )");
    }

    private Map<Permissions,String> model2StringMap;

    @Before
    public void init(){
        PermissionsFunctionTest builderTest = new PermissionsFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "PERMISSIONS()");
        model2StringMap.put(
                builderTest.exampleB,
                "PERMISSIONS(OBJECT_ID('AdventureWorks2012.Person.Address','U'))");
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