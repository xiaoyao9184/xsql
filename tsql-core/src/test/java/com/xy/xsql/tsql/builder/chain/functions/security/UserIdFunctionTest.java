package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.User_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_user_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class UserIdFunctionTest {


    /**
     * USER_ID('Harold')
     */
    public User_Id example1 = f_user_id(
            c_string("Harold")
    );

    @Test
    public void testExample1() {
        assertEquals(example1.getUser().getString(),"Harold");
    }

}