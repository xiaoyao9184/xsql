package com.xy.xsql.tsql.builder.chain.functions.rowset;

import com.xy.xsql.tsql.model.functions.rowset.OpenQuery;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.RowsetFunctions.f_openquery;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class OpenQueryFunctionTest {


    /**
     * OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles WHERE id = 101') 
     */
    public OpenQuery exampleA = f_openquery(
            "OracleSvr",
            c_string("SELECT name FROM joe.titles WHERE id = 101")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getLinkedServer(),"OracleSvr");
        assertEquals(exampleA.getQuery().getString(), "SELECT name FROM joe.titles WHERE id = 101");
    }

    /**
     *  OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles')
     */
    public OpenQuery exampleB = f_openquery(
            "OracleSvr",
            c_string("SELECT name FROM joe.titles")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getLinkedServer(),"OracleSvr");
        assertEquals(exampleB.getQuery().getString(), "SELECT name FROM joe.titles");
    }

    /**
     *  OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles WHERE name = ''NewTitle''')
     */
    public OpenQuery exampleC = f_openquery(
            "OracleSvr",
            c_string("SELECT name FROM joe.titles WHERE name = ''NewTitle''")
    );

    @Test
    public void testExampleC(){
        assertEquals(exampleC.getLinkedServer(),"OracleSvr");
        assertEquals(exampleC.getQuery().getString(), "SELECT name FROM joe.titles WHERE name = ''NewTitle''");
    }

    /**
     * OPENQUERY (OracleSvr, 'SELECT name FROM joe.titles WHERE name = ''NewTitle''')
     */
    public OpenQuery exampleD = f_openquery(
            "OracleSvr",
            c_string("SELECT name FROM joe.titles WHERE name = ''NewTitle''")
    );

    @Test
    public void testExampleD(){
        assertEquals(exampleD.getLinkedServer(),"OracleSvr");
        assertEquals(exampleD.getQuery().getString(), "SELECT name FROM joe.titles WHERE name = ''NewTitle''");
    }


}