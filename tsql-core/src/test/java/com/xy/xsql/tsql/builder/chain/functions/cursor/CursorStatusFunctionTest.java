package com.xy.xsql.tsql.builder.chain.functions.cursor;

import com.xy.xsql.tsql.model.functions.cursor.Cursor_Status;
import org.junit.Test;

import static com.xy.xsql.tsql.builder.chain.datatypes.Constants.c_string;
import static com.xy.xsql.tsql.builder.chain.functions.CursorFunctions.f_cursor_status;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2018/6/5.
 */
public class CursorStatusFunctionTest {


    /**
     * CURSOR_STATUS('global','cur')
     */
    public Cursor_Status example1 = f_cursor_status(Cursor_Status.Scope.global,c_string("cur"));

    @Test
    public void testExample1(){
        assertEquals(example1.getScope(), Cursor_Status.Scope.global);
        assertEquals(example1.getCursorName().getString(), "cur");
    }
}