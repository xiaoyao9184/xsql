package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.functions.metadata.Object_Id;
import com.xy.xsql.tsql.model.functions.security.Permissions;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_object_id;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_permissions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class PermissionsFunctionTest {


    /**
     * PERMISSIONS()
     */
    public Permissions exampleA = f_permissions();

    @Test
    public void testExampleA(){
        assertNull(exampleA.getColumn());
        assertNull(exampleA.getObjectId());
    }

    /**
     * PERMISSIONS(OBJECT_ID('AdventureWorks2012.Person.Address','U'))
     */
    public Permissions exampleB = f_permissions(
            f_object_id(c_string("AdventureWorks2012.Person.Address"),
                    c_string("U"))
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getObjectId().getClass(), Object_Id.class);
    }

    //ExampleC is same as B

}