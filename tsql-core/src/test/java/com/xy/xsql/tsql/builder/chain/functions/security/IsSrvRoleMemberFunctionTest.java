package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.Is_SrvRoleMember;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_is_srvrolemember;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IsSrvRoleMemberFunctionTest {


    /**
     * IS_SRVROLEMEMBER ('sysadmin')
     */
    public Is_SrvRoleMember example1 = f_is_srvrolemember(
            c_string("sysadmin")
    );

    /**
     * IS_SRVROLEMEMBER('diskadmin', 'Contoso\Pat')
     */
    public Is_SrvRoleMember example2 = f_is_srvrolemember(
            c_string("diskadmin"),
            c_string("Contoso\\Pat")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getRole().getString(), "sysadmin");
        assertEquals(example2.getRole().getString(), "diskadmin");
        assertEquals(example2.getLogin().getString(), "Contoso\\Pat");
    }

}