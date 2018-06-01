package com.xy.xsql.tsql.model.functions.cursor;

import com.xy.xsql.tsql.model.datatypes.constants.StringConstant;
import com.xy.xsql.tsql.model.functions.Function;

/**
 * Created by xiaoyao9184 on 2018/5/29.
 */
public class Cursor_Status
        implements CursorFunction, Function.InternalFunction {

    private Scope scope;
    private StringConstant cursorName;

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public StringConstant getCursorName() {
        return cursorName;
    }

    public void setCursorName(StringConstant cursorName) {
        this.cursorName = cursorName;
    }

    public enum Scope {
        local,
        global,
        variable;

        private String string;

        Scope(){
            this.string = "'" + this.name() + "'";
        }

        @Override
        public String toString(){
            return string;
        }
    }
}
