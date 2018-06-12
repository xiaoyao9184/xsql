package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.IsSrvRoleMemberFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.Is_SrvRoleMember;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class IsSrvRoleMemberConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IsSrvRoleMemberConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IS_SRVROLEMEMBER> ::=\n" +
                        "IS_SRVROLEMEMBER ( 'role' [ , 'login' ] )");
    }

    private Map<Is_SrvRoleMember,String> model2StringMap;

    @Before
    public void init(){
        IsSrvRoleMemberFunctionTest builderTest = new IsSrvRoleMemberFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "IS_SRVROLEMEMBER ('sysadmin')");
        model2StringMap.put(
                builderTest.example2,
                "IS_SRVROLEMEMBER('diskadmin', 'Contoso\\Pat')");
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