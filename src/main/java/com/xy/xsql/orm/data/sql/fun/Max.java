package com.xy.xsql.orm.data.sql.fun;

import com.xy.xsql.orm.data.sql.info.Column;

/**
 * Created by xiaoyao9184 on 2016/10/23.
 */
public class Max extends Column {

    private Column column;

    public Max(){
        this.column = new Column();
    }

    public Max(Column column){
        this.column = column;
    }
}