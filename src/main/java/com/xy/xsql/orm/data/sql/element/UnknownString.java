package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/11/12.
 */
public class UnknownString implements Element {

    private String string;

    public UnknownString(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return this.string;
    }
}
