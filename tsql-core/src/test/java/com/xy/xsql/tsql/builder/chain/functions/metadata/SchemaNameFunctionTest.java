package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.functions.metadata.Schema_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_schema_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class SchemaNameFunctionTest {


    /**
     * SCHEMA_NAME()
     */
    public Schema_Name exampleA = f_schema_name();

    /**
     * SCHEMA_NAME(1)
     */
    public Schema_Name exampleB = f_schema_name(
            c_number(1)
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getSchemaId().getClass(), NumberConstant.class);
    }

}