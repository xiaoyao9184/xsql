package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Stuff;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_stuff;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class StuffFunctionTest {


    /**
     * STUFF('abcdef', 2, 3, 'ijklmn')
     */
    public Stuff example1 = f_stuff(
            c_string("abcdef"),
            c_number(2),
            c_number(3),
            c_string("ijklmn")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(example1.getStart().getClass(), NumberConstant.class);
        assertEquals(example1.getLength().getClass(), NumberConstant.class);
        assertEquals(example1.getReplaceWithExpression().getClass(), StringConstant.class);
    }

}