package com.xy.xsql.tsql.builder.chain.functions.metadata;

import com.xy.xsql.tsql.model.functions.metadata.Object_Definition;
import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_n_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_definition;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ObjectDefinitionFunctionTest {


    /**
     * OBJECT_DEFINITION (OBJECT_ID(N'Person.uAddress'))
     */
    public Object_Definition exampleA = f_object_definition(
            f_object_id(
                    c_n_string("HumanResources.uAddress"))
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getObjectId().getClass(), Object_Id.class);
    }

}