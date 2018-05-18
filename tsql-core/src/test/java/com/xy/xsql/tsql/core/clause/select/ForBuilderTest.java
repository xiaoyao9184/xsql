package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.model.queries.select.For;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.clause.select.ForBuilder.XmlConfigurator.*;

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
            .$XML(
                    AUTO(),
                    TYPE(),
                    XMLSCHEMA(),
                    ELEMENTS_XSINIL()
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

        Assert.assertTrue(for1.getXml().isUseAuto());
        Assert.assertTrue(for1.getXml().isUseType());
        Assert.assertTrue(for1.getXml().isUseXmlSchema());
        Assert.assertTrue(for1.getXml().isUseElementsXsinil());
    }

}
