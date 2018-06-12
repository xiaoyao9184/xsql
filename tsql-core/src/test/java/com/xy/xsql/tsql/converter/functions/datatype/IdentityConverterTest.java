package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datatype.IdentityFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datatype.Identity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class IdentityConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IdentityConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<IDENTITY> ::=\n" +
                        "IDENTITY ( data_type [ , seed , increment ] ) AS column_name");
    }

    private Map<Identity,String> model2StringMap;

    @Before
    public void init(){
        IdentityFunctionTest builderTest = new IdentityFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "IDENTITY(smallint, 100, 1) AS ContactNum");
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