package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Unary implements Operator {

    PLUS("+",Type.Unary),
    NEGATIVE("-",Type.Unary);

    private java.lang.String keyword;
    private Type type;

    Unary(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Unary(java.lang.String keyword, Type type){
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
