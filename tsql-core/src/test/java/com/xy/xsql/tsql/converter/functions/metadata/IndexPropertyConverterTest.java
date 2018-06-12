package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.IndexPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.IndexProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class IndexPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IndexPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INDEXPROPERTY> ::=\n" +
                        "INDEXPROPERTY ( object_ID , index_or_statistics_name , property )");
    }

    private Map<IndexProperty,String> model2StringMap;

    @Before
    public void init(){
        IndexPropertyFunctionTest builderTest = new IndexPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "INDEXPROPERTY(OBJECT_ID('HumanResources.Employee'),\n" +
                        "     'PK_Employee_BusinessEntityID','IsClustered')");
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