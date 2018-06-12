package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.security.User_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_user_name;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class UserNameFunctionTest {

    /**
     * USER_NAME(13)
     */
    public User_Name exampleA = f_user_name(
            c_number(13)
    );

    @Test
    public void testExampleA() {
        assertEquals(exampleA.getId().getClass(), NumberConstant.class);
    }

    /**
     * USER_NAME()
     */
    public User_Name exampleB = f_user_name();

    @Test
    public void testExampleB() {
        assertNull(exampleB.getId());
    }

    /**
     * USER_NAME(1)
     */
    public User_Name exampleC = f_user_name(
            c_number(1)
    );

    @Test
    public void testExampleC() {
        assertEquals(exampleC.getId().getClass(), NumberConstant.class);
    }

    //ExampleD is same as B
    //ExampleE is same as B
    //ExampleF is same as C

}