package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.MetaContextBlockPrinter;
import com.xy.xsql.block.core.MetaContextKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.select.ForBuilderTest;
import com.xy.xsql.tsql.model.clause.select.For;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class ForConverterTest {

    @Test
    public void testMetaPrint() throws Exception {
        BlockMeta b = ForConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FOR> ::=\n" +
                        "FOR { BROWSE | <XML> | <JSON> }");
    }

    @Test
    public void testMetaPrint_Xml() throws Exception {
        BlockMeta b = ForConverter.XmlConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<XML> ::=\n" +
                        "XML\n" +
                        "{\n" +
                        "{ RAW [ ( 'ElementName' ) ] | AUTO } \n" +
                        "[\n" +
                        "<CommonDirectivesForXML>\n" +
                        "[ , { XMLDATA | XMLSCHEMA [ ( 'TargetNameSpaceURI' ) ] } ]\n" +
                        "[ , ELEMENTS [ XSINIL | ABSENT ] ]\n" +
                        "]\n" +
                        "| EXPLICIT\n" +
                        "[\n" +
                        "<CommonDirectivesForXML>\n" +
                        "[ , XMLDATA ]\n" +
                        "]\n" +
                        "| PATH [ ( 'ElementName' ) ] \n" +
                        "[\n" +
                        "<CommonDirectivesForXML>\n" +
                        "[ , ELEMENTS [ XSINIL | ABSENT ] ]\n" +
                        "]\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_CommonDirectivesForXML() throws Exception {
        BlockMeta b = ForConverter.CommonDirectivesForXMLConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CommonDirectivesForXML> ::=\n" +
                        "[ , BINARY BASE64 ]\n" +
                        "[ , TYPE ]\n" +
                        "[ , ROOT [ ( 'RootName' ) ] ]");
    }

    @Test
    public void testJson() throws Exception {
        BlockMeta b = ForConverter.JsonConverter.meta;

        StringWriter writer = new MetaContextBlockPrinter()
                .printMeta(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "JSON\n" +
                        "{\n" +
                        "{ AUTO | PATH }\n" +
                        "[\n" +
                        "[ , ROOT ( 'RootName' ) ]\n" +
                        "[ , INCLUDE_NULL_VALUES ]\n" +
                        "[ , WITHOUT_ARRAY_WRAPPER ]\n" +
                        "]\n" +
                        "}");
    }


    private Map<For,String> model2StringMap;

    @Before
    public void init(){
        ForBuilderTest builderTest = new ForBuilderTest();
        model2StringMap = new LinkedHashMap<>();

        model2StringMap.put(
                builderTest.exampleXML,
                "FOR XML AUTO, TYPE, XMLSCHEMA, ELEMENTS XSINIL");

    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testPrint() throws Exception {
        final int[] index = {1};
        model2StringMap.forEach((key, value) -> {
            StringWriter writer = MetaContextBlockPrinter.print(key);
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
            String check = MetaContextKeywordBlockConverter
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