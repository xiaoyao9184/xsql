package com.xy.xsql.tsql.model.elements.operators;

import com.xy.xsql.tsql.model.elements.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Compound implements Operator {

    ADD_ASSIGNMENT("+="),
    SUBTRACT_ASSIGNMENT("-="),
    MULTIPLY_ASSIGNMENT("*="),
    DIVIDE_ASSIGNMENT("/="),
    MODULO_ASSIGNMENT("%="),
    BITWISE_AND_ASSIGNMENT("&="),
    BITWISE_EXCLUSIVE_OR_ASSIGNMENT("^="),
    BITWISE_OR_ASSIGNMENT("|=");

    private String keyword;


    Compound(String keyword){
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
