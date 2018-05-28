package com.xy.xsql.tsql.style.constructor.introducer.queries.select;


import com.xy.xsql.tsql.model.queries.select.For;
import org.junit.Test;

import static com.xy.xsql.tsql.style.constructor.introducer.queries.select.for_.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by xiaoyao9184 on 2018/5/10.
 */
public class for$Test {

    /*
    FOR XML
    See https://docs.microsoft.com/en-us/sql/t-sql/queries/select-for-clause-transact-sql#for-xml
     */

    // @formatter:off
    /**
     * FOR XML AUTO, TYPE, XMLSCHEMA, ELEMENTS XSINIL;
     */
    public For exampleXML = FOR(
            XML(
                    AUTO(),
                    TYPE(),
                    XMLSCHEMA(),
                    ELEMENTS(),
                    XSINIL()
            )
    ).build();
    // @formatter:on

    @Test
    public void testForXMLExample(){
        assertTrue(exampleXML.getXml().isUseAuto());
        assertTrue(exampleXML.getXml().isUseType());
        assertTrue(exampleXML.getXml().isUseXmlSchema());
        assertTrue(exampleXML.getXml().isUseElementsXsinil());
    }

}