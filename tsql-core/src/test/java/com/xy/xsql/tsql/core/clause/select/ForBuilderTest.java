package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.model.clause.select.For;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by xiaoyao9184 on 2017/1/18.
 */
public class ForBuilderTest {

    /**
     * FOR XML AUTO, TYPE, XMLSCHEMA, ELEMENTS XSINIL;
     */
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
