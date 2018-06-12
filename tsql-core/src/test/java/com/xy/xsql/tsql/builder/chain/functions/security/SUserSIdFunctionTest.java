package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.security.SUser_SId;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_suser_sid;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SUserSIdFunctionTest {


    /**
     * SUSER_SID()
     */
    public SUser_SId exampleA = f_suser_sid();

    @Test
    public void testExampleA() {
        assertNull(exampleA.getLogin());
        assertNull(exampleA.getParam2());
    }

    /**
     * SUSER_SID('sa')
     */
    public SUser_SId exampleB = f_suser_sid(
            c_string("sa")
    );

    @Test
    public void testExampleB() {
        assertEquals(exampleB.getLogin().getString(), "sa");
    }

    /**
     * SUSER_SID('London\Workstation1')
     */
    public SUser_SId exampleC = f_suser_sid(
            c_string("London\\Workstation1")
    );

    @Test
    public void testExampleC() {
        assertEquals(exampleC.getLogin().getString(), "London\\Workstation1");
    }

    /**
     * SUSER_SID('TestComputer\User', 0)
     */
    public SUser_SId exampleE = f_suser_sid(
            c_string("TestComputer\\User"),
            c_number(0)
    );

    @Test
    public void testExampleD() {
        assertEquals(exampleE.getLogin().getString(), "TestComputer\\User");
        assertEquals(exampleE.getParam2().getClass(), NumberConstant.class);
    }

}