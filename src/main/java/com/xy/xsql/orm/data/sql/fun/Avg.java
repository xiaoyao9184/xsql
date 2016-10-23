package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Avg extends Column {
    private Column column;

    public Avg(){
        this.column = new Column();
    }

    public Avg(Column column){
        this.column = column;
    }
}
