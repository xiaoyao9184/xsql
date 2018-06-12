package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.security.Has_DBAccess;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_has_dbaccess;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class HasDBAccessFunctionTest {

    /**
     * HAS_DBACCESS('AdventureWorks2012')
     */
    public Has_DBAccess example1 = f_has_dbaccess(
            c_string("AdventureWorks2012")
    );

    @Test
    public void testExample(){
        assertEquals(example1.getDatabaseName().getString(), "AdventureWorks2012");
    }

}