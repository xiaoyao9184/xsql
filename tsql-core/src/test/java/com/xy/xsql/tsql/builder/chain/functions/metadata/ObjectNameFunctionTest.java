package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.NumberConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.metadata.Object_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ObjectNameFunctionTest {


    /**
     * OBJECT_NAME(@MyID)
     */
    public Object_Name exampleA = f_object_name(
            e_variable("MyID")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectId().getClass(), LocalVariable.class);
    }

    /**
     * OBJECT_NAME(st.objectid, st.dbid)
     */
    public Object_Name exampleB = f_object_name(
            c("st","objectid"),
            c("st","dbid")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getObjectId().getClass(), ColumnName.class);
        assertEquals(exampleB.getDatabaseId().getClass(), ColumnName.class);
    }

    /**
     * OBJECT_NAME(274100017)
     */
    public Object_Name exampleD = f_object_name(
            c_number(274100017)
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getObjectId().getClass(), NumberConstant.class);
    }
}