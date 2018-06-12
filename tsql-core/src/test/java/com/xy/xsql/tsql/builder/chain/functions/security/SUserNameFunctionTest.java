package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.security.SUser_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_suser_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SUserNameFunctionTest {


    /**
     * SUSER_NAME(1)
     */
    public SUser_Name example1 = f_suser_name(
            c_number(1)
    );

    @Test
    public void testExample() {
        assertEquals(example1.getServerUserId().getClass(), NumberConstant.class);
    }

}