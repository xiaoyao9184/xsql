package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.string.Space;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_space;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SpaceFunctionTest {


    /**
     * SPACE(2)
     */
    public Space example1 = f_space(
            c_number(2)
    );

    @Test
    public void testExample(){
        assertEquals(example1.getIntegerExpression().getClass(), NumberConstant.class);
    }

}