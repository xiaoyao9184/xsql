package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.system.ConnectionProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_connectionproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ConnectionPropertyFunctionTest {


    /**
     * ConnectionProperty('net_transport')
     */
    public ConnectionProperty example1 = f_connectionproperty(
            c_string("net_transport")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getProperty().getClass(), StringConstant.class);
    }

}