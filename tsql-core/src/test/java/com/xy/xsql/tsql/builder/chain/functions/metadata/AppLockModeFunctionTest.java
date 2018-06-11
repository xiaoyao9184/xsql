package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.AppLock_Mode;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_applock_mode;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AppLockModeFunctionTest {


    /**
     * APPLOCK_MODE('public', 'Form1', 'Transaction')
     */
    public AppLock_Mode example1 = f_applock_mode(
            c_string("public"),
            c_string("Form1"),
            c_string("Transaction")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDatabasePrincipal().getClass(), StringConstant.class);
        assertEquals(example1.getResourceName().getClass(), StringConstant.class);
        assertEquals(example1.getLockOwner().getClass(), StringConstant.class);
    }

}