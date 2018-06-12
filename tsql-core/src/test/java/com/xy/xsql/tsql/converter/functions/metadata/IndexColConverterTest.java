package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.IndexColFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Index_Col;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class IndexColConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IndexColConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INDEX_COL> ::=\n" +
                        "INDEX_COL ( [ server_name . ] [ database_name . [ schema_name ] . | schema_name . ] table_name , index_id , key_id )");
    }

    private Map<Index_Col,String> model2StringMap;

    @Before
    public void init(){
        IndexColFunctionTest builderTest = new IndexColFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "INDEX_COL (N'AdventureWorks2012.Sales.SalesOrderDetail', 1,1)");
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