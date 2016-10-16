package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Case implements Element {

    private String value;

    public Case() {

    }

    public String toString(){
        return "CASE";
    }

    public Element value(String value) {
        this.value = value;
        return this;
    }
}
