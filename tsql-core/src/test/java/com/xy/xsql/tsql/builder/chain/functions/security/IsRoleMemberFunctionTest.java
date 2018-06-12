package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.Is_RoleMember;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_is_rolemember;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsRoleMemberFunctionTest {


    /**
     * IS_ROLEMEMBER ('db_datareader')
     */
    public Is_RoleMember example1 = f_is_rolemember(
            c_string("db_datareader")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getRole().getString(), "db_datareader");
    }

}