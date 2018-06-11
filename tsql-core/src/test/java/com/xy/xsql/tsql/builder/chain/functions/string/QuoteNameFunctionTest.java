package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.QuoteName;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_quotename;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class QuoteNameFunctionTest {


    /**
     * QUOTENAME('abc[]def')
     */
    public QuoteName example1 = f_quotename(
            c_string("abc[]def")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterString().getClass(), StringConstant.class);
    }

}