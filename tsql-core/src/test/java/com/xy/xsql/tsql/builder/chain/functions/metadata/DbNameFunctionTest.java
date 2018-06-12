package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.metadata.Db_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_db_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DbNameFunctionTest {


    /**
     * DB_NAME()
     */
    public Db_Name exampleA = f_db_name();

    /**
     * DB_NAME(3)
     */
    public Db_Name exampleB = f_db_name(
            c_number(3)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getDatabaseId().getClass(), NumberConstant.class);
    }

    /**
     * DB_NAME(database_id)
     */
    public Db_Name exampleD = f_db_name(
            c("database_id")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getDatabaseId().getClass(), ColumnName.class);
    }

}