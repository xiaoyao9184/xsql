package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Soundex;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_soundex;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SoundexFunctionTest {


    /**
     * SOUNDEX ('Smith')
     */
    public Soundex example1 = f_soundex(
            c_string("Smith")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterExpression().getClass(), StringConstant.class);
    }

}