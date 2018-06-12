package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.Is_Member;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_is_member;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsMemberFunctionTest {


    /**
     * IS_MEMBER ('db_owner')
     */
    public Is_Member example1 = f_is_member(
            c_string("db_owner")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getRole().getString(), "db_owner");
    }

}