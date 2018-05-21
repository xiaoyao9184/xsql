package com.xy.xsql.block.tsql.core.clause;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.builder.chain.queries.OptionBuilderTest;
import com.xy.xsql.tsql.converter.queries.OptionConverter;
import com.xy.xsql.tsql.model.queries.Option;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class OptionConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = OptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<OPTION Clause> ::=\n" +
                        "OPTION ( <query_hint> [,...n] )");
    }

    @Test
    public void testMetaPrint_OptionQueryOption() throws Exception {
        BlockMeta b = OptionConverter.QueryOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<query_option> ::=\n" +
                        "LABEL = label_name\n" +
                        "| <query_hint>");
    }

    @Test
    public void testMetaPrint_OptionLabelQueryOption() throws Exception {
        BlockMeta b = OptionConverter.LabelQueryOptionConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "LABEL = label_name");
    }


    private Map<Option,String> model2StringMap;

    @Before
    public void init(){
        OptionBuilderTest builderTest = new OptionBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleA,
                "OPTION (HASH GROUP, FAST 10)");

        model2StringMap.put(
                builderTest.exampleB,
                "OPTION ( LABEL = 'q17' )");

        model2StringMap.put(
                builderTest.exampleC,
                "OPTION (HASH JOIN)");

        model2StringMap.put(
                builderTest.exampleD,
                "OPTION ( LABEL = 'CustJoin', HASH JOIN, MERGE JOIN)");

        model2StringMap.put(
                builderTest.exampleE,
                "OPTION (HASH JOIN)");

        model2StringMap.put(
                builderTest.exampleF,
                "OPTION (HASH JOIN)");

        model2StringMap.put(
                builderTest.exampleG,
                "OPTION ( FORCE ORDER )");

        model2StringMap.put(
                builderTest.exampleH1,
                "OPTION (FORCE EXTERNALPUSHDOWN)");

        model2StringMap.put(
                builderTest.exampleH2,
                "OPTION (DISABLE EXTERNALPUSHDOWN)");

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
                    .replaceAll("\t", "")
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