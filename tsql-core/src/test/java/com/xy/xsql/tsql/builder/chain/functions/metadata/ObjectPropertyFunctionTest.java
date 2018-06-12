package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.functions.metadata.ObjectProperty;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_objectproperty;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ObjectPropertyFunctionTest {


    /**
     * OBJECTPROPERTY (OBJECT_ID(N'Production.UnitMeasure'),'ISTABLE')
     */
    public ObjectProperty exampleA = f_objectproperty(
            f_object_id(
                    c_n_string("Production.UnitMeasure")
            ),
            c_string("ISTABLE")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getId().getClass(), Object_Id.class);
        assertEquals(exampleA.getProperty().getClass(), StringConstant.class);
    }

    /**
     * OBJECTPROPERTY(OBJECT_ID('dbo.ufnGetProductDealerPrice'), 'IsDeterministic')
     */
    public ObjectProperty exampleB = f_objectproperty(
            f_object_id(
                    c_string("dbo.ufnGetProductDealerPrice")
            ),
            c_string("IsDeterministic")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getId().getClass(), Object_Id.class);
        assertEquals(exampleB.getProperty().getClass(), StringConstant.class);
    }

    /**
     * OBJECTPROPERTY(object_id, N'SchemaId')
     */
    public ObjectProperty exampleC = f_objectproperty(
            c("object_id"),
            c_n_string("SchemaId")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getId().getClass(), ColumnName.class);
        assertEquals(exampleC.getProperty().getClass(), StringConstant.class);
    }

    /**
     * OBJECTPROPERTY (OBJECT_ID(N'dbo.DimReseller'),'ISTABLE')
     */
    public ObjectProperty exampleD = f_objectproperty(
            f_object_id(
                    c_n_string("dbo.DimReseller")
            ),
            c_string("ISTABLE")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getId().getClass(), Object_Id.class);
        assertEquals(exampleD.getProperty().getClass(), StringConstant.class);
    }

}