package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.string.Str;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_str;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StrFunctionTest {


    /**
     * STR(123.45, 6, 1)
     */
    public Str example1 = f_str(
            c_number(123.45),
            6,
            1
    );

    @Test
    public void testExample(){
        assertEquals(example1.getFloatExpression().getClass(), NumberConstant.class);
        assertEquals(example1.getLength(), new Integer(6));
        assertEquals(example1.getDecimal(), new Integer(1));
    }

}