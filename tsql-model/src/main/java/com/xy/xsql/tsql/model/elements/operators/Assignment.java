package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Assignment implements Operator, BinaryOperator {

    ASSIGNMENT("=");

    private String keyword;

    Assignment(String keyword){
        this.keyword = keyword;
    }

    @Override
    public String toString(){
        return this.keyword;
    }

    @Override
    public String getKeyword(){
        return this.keyword;
    }

}
