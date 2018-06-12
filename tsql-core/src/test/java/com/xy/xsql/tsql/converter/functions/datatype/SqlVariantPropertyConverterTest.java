package com.xy.xsql.tsql.converter.functions.datatype;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.datatype.SqlVariantPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.datatype.Sql_Variant_Property;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class SqlVariantPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = SqlVariantPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<SQL_VARIANT_PROPERTY> ::=\n" +
                        "SQL_VARIANT_PROPERTY ( expression , property )");
    }

    private Map<Sql_Variant_Property,String> model2StringMap;

    @Before
    public void init(){
        SqlVariantPropertyFunctionTest builderTest = new SqlVariantPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "SQL_VARIANT_PROPERTY(colA,'BaseType')");
        model2StringMap.put(
                builderTest.exampleB,
                "SQL_VARIANT_PROPERTY(@v1, 'BaseType')");
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