package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Equals or same
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Comparison implements Operator, BinaryOperator {

    EQUAL("=",Type.Comparison),
    GREATER(">",Type.Comparison),
    LESS("<",Type.Comparison),
    GREATER_EQUAL(">=",Type.Comparison),
    LESS_EQUAL("<=",Type.Comparison),
    NOT_EQUAL("<>",Type.Comparison),
    NOT_EQUAL_NOT_ISO("!=",Type.Comparison),
    NOT_LESS_NOT_ISO("!<",Type.Comparison),
    NOT_GREATER_NOT_ISO("!>",Type.Comparison);

    private String keyword;
    private Type type;

    Comparison(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Comparison(String keyword, Type type){
        this.keyword = keyword;
        this.type = type;
    }


    @Override
    public String toString(){
        return getKeyword();
    }

    @Override
    public String getKeyword(){
        return this.keyword;
    }

    @Override
    public Type getType(){
        return this.type;
    }

}
