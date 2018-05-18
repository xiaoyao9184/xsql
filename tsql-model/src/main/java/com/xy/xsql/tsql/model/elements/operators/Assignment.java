package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Assignment implements Operator, BinaryOperator {

    ASSIGNMENT("=", Type.Assignment);

    private String keyword;
    private Type type;

    Assignment(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Assignment(String keyword, Type type){
        this.keyword = keyword;
        this.type = type;
    }

    @Override
    public String toString(){
        return this.keyword;
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
