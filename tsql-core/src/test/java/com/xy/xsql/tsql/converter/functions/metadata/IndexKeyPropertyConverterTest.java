package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.IndexKeyPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.IndexKey_Property;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class IndexKeyPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IndexKeyPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INDEXKEY_PROPERTY> ::=\n" +
                        "INDEXKEY_PROPERTY ( object_ID , index_ID , key_ID , property )");
    }

    private Map<IndexKey_Property,String> model2StringMap;

    @Before
    public void init(){
        IndexKeyPropertyFunctionTest builderTest = new IndexKeyPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "INDEXKEY_PROPERTY(OBJECT_ID('Production.Location', 'U'),\n" +
                        "     1,1,'ColumnId')");
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