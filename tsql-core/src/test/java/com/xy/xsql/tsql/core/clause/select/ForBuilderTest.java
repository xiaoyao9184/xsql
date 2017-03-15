package com.xy.xsql.tsql.core.clause.select;

import com.xy.xsql.tsql.model.clause.select.For;
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

                .build();

//        Assert.assertEquals(for1.getItems().get(0).getColumnExpression().toString(),"1");
//        Assert.assertEquals(for1.getItems().get(1).getColumnExpression().toString(),"2");
    }


}
