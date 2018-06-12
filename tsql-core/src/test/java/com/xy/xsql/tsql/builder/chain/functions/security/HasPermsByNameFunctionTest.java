package com.xy.xsql.tsql.builder.chain.functions.security;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.datatypes.table.ColumnName;
import com.xy.xsql.tsql.model.elements.expressions.keyword.Null;
import com.xy.xsql.tsql.model.functions.metadata.Db_Name;
import com.xy.xsql.tsql.model.functions.security.Has_Perms_By_Name;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_null;
import static com.xy.xsql.tsql.builder.chain.functions.MetaDataFunctions.f_db_name;
import static com.xy.xsql.tsql.builder.chain.functions.SecurityFunctions.f_has_perms_by_name;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class HasPermsByNameFunctionTest {


    /**
     * HAS_PERMS_BY_NAME(null, null, 'VIEW SERVER STATE')
     */
    public Has_Perms_By_Name exampleA = f_has_perms_by_name(
            e_null(),
            e_null(),
            c_string("VIEW SERVER STATE")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getSecurable().getClass(), Null.class);
        assertEquals(exampleA.getSecurableClass().getClass(), Null.class);
        assertEquals(exampleA.getPermission().getClass(), StringConstant.class);
    }

    /**
     * HAS_PERMS_BY_NAME('Ps', 'LOGIN', 'IMPERSONATE')
     */
    public Has_Perms_By_Name exampleB = f_has_perms_by_name(
            c_string("Ps"),
            c_string("LOGIN"),
            c_string("IMPERSONATE")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getSecurable().getClass(), StringConstant.class);
        assertEquals(exampleB.getSecurableClass().getClass(), StringConstant.class);
        assertEquals(exampleB.getPermission().getClass(), StringConstant.class);
    }

    /**
     * HAS_PERMS_BY_NAME(db_name(), 'DATABASE', 'ANY')
     */
    public Has_Perms_By_Name exampleC = f_has_perms_by_name(
            f_db_name(),
            c_string("DATABASE"),
            c_string("ANY")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getSecurable().getClass(), Db_Name.class);
        assertEquals(exampleC.getSecurableClass().getClass(), StringConstant.class);
        assertEquals(exampleC.getPermission().getClass(), StringConstant.class);
    }

    //ExampleD is same as C

    /**
     * HAS_PERMS_BY_NAME('Sales.SalesPerson', 'OBJECT', 'INSERT')
     */
    public Has_Perms_By_Name exampleG = f_has_perms_by_name(
            c_string("Sales.SalesPerson"),
            c_string("OBJECT"),
            c_string("INSERT")
    );

    @Test
    public void testExampleG(){
        assertEquals(exampleG.getSecurable().getClass(), StringConstant.class);
        assertEquals(exampleG.getSecurableClass().getClass(), StringConstant.class);
        assertEquals(exampleG.getPermission().getClass(), StringConstant.class);
    }

    /**
     * HAS_PERMS_BY_NAME('T', 'OBJECT', 'SELECT', name, 'COLUMN')
     */
    public Has_Perms_By_Name exampleH = f_has_perms_by_name(
            c_string("T"),
            c_string("OBJECT"),
            c_string("SELECT"),
            c("name"),
            c_string("COLUMN")
    );

    @Test
    public void testExampleH(){
        assertEquals(exampleH.getSecurable().getClass(), StringConstant.class);
        assertEquals(exampleH.getSecurableClass().getClass(), StringConstant.class);
        assertEquals(exampleH.getPermission().getClass(), StringConstant.class);
        assertEquals(exampleH.getSubSecurable().getClass(), ColumnName.class);
        assertEquals(exampleH.getSubSecurableClass().getClass(), StringConstant.class);
    }

}