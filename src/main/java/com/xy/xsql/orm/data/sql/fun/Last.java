package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Last extends Column {
    private Column column;

    public Last(){
        this.column = new Column();
    }

    public Last(Column column){
        this.column = column;
    }
}
