package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum StringConcatenation implements Operator {

    CONCATENATION("+",Type.String),
    CONCATENATION_SET("+=",Type.String);

    private java.lang.String keyword;
    private Type type;

    StringConcatenation(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    StringConcatenation(java.lang.String keyword, Type type){
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
