package com.xy.xsql.block.tsql.core.clause.select;

import com.xy.xsql.block.core.ReferenceBlockPrinter;
import com.xy.xsql.block.model.ReferenceBlock;
import com.xy.xsql.tsql.core.clause.select.ForBuilder;
import com.xy.xsql.tsql.model.clause.select.For;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static com.xy.xsql.tsql.core.clause.hints.TableHintBuilder.FORCESCAN;

/**
 * Created by xiaoyao9184 on 2017/6/20.
 */
public class ForConverterTest {

    @Test
    public void test() throws Exception {
        ReferenceBlock b = ForConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<FOR> ::=\n" +
                        "FOR { BROWSE | <XML> | <JSON> }");
    }

    @Test
    public void testXml() throws Exception {
        ReferenceBlock b = ForConverter.XmlConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

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
    public void testCommonDirectivesForXML() throws Exception {
        ReferenceBlock b = ForConverter.CommonDirectivesForXMLConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

        System.out.println(writer);
        Assert.assertEquals(writer.toString(),
                "<CommonDirectivesForXML> ::=\n" +
                        "[ , BINARY BASE64 ]\n" +
                        "[ , TYPE ]\n" +
                        "[ , ROOT [ ( 'RootName' ) ] ]");
    }

    @Test
    public void testJson() throws Exception {
        ReferenceBlock b = ForConverter.JsonConverter.meta();

        StringWriter writer = new ReferenceBlockPrinter()
                .print(b);

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


    @Test
    public void testPrintForXMLExample() throws Exception {
        For for1 = new ForBuilder<Void>()
                .withXml()
                    .withAuto()
                    .withType()
                    .withXmlSchema()
                    .withElementsXsinil()
                    .and()
                .build();

        StringWriter writer = ReferenceBlockPrinter.print(for1);
        String check = writer.toString()
                .replace(" ","")
                .replace("\n","");

        String ok = "FOR XML AUTO, TYPE, XMLSCHEMA, ELEMENTS XSINIL";
        ok = ok.replaceAll(" ","");
        Assert.assertEquals(
                check,
                ok);
    }

}