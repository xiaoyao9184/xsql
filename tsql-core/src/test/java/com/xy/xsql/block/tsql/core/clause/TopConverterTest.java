package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.TopBuilderTest;
import com.xy.xsql.tsql.model.clause.Top;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class TopConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = TopConverter.meta;

        StringWriter writer = new ModelMetaBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<TOP> ::=\n" +
                        "TOP ( expression ) [ PERCENT ] [ WITH TIES ]");
    }

    private Map<Top,String> model2StringMap;

    @Before
    public void init(){
        TopBuilderTest builderTest = new TopBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.example1A,
                "TOP(10)");

        model2StringMap.put(
                builderTest.example1B,
                "TOP(@p)");

        model2StringMap.put(
                builderTest.example1C,
                "TOP(5)PERCENT");

        model2StringMap.put(
                builderTest.example2A,
                "TOP(10)WITH TIES");

        model2StringMap.put(
                builderTest.example3A,
                "TOP (20)");

        model2StringMap.put(
                builderTest.example3B,
                "TOP (5)");

        model2StringMap.put(
                builderTest.example3C,
                "TOP (10)");
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