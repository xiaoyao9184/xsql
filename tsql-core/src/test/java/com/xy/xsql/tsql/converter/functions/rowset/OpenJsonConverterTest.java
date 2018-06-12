package com.xy.xsql.tsql.converter.functions.rowset;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.rowset.OpenJsonFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.rowset.OpenJson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/5/31.
 */
public class OpenJsonConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OpenJsonConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPENJSON> ::=\n" +
                        "OPENJSON ( jsonExpression [ , path ] ) [ <with_clause> ]");
    }

    @Test
    public void testMetaPrint_WithClause() throws Exception {
        BlockMeta b = OpenJsonConverter.metaWith;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<with_clause> ::=\n" +
                        "WITH ( { colName type [ column_path ] [ AS JSON ] } [,...n] )");
    }

    private Map<OpenJson,String> model2StringMap;

    @Before
    public void init(){
        OpenJsonFunctionTest builderTest = new OpenJsonFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "OPENJSON(@pSearchOptions)");
        model2StringMap.put(
                builderTest.example3,
                "OPENJSON(SalesReasons)");
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