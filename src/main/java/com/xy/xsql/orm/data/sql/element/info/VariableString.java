package com.xy.xsql.orm.data.sql.element.info;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2017/1/13.
 */
public class VariableString implements Element {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        if(this.name.startsWith("@")){
            return this.name;
        }
        return "@" + this.name;
    }
}
