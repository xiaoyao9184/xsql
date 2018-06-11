package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.metadata.Object_Schema_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_schema_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ObjectSchemaNameFunctionTest {


    /**
     * OBJECT_SCHEMA_NAME(st.objectid, st.dbid)
     */
    public Object_Schema_Name exampleA = f_object_schema_name(
            c("st","objectid"),
            c("st","dbid")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectId().getClass(), ColumnName.class);
        assertEquals(exampleB.getDatabaseId().getClass(), ColumnName.class);
    }

    /**
     * OBJECT_SCHEMA_NAME(object_id, database_id)
     */
    public Object_Schema_Name exampleB = f_object_schema_name(
            c("object_id"),
            c("database_id")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getObjectId().getClass(), ColumnName.class);
        assertEquals(exampleB.getDatabaseId().getClass(), ColumnName.class);
    }

}