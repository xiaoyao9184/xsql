package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.AppLock_Test;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_applock_test;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class AppLockTestFunctionTest {


    /**
     * APPLOCK_TEST('public', 'Form1', 'Shared', 'Transaction');
     */
    public AppLock_Test example1 = f_applock_test(
            c_string("public"),
            c_string("Form1"),
            c_string("Shared"),
            c_string("Transaction")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDatabasePrincipal().getClass(), StringConstant.class);
        assertEquals(example1.getResourceName().getClass(), StringConstant.class);
        assertEquals(example1.getLockMode().getClass(), StringConstant.class);
        assertEquals(example1.getLockOwner().getClass(), StringConstant.class);
    }

}