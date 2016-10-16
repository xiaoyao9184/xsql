package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class As implements Element {

    private String asName;

    public As() {

    }

    public String toString(){
        return "AS";
    }

    public Element name(String asName) {
        this.asName = asName;
        return this;
    }
}
