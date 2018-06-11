package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.string.Difference;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_difference;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DifferenceFunctionTest {


    /**
     * DIFFERENCE('Green','Greene')
     */
    public Difference example1 = f_difference(
            c_string("Green"),
            c_string("Greene")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getCharacterExpression().getClass(), StringConstant.class);
        assertEquals(example1.getCharacterExpression2().getClass(), StringConstant.class);
    }

}