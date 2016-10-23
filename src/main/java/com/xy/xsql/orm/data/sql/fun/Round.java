package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Round extends Column {
    private Column column;
    private int decimals;

    public Round(){
        this.column = new Column();
    }

    public Round(Column column, int decimals){
        this.column = column;
    }
}
