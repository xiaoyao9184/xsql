package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.DatabasePrincipalIdFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.Database_Principal_Id;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class DatabasePrincipalIdConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = DatabasePrincipalIdConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<DATABASE_PRINCIPAL_ID> ::=\n" +
                        "DATABASE_PRINCIPAL_ID ( 'principal_name' )");
    }

    private Map<Database_Principal_Id,String> model2StringMap;

    @Before
    public void init(){
        DatabasePrincipalIdFunctionTest builderTest = new DatabasePrincipalIdFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleB,
                "DATABASE_PRINCIPAL_ID('db_owner')");
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