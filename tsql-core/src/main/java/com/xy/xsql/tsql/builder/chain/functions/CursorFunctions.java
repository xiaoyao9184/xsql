package com.xy.xsql.tsql.builder.chain.functions;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.cursor.*;

/**
 * Created by xiaoyao9184 on 2018/6/1.
 */
public interface CursorFunctions {

    static $$Cursor_Rows f_$$cursor_rows(){
        return new $$Cursor_Rows();
    }
    static $$Fetch_Status f_$$fetch_status(){
        return new $$Fetch_Status();
    }
    static Cursor_Status f_cursor_status(
            Cursor_Status.Scope scope,
            StringConstant cursorName){
        Cursor_Status f = new Cursor_Status();
        f.setScope(scope);
        f.setCursorName(cursorName);
        return f;
    }
}
