package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Col_Length;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_col_length;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ColLengthFunctionTest {


    /**
     * COL_LENGTH('t1','c1')
     */
    public Col_Length example1 = f_col_length(
            c_string("t1"),
            c_string("c1")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getTable().getClass(), StringConstant.class);
        assertEquals(example1.getColumn().getClass(), StringConstant.class);
    }

}