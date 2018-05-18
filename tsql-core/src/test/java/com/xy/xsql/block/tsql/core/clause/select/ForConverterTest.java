package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.printer.MetaBlockPrinter;
import com.xy.xsql.block.core.printer.ModelMetaBlockPrinter;
import com.xy.xsql.block.core.converter.ModelKeywordBlockConverter;
import com.xy.xsql.block.model.BlockMeta;
import com.xy.xsql.tsql.core.clause.select.ForBuilderTest;
import com.xy.xsql.tsql.model.queries.select.For;
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

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FOR> ::=\n" +
                        "FOR { BROWSE | <XML> | <JSON> }");
    }

    @Test
    public void testMetaPrint_Xml() throws Exception {
        BlockMeta b = ForConverter.XmlConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<XML> ::=\n" +
                        "XML\n" +
                        "{\n" +
                        "\t{ RAW [ ( 'ElementName' ) ] | AUTO }\n" +
                        "\t[\n" +
                        "\t\t<CommonDirectivesForXML>\n" +
                        "\t\t[ , { XMLDATA | XMLSCHEMA [ ( 'TargetNameSpaceURI' ) ] } ]\n" +
                        "\t\t[ , ELEMENTS [ XSINIL | ABSENT ] ]\n" +
                        "\t]\n" +
                        "\t| EXPLICIT\n" +
                        "\t[\n" +
                        "\t\t<CommonDirectivesForXML>\n" +
                        "\t\t[ , XMLDATA ]\n" +
                        "\t]\n" +
                        "\t| PATH [ ( 'ElementName' ) ]\n" +
                        "\t[\n" +
                        "\t\t<CommonDirectivesForXML>\n" +
                        "\t\t[ , ELEMENTS [ XSINIL | ABSENT ] ]\n" +
                        "\t]\n" +
                        "}");
    }

    @Test
    public void testMetaPrint_CommonDirectivesForXML() throws Exception {
        BlockMeta b = ForConverter.CommonDirectivesForXMLConverter.meta;

        StringWriter writer = MetaBlockPrinter.print(b);

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

        StringWriter writer = MetaBlockPrinter.print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "JSON\n" +
                        "{\n" +
                        "\t{ AUTO | PATH }\n" +
                        "\t[\n" +
                        "\t\t[ , ROOT ( 'RootName' ) ]\n" +
                        "\t\t[ , INCLUDE_NULL_VALUES ]\n" +
                        "\t\t[ , WITHOUT_ARRAY_WRAPPER ]\n" +
                        "\t]\n" +
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