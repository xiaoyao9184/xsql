package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Scope_Resolution implements Operator {

    SCOPE_RESOLUTION("::",Type.Scope_Resolution);

    private java.lang.String keyword;
    private Type type;

    Scope_Resolution(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Scope_Resolution(java.lang.String keyword, Type type){
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
