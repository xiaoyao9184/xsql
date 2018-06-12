package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.SUser_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_suser_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SUserIdFunctionTest {


    /**
     * SUSER_ID('sa')
     */
    public SUser_Id example1 = f_suser_id(
            c_string("sa")
    );

    @Test
    public void testExample() {
        assertEquals(example1.getLogin().getString(), "sa");
    }

}