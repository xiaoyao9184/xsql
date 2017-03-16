package com.xy.xsql.tsql.core.predicate;

import com.xy.xsql.tsql.model.predicate.Contains;
import com.xy.xsql.tsql.model.predicate.FreeText;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.expression.ExpressionBuilder.e_variable;

/**
 * Created by xiaoyao9184 on 2017/3/16.
 */
public class FreeTextPredicateBuilderTest {

    /**
     * FREETEXT (Document, 'vital safety components' );
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        FreeText freeText = new FreeTextPredicateBuilder<Void>()
                .withColumnName("Document")
                .withFreeText("vital safety components")
                .build();
        // @formatter:on

        Assert.assertEquals(freeText.getColumnName().toString(),"Document");
        Assert.assertEquals(freeText.getFreetextString().toString(),"'vital safety components'");
    }

    /**
     * FREETEXT(Description, @SearchWord);
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        FreeText freeText = new FreeTextPredicateBuilder<Void>()
                .withColumnName("Document")
                //TODO
//                .withFreeText(e_variable("SearchWord"))
                .build();
        // @formatter:on

        Assert.assertEquals(freeText.getColumnName().toString(),"Document");
//        Assert.assertEquals(freeText.getFreetextString().toString(),"@SearchWord");
    }

}
