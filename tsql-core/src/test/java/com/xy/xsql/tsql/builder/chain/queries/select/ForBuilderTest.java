package com.xy.xsql.tsql.builder.chain.queries.select;

import com.xy.xsql.tsql.model.queries.select.For;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.queries.select.ForBuilder.XmlConfigurator.*;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class ForBuilderTest {

    /*
    FOR XML
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-for-clause-transact-sql#for-xml
     */

    // @formatter:off
    /**
     * FOR XML AUTO, TYPE, XMLSCHEMA, ELEMENTS XSINIL;
     */
    public For exampleXML = new ForBuilder<Void>()
            .$Xml(
                    $Auto(),
                    $Type(),
                    $Xmlschema(),
                    $ElementsXsinil()
            )
            .build();
    // @formatter:on

    @Test
    public void testForXMLExample(){
        For for1 = new ForBuilder<Void>()
                .withXml()
                    .withAuto()
                    .withType()
                    .withXmlSchema()
                    .withElementsXsinil()
                    .and()
                .build();

        assertTrue(for1.getXml().isUseAuto());
        assertTrue(for1.getXml().isUseType());
        assertTrue(for1.getXml().isUseXmlSchema());
        assertTrue(for1.getXml().isUseElementsXsinil());
    }

}
