package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.SessionProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_sessionproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SessionPropertyFunctionTest {

    /**
     * SESSIONPROPERTY ('CONCAT_NULL_YIELDS_NULL')
     */
    public SessionProperty example1 = f_sessionproperty(
            c_string("CONCAT_NULL_YIELDS_NULL")
    );

    @Test
    public void testExample() {
        assertEquals(example1.getOption().getString(), "CONCAT_NULL_YIELDS_NULL");
    }

}