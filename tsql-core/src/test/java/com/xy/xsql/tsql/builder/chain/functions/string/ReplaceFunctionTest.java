package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Replace;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_replace;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ReplaceFunctionTest {


    /**
     * REPLACE('abcdefghicde','cde','xxx')
     */
    public Replace example1 = f_replace(
            c_string("abcdefghicde"),
            c_string("cde"),
            c_string("xxx")
    );

    /**
     * REPLACE('This is a Test'  COLLATE Latin1_General_BIN,
     'Test', 'desk' )
     */
    public Replace example2 = f_replace(
            //TODO String with COLLATE Expression
            c_string("This is a Test"),
            c_string("Test"),
            c_string("desk")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getStringExpression().getClass(), StringConstant.class);
        assertEquals(example1.getStringPattern().getClass(), StringConstant.class);
        assertEquals(example1.getStringReplacement().getClass(), StringConstant.class);
        assertEquals(example2.getStringExpression().getClass(), StringConstant.class);
        assertEquals(example2.getStringPattern().getClass(), StringConstant.class);
        assertEquals(example2.getStringReplacement().getClass(), StringConstant.class);
    }

}