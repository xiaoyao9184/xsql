package com.xy.xsql.orm.data.sql.element.operator;

import com.xy.xsql.orm.data.sql.element.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum String implements Operator {

    CONCATENATION("+",Type.String),
    CONCATENATION_SET("+=",Type.String);

    private java.lang.String keyword;
    private Type type;

    String(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    String(java.lang.String keyword, Type type){
        this.keyword = keyword;
        this.type = type;
    }

    public java.lang.String getKeyword(){
        return this.keyword;
    }

    public Type getType(){
        return this.type;
    }
}
