package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_schema_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SchemaIdFunctionTest {


    /**
     * SCHEMA_ID()
     */
    public Schema_Id example1 = f_schema_id();

    /**
     * SCHEMA_ID('dbo')
     */
    public Schema_Id example2 = f_schema_id(
            c_string("dbo")
    );

    @Test
    public void testExample(){
        assertEquals(example2.getSchemaName().getClass(), StringConstant.class);
    }

}