package com.xy.xsql.tsql.builder.chain.functions.rowset;

import com.xy.xsql.tsql.model.elements.variables.LocalVariable;
import com.xy.xsql.tsql.model.functions.rowset.OpenXml;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.elements.expressions.Expressions.e_variable;
import static com.xy.xsql.tsql.builder.chain.functions.RowsetFunctions.f_openxml;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class OpenXmlFunctionTest {


    /**
     * OPENXML (@idoc, '/ROOT/Customer',1)
     */
    public OpenXml exampleA = f_openxml(
            e_variable("idoc"),
            "/ROOT/Customer",
            OpenXml.Flags._1
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getIdoc().getClass(), LocalVariable.class);
        assertEquals(exampleA.getRowPattern(), "/ROOT/Customer");
        assertEquals(exampleA.getFlags(), OpenXml.Flags._1);
    }

    /**
     * OPENXML (@idoc, '/ROOT/Customer/Order/OrderDetail',2)
     */
    public OpenXml exampleB = f_openxml(
            e_variable("idoc"),
            "/ROOT/Customer/Order/OrderDetail",
            OpenXml.Flags._2
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getIdoc().getClass(), LocalVariable.class);
        assertEquals(exampleB.getRowPattern(), "/ROOT/Customer/Order/OrderDetail");
        assertEquals(exampleB.getFlags(), OpenXml.Flags._2);
    }

    /**
     *  OPENXML (@idoc, '/ROOT/Customers')
     */
    public OpenXml exampleC = f_openxml(
            e_variable("idoc"),
            "/ROOT/Customers"
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getIdoc().getClass(), LocalVariable.class);
        assertEquals(exampleC.getRowPattern(), "/ROOT/Customers");
    }

}