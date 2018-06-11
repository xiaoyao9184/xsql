package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Db_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_db_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DbIdFunctionTest {


    /**
     * DB_ID()
     */
    public Db_Id example1 = f_db_id();

    /**
     * DB_ID(N'AdventureWorks2008R2')
     */
    public Db_Id example2 = f_db_id(
            c_n_string("AdventureWorks2008R2")
    );

    @Test
    public void testExample(){
        assertEquals(example2.getDatabaseName().getClass(), StringConstant.class);
    }

}