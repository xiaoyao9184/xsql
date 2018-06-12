package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.DbNameFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Db_Name;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DbNameConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DbNameConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DB_NAME> ::=\n" +
                        "DB_NAME ( [ database_id ] )");
    }

    private Map<Db_Name,String> model2StringMap;

    @Before
    public void init(){
        DbNameFunctionTest builderTest = new DbNameFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "DB_NAME()");
        model2StringMap.put(
                builderTest.exampleB,
                "DB_NAME(3)");
        model2StringMap.put(
                builderTest.exampleD,
                "DB_NAME(database_id)");
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