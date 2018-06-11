package com.xy.xsql.tsql.builder.chain.functions.datatype;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.datatype.Ident_Current;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.DataTypeFunctions.f_ident_current;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class IdentCurrentFunctionTest {


    /**
     * IDENT_CURRENT ('Person.Address')
     */
    public Ident_Current exampleA = f_ident_current(c_string("Person.Address"));

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getTableName().getClass(), StringConstant.class);
    }

    /**
     * IDENT_CURRENT('t7')
     */
    public Ident_Current exampleB = f_ident_current(c_string("t7"));

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getTableName().getClass(), StringConstant.class);
    }
}