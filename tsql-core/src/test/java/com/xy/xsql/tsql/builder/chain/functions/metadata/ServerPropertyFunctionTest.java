package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.ServerProperty;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_serverproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ServerPropertyFunctionTest {


    /**
     * SERVERPROPERTY('MachineName')
     */
    public ServerProperty example1 = f_serverproperty(
            c_string("MachineName")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getPropertyName().getClass(), StringConstant.class);
    }

}