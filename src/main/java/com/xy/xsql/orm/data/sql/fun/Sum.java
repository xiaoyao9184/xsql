package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Sum extends Column {
    private Column column;

    public Sum(){
        this.column = new Column();
    }

    public Sum(Column column){
        this.column = column;
    }
}
