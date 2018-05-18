package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.select.IntoBuilderTest;
import com.xy.xsql.tsql.model.queries.select.Into;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/21.
 */
public class IntoConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = IntoConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<INTO Clause> ::=\n" +
                        "INTO new_table [ ON filegroup ]");
    }


    private Map<Into,String> model2StringMap;

    @Before
    public void init(){
        IntoBuilderTest builderTest = new IntoBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "INTO dbo.EmployeeAddresses");

        model2StringMap.put(
                builderTest.exampleF,
                "INTO [dbo].[FactResellerSalesXL] ON FG2");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = ModelMetaBlockPrinter.print(key);
            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testKeywordPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            String check = ModelKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);

            check = check
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");
            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }

}