package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Format extends Column {
    private Column column;
    private String format;

    public Format(){
        this.column = new Column();
    }

    public Format(Column column, String format){
        this.column = column;
        this.format = format;
    }
}
