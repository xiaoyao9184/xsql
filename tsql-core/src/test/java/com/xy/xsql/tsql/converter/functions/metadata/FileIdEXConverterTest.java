package com.xy.xsql.tsql.converter.functions.metadata;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.functions.metadata.FileIdExFunctionTest;
import com.xy.xsql.tsql.converter.BaseTest;
import com.xy.xsql.tsql.model.functions.metadata.File_IdEx;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public class FileIdEXConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = FileIdEXConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FILE_IDEX> ::=\n" +
                        "FILE_IDEX ( file_name )");
    }

    private Map<File_IdEx,String> model2StringMap;

    @Before
    public void init(){
        FileIdExFunctionTest builderTest = new FileIdExFunctionTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "FILE_IDEX('AdventureWorks2012_Data')");
        model2StringMap.put(
                builderTest.exampleB,
                "FILE_IDEX((SELECT TOP (1) name FROM sys.database_files WHERE type = 1))");
        model2StringMap.put(
                builderTest.exampleC,
                "FILE_IDEX((SELECT name FROM sys.master_files WHERE type = 4))");
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