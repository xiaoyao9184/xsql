package com.xy.xsql.tsql.builder.chain.functions.string;

import com.xy.xsql.tsql.model.functions.string.Concat;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_number;
import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.datatypes.table.ColumnNameFactory.c;
import static com.xy.xsql.tsql.builder.chain.functions.StringFunctions.f_concat;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class ConcatFunctionTest {


    /**
     * CONCAT ( 'Happy ', 'Birthday ', 11, '/', '25' )
     */
    public Concat exampleA = f_concat(
            c_string("Happy"),
            c_string("Birthday"),
            c_number(11),
            c_string("/"),
            c_string("25")
    );

    @Test
    public void testExampleA(){
        assertEquals(exampleA.getStringValueList().size(), 5);
    }

    /**
     * CONCAT( emp_name, emp_middlename, emp_lastname )
     */
    public Concat exampleB = f_concat(
            c("emp_name"),
            c("emp_middlename"),
            c("emp_lastname")
    );

    @Test
    public void testExampleB(){
        assertEquals(exampleB.getStringValueList().size(), 3);
    }

}