package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.DatabasePropertyEX;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_databasepropertyex;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class DatabasePropertyEXFunctionTest {


    /**
     * DATABASEPROPERTYEX('AdventureWorks2014', 'IsAutoShrink')
     */
    public DatabasePropertyEX exampleB = f_databasepropertyex(
            c_string("AdventureWorks2014"),
            c_string("IsAutoShrink")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getDatabase().getClass(), StringConstant.class);
        assertEquals(exampleB.getProperty().getClass(), StringConstant.class);
    }

}