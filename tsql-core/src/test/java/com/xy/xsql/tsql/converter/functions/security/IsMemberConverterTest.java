package com.xy.xsql.tsql.converter.functions.security;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.security.IsMemberFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.security.Is_Member;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class IsMemberConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IsMemberConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IS_MEMBER> ::=\n" +
                        "IS_MEMBER ( { 'group' | 'role' } )");
    }

    private Map<Is_Member,String> model2StringMap;

    @Before
    public void init(){
        IsMemberFunctionTest builderTest = new IsMemberFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "IS_MEMBER ('db_owner')");
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