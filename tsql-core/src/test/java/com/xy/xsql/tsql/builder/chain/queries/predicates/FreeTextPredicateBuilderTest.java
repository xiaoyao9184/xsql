package com.xy.xsql.tsql.builder.chain.queries.predicates;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import org.junit.Test;

import static org.junit.Assert.*;

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

        assertEquals(freeText.getColumnName().toString(),"Document");
        assertEquals(freeText.getFreetextString().getString(),"vital safety components");
    }

    /**
     * FREETEXT(Description, @SearchWord);
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        FreeText freeText = new FreeTextPredicateBuilder<Void>()
                .withColumnName("Document")
                //TODO string variables
//                .withFreeText("SearchWord")
                .build();
        // @formatter:on

        assertEquals(freeText.getColumnName().toString(),"Document");
//        assertEquals(freeText.getFreetextString().getString(),"@SearchWord");
    }

}
