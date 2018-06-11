package com.xy.xsql.tsql.builder.chain.functions.collation;

import com.xy.xsql.tsql.model.functions.collation.CollationProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.functions.CollationFunctions.f_collationproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CollationPropertyFunctionTest {


    /**
     * COLLATIONPROPERTY('Traditional_Spanish_CS_AS_KS_WS', 'CodePage')
     */
    public CollationProperty example1 = f_collationproperty(
            "Traditional_Spanish_CS_AS_KS_WS","CodePage");

    @Test
    public void testExample1(){
        assertEquals(example1.getCollationName(),"Traditional_Spanish_CS_AS_KS_WS");
        assertEquals(example1.getProperty(),"CodePage");
    }
}