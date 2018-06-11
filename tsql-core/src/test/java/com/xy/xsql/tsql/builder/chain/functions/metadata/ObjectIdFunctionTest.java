package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ObjectIdFunctionTest {


    /**
     * OBJECT_ID(N'AdventureWorks2012.Production.WorkOrder')
     */
    public Object_Id exampleA = f_object_id(
                    c_n_string("AdventureWorks2012.Production.WorkOrder")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectName().getClass(), StringConstant.class);
    }


    /**
     * OBJECT_ID (N'dbo.AWBuildVersion', N'U')
     */
    public Object_Id exampleB = f_object_id(
            c_n_string("dbo.AWBuildVersion"),
            c_n_string("U")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getObjectName().getClass(), StringConstant.class);
        assertEquals(exampleB.getObjectType().getClass(), StringConstant.class);
    }

}