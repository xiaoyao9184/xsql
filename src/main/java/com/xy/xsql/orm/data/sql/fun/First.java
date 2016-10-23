package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class First extends Column {
    private Column column;

    public First(){
        this.column = new Column();
    }

    public First(Column column){
        this.column = column;
    }
}
