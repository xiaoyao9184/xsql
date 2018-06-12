package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.LoginProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_loginproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class LoginPropertyFunctionTest {


    /**
     * LOGINPROPERTY('John3', 'IsMustChange')
     */
    public LoginProperty exampleA = f_loginproperty(
            c_string("John3"),
            c_string("IsMustChange")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getLoginName().getString(), "John3");
        assertEquals(exampleA.getPropertyName().getString(), "IsMustChange");
    }

    /**
     * LOGINPROPERTY('John3', 'IsLocked')
     */
    public LoginProperty exampleB = f_loginproperty(
            c_string("John3"),
            c_string("IsLocked")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getLoginName().getString(), "John3");
        assertEquals(exampleB.getPropertyName().getString(), "IsLocked");
    }

}