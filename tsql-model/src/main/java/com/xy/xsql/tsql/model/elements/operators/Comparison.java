package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Equals or same
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Comparison implements Operator, BinaryOperator {

    EQUAL("="),
    GREATER(">"),
    LESS("<"),
    GREATER_EQUAL(">="),
    LESS_EQUAL("<="),
    NOT_EQUAL("<>"),
    NOT_EQUAL_NOT_ISO("!="),
    NOT_LESS_NOT_ISO("!<"),
    NOT_GREATER_NOT_ISO("!>");

    private String keyword;

    Comparison(String keyword){
        this.keyword = keyword;
    }


    @Override
    public String toString(){
        return getKeyword();
    }

    @Override
    public String getKeyword(){
        return this.keyword;
    }

}
