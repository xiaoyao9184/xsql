package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Ascii;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_ascii;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AsciiFunctionTest {


    /**
     * ASCII('A')
     */
    public Ascii example1 = f_ascii(
            c_string("A")
    );

    /**
     * ASCII(1)
     */
    public Ascii example2 = f_ascii(
            c_number(1)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(example2.getCharacterExpression().getClass(), NumberConstant.class);
    }

}