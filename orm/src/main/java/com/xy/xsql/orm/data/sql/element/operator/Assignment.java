package com.xy.xsql.orm.data.sql.element.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Assignment implements Operator {

    ASSIGNMENT("=", Type.Assignment);

    private java.lang.String keyword;
    private Type type;

    Assignment(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Assignment(java.lang.String keyword, Type type){
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
