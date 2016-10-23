package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Mid extends Column {
    private Column column;
    private int start;
    private int length;

    public Mid(){
        this.column = new Column();
        this.length = 1;
        this.length = -1;
    }

    public Mid(Column column, int start){
        this.column = column;
        this.start = start;
        this.length = -1;
    }

    public Mid(Column column, int start, int length){
        this.column = column;
        this.start = start;
        this.length = length;
    }
}
