package com.xy.xsql.tsql.style.constructor.introducer.queries.predicates;

import com.xy.xsql.tsql.model.queries.predicates.FreeText;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.style.constructor.introducer.queries.predicates.freetext.FREETEXT;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2018/5/16.
 */
public class freetextTest {

    /**
     * b_FREETEXT (Document, 'vital safety components' );
     */
    @Test
    public void testExampleA(){
        // @formatter:off
        FreeText freeText = FREETEXT(
                c("Document"),
                "vital safety components"
        ).build();
        // @formatter:on

        assertEquals(freeText.getColumnName().toString(),"Document");
        assertEquals(freeText.getFreetextString().getString(),"vital safety components");
    }

    /**
     * b_FREETEXT(Description, @SearchWord);
     */
    @Test
    public void testExampleB(){
        // @formatter:off
        FreeText freeText = FREETEXT(
                c("Document"),
                //TODO Using FREETEXT with variables
                "@SearchWord"
        ).build();
        // @formatter:on

        assertEquals(freeText.getColumnName().toString(),"Document");
//        assertEquals(freeText.getFreetextString().toString(),"@SearchWord");
    }

}