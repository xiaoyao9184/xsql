package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Insert implements Element {

    public String toString(){
        StringBuilder sql = new StringBuilder("INSERT INTO");
        return sql.append("\n").toString();
    }
}
