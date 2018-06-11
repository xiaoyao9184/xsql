package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.metadata.ObjectPropertyEX;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_objectpropertyex;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ObjectPropertyExFunctionTest {


    /**
     * OBJECTPROPERTYEX ( object_id(N'MyEmployeeTable'), N'BaseType')
     */
    public ObjectPropertyEX exampleA = f_objectpropertyex(
            f_object_id(
                    c_n_string("MyEmployeeTable")
            ),
            c_n_string("BaseType")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getId().getClass(), Object_Id.class);
        assertEquals(exampleA.getProperty().getClass(), StringConstant.class);
    }

    /**
     * OBJECTPROPERTYEX(OBJECT_ID(N'HumanResources.Employee'), N'TABLEUPDATETRIGGERCOUNT')
     */
    public ObjectPropertyEX exampleB = f_objectpropertyex(
            f_object_id(
                    c_n_string("HumanResources.Employee")
            ),
            c_n_string("TABLEUPDATETRIGGERCOUNT")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getId().getClass(), Object_Id.class);
        assertEquals(exampleB.getProperty().getClass(), StringConstant.class);
    }

    /**
     * OBJECTPROPERTYEX(object_id, N'TableHasForeignKey')
     */
    public ObjectPropertyEX exampleC = f_objectpropertyex(
            c("object_id"),
            c_n_string("TableHasForeignKey")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getId().getClass(), ColumnName.class);
        assertEquals(exampleC.getProperty().getClass(), StringConstant.class);
    }

    /**
     * OBJECTPROPERTYEX ( object_id(N'dbo.DimReseller'), N'BaseType')
     */
    public ObjectPropertyEX exampleD = f_objectpropertyex(
            f_object_id(
                    c_n_string("dbo.DimReseller")
            ),
            c_n_string("BaseType")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getId().getClass(), Object_Id.class);
        assertEquals(exampleD.getProperty().getClass(), StringConstant.class);
    }

}