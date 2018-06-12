package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Database_Principal_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_database_principal_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DatabasePrincipalIdFunctionTest {


    /**
     * DATABASE_PRINCIPAL_ID('db_owner')
     */
    public Database_Principal_Id exampleB = f_database_principal_id(
            c_string("db_owner")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getPrincipalName().getClass(), StringConstant.class);
    }

}