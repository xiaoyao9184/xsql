package com.xy.xsql.orm.data.sql.element;

import com.xy.xsql.orm.data.sql.Element;

/**
 * Created by xiaoyao9184 on 2016/10/22.
 */
public enum OtherEnum implements Element {
    SPACE(" "),
    DELIMITER(","),
    GROUP_START("("),
    GROUP_END(")");

    private String string;

    OtherEnum(String string){
        this.string = string;
    }

    @Override
    public String toString(){
        return this.string;
    }
}
