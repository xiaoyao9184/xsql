package com.xy.xsql.tsql.model.operator;

import com.xy.xsql.tsql.model.Keywords;

/**
 * Created by xiaoyao9184 on 2017/3/14.
 */
public enum Compound implements Operator {

    ADD_ASSIGNMENT("+=",Type.Compound),
    SUBTRACT_ASSIGNMENT("-=",Type.Compound),
    MULTIPLY_ASSIGNMENT("*=",Type.Compound),
    DIVIDE_ASSIGNMENT("/=",Type.Compound),
    MODULO_ASSIGNMENT("%=",Type.Compound),
    BITWISE_AND_ASSIGNMENT("&=",Type.Compound),
    BITWISE_EXCLUSIVE_OR_ASSIGNMENT("^=",Type.Compound),
    BITWISE_OR_ASSIGNMENT("|=",Type.Compound);

    private String keyword;
    private Type type;

    Compound(Keywords keyword, Type type){
        this.keyword = keyword.name();
        this.type = type;
    }

    Compound(String keyword, Type type){
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
