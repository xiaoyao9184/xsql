package com.xy.xsql.orm.data.sql.grammar;

import com.xy.xsql.orm.data.sql.Element;
import com.xy.xsql.orm.util.CheckUtil;

/**
 * Created by xiaoyao9184 on 2016/7/14.
 */
public class As implements Element {

    private String asName;

    public As() {

    }

    public Element name(String asName) {
        this.asName = asName;
        return this;
    }

    public String toString(){
        return "AS" + (CheckUtil.isNullOrEmpty(this.asName) ? "" : this.asName);
    }


}
