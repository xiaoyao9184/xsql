package com.xy.xsql.tsql.builder.chain.functions.system;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.system.GetAnsiNull;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SystemFunctions.f_getansinull;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class GetAnsiNullFunctionTest {


    /**
     * GETANSINULL('AdventureWorks2012')
     */
    public GetAnsiNull example1 = f_getansinull(
            c_string("AdventureWorks2012")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDatabase().getClass(), StringConstant.class);
    }

}