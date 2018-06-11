package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.system.Session_Context;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_session_context;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SessionContextFunctionTest {


    /**
     * SESSION_CONTEXT(N'user_id')
     */
    public Session_Context example1 = f_session_context(
            c_n_string("user_id")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getKey().getClass(), StringConstant.class);
    }

}