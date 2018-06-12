package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.FileGroupPropertyFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.FileGroupProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class FileGroupPropertyConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FileGroupPropertyConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FILEGROUPPROPERTY> ::=\n" +
                        "FILEGROUPPROPERTY ( filegroup_name , property )");
    }

    private Map<FileGroupProperty,String> model2StringMap;

    @Before
    public void init(){
        FileGroupPropertyFunctionTest builderTest = new FileGroupPropertyFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1,
                "FILEGROUPPROPERTY('PRIMARY', 'IsDefault')");
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