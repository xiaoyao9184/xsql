package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.security.PWDcompare;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_pwdcompare;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class PWDcompareFunctionTest {


    /**
     * PWDCOMPARE('', password_hash)
     */
    public PWDcompare exampleA = f_pwdcompare(
            c_string(""),
            c("password_hash")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getClearTextPassword().getString(),"");
        assertEquals(exampleA.getPasswordHash().getClass(), ColumnName.class);
    }

    /**
     * PWDCOMPARE('password', password_hash)
     */
    public PWDcompare exampleB = f_pwdcompare(
            c_string("password"),
            c("password_hash")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getClearTextPassword().getString(),"password");
        assertEquals(exampleB.getPasswordHash().getClass(), ColumnName.class);
    }

}