package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class Join implements Element {

    private boolean left;

    public Join() {

    }

    public Join left(){
        this.left = true;
        return this;
    }



    public String toString(){
        return "JOIN";
    }
}
