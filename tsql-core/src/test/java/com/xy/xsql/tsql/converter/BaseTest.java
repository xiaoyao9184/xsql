package com.xy.xsql.tsql.converter;

import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import org.junit.Assert;

import java.io.StringWriter;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2018/6/11.
 */
public class BaseTest {

    public static void testPrint(Map<?,String> model2StringMap) throws Exception {
        testPrint(model2StringMap,false);
    }

    public static void testPrintIgnoreCase(Map<?,String> model2StringMap) throws Exception {
        testPrint(model2StringMap,true);
    }

    @SuppressWarnings("Duplicates")
    public static void testPrint(Map<?,String> model2StringMap, boolean ignoreCase) throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            System.out.println("Index:" + index[0]);
            StringWriter writer = ModelMetaBlockPrinter.print(key);
            System.out.println(writer.toString());
            System.out.println("==========");

            String check = writer.toString()
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            if(ignoreCase){
                check = check.toUpperCase();
                ok = ok.toUpperCase();
            }

            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }


    public static void testKeywordPrint(Map<?,String> model2StringMap) throws Exception {
        testKeywordPrint(model2StringMap,false);
    }

    public static void testKeywordPrintIgnoreCase(Map<?,String> model2StringMap) throws Exception {
        testKeywordPrint(model2StringMap,true);
    }

    @SuppressWarnings("Duplicates")
    public static void testKeywordPrint(Map<?,String> model2StringMap, boolean ignoreCase) throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            System.out.println("Index:" + index[0]);
            String check = ModelKeywordBlockConverter
                    .convert(key)
                    .print();
            System.out.println(check);
            System.out.println("==========");

            check = check
                    .replaceAll(" ", "")
                    .replaceAll("\t", "")
                    .replaceAll("\n", "");

            String ok = value
                    .replaceAll(" ", "")
                    .replaceAll("\n", "");

            if(ignoreCase){
                check = check.toUpperCase();
                ok = ok.toUpperCase();
            }

            Assert.assertEquals(
                    "Not Equal Index:" + index[0],
                    check,
                    ok);
            index[0]++;
        });
    }
}
